package alis.store.domain.handlers;

import alis.store.domain.commands.inputs.CreateUserCommand;
import alis.store.domain.commands.inputs.DeleteUserCommand;
import alis.store.domain.commands.outputs.CreateUserCommandResult;
import alis.store.domain.commands.outputs.DeleteUserCommandResult;
import alis.store.domain.queries.QueryUsersResult;
import alis.store.domain.repositories.IUserRepository;
import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Document;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;
import alis.store.shared.commands.ICommand;
import alis.store.shared.commands.ICommandHandler;
import alis.store.shared.commands.ICommandResult;
import alis.store.domain.entities.User;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserCreateHandler implements ICommandHandler<CreateUserCommand> {


    private BCryptPasswordEncoder Pe;

	private IUserRepository Repository;
    
	private List<String> Notifications;


	@Autowired
    public UserCreateHandler(IUserRepository repository, BCryptPasswordEncoder pe) {
        Repository = repository;
        Notifications = new ArrayList<>();
        Pe = pe;
    }

    public ICommandResult Handle(CreateUserCommand command) throws Exception{

        if (Repository.CheckIfEmailAlreadyExists(command.Email))
            Notifications.add("Email " + command.Email.toString() + " already exists.");

        if (Repository.CheckIfDocumentAlreadyExists(command.Document))
            Notifications.add("Document number " + command.Document.toString() + " already exists.");

        Document document = new Document(command.Document);
        if (document.isInvalid())
            Notifications.add("Invalid document.");

        if (Notifications.isEmpty()){
            Name name = new Name(command.FirstName, command.LastName);
            Address address = new Address(command.BillingAddress, command.HomeAddress);
            Email email = new Email(command.Email);
            User user = new User(name, address, email, document, command.Type, Pe.encode(command.Password));
            Repository.AddUser(user);

            CreateUserCommandResult result = new CreateUserCommandResult();
            result.Id = user.getId();
            return result;
        }

        String exceptionMessage = String.join(",",Notifications);
        throw new Exception(exceptionMessage);
    }



}
