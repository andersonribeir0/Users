package alis.store.domain.handlers;

import alis.store.domain.commands.inputs.CreateUserCommand;
import alis.store.domain.commands.outputs.CreateUserCommandResult;
import alis.store.domain.repositories.IUserRepository;
import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Document;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;
import alis.store.shared.commands.ICommandHandler;
import alis.store.shared.commands.ICommandResult;
import alis.store.domain.entities.User;
import org.junit.platform.commons.util.StringUtils;
import sun.tools.java.Environment;

import java.util.ArrayList;
import java.util.List;

public class UserHandler implements ICommandHandler<CreateUserCommand> {
    private IUserRepository Repository;
    private List<String> Notifications;

    public UserHandler(IUserRepository repository) {
        Repository = repository;
        Notifications = new ArrayList<>();

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
            User user = new User(name, address, email, document, command.Type);
            Repository.AddUser(user);

            CreateUserCommandResult result = new CreateUserCommandResult();
            result.Id = user.getId();
            return result;
        } else {
            String exceptionMessage = String.join(",",Notifications);
            throw new Exception(exceptionMessage);
        }
    }
}
