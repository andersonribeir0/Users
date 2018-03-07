package alis.store.domain.handlers;

import alis.store.domain.commands.inputs.UpdateUserCommand;
import alis.store.domain.commands.outputs.CreateUserCommandResult;
import alis.store.domain.commands.outputs.UpdateUserCommandResult;
import alis.store.domain.entities.User;
import alis.store.domain.queries.QueryUsersResult;
import alis.store.domain.repositories.IUserRepository;
import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Document;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;
import alis.store.shared.commands.ICommandHandler;
import alis.store.shared.commands.ICommandResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserUpdateHandler implements ICommandHandler<UpdateUserCommand>{
    private IUserRepository Repository;
    private List<String> Notifications;
    @Autowired
    public UserUpdateHandler(IUserRepository repository) {
        Repository = repository;
        Notifications = new ArrayList<>();
    }

    public ICommandResult Handle(UpdateUserCommand command) throws Exception{

        QueryUsersResult queryUsersResult = Repository.GetById(command.Id);
        if (!command.Email.equals(queryUsersResult.Email) && Repository.CheckIfEmailAlreadyExists(command.Email))
            Notifications.add("Email " + command.Email.toString() + " already exists.");

        if (!command.Document.equals(queryUsersResult.Document) && Repository.CheckIfDocumentAlreadyExists(command.Document))
            Notifications.add("Document number " + command.Document.toString() + " already exists.");

        Document document = new Document(command.Document);
        if (document.isInvalid())
            Notifications.add("Invalid document.");

        if (Notifications.isEmpty()){
            Name name = new Name(command.FirstName, command.LastName);
            Address address = new Address(command.BillingAddress, command.HomeAddress);
            Email email = new Email(command.Email);
            User user = new User(name, address, email, document, command.Type);
            boolean result = Repository.UpdateUser(command.Id, user);
            UpdateUserCommandResult commandResult = new UpdateUserCommandResult();
            commandResult.Document = command.Document;
            commandResult.Success = result;
            return commandResult;
        }

        String exceptionMessage = String.join(",",Notifications);
        throw new Exception(exceptionMessage);
    }
}
