package handlers;

import alis.store.domain.commands.inputs.CreateUserCommand;
import alis.store.domain.enums.EType;
import alis.store.domain.handlers.UserHandler;
import alis.store.domain.repositories.IUserRepository;
import alis.store.shared.commands.ICommandResult;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class UserHandlerTest {

    public IUserRepository Repository = mock(IUserRepository.class);
    public UserHandler UserHandler = new UserHandler(Repository);

    @Test
    public void Should_Return_Valid_Result_If_A_Valid_User_Is_Added() throws Exception {

        CreateUserCommand command = new CreateUserCommand();
        command.Document = "76583352311";
        command.Email = "abc@gmail.com";
        command.BillingAddress = "rua b";
        command.HomeAddress = command.BillingAddress;
        command.FirstName = "Anderson";
        command.LastName = "Ribeiro";
        command.Type = EType.Admin;

        when(Repository.CheckIfDocumentAlreadyExists(command.Document)).thenReturn(false);
        when(Repository.CheckIfEmailAlreadyExists(command.Email)).thenReturn(false);

        final ICommandResult result = UserHandler.Handle(command);

        assertNotNull(result);
    }

    @Test
    public void Should_Throws_Exception_If_Document_Is_Invalid() throws Exception{
        CreateUserCommand command = new CreateUserCommand();
        command.Document = "11111111111";
        command.Email = "abc@gmail.com";
        command.BillingAddress = "rua b";
        command.HomeAddress = command.BillingAddress;
        command.FirstName = "Anderson";
        command.LastName = "Ribeiro";
        command.Type = EType.Admin;

        when(Repository.CheckIfDocumentAlreadyExists(command.Document)).thenReturn(false);
        when(Repository.CheckIfEmailAlreadyExists(command.Email)).thenReturn(false);

        assertThrows(Exception.class, () -> UserHandler.Handle(command));
    }
}
