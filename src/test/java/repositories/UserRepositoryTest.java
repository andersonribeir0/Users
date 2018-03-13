package repositories;

import alis.store.domain.entities.User;
import alis.store.domain.enums.EType;
import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Document;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;
import alis.store.infra.repositories.UserRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

public class UserRepositoryTest {

    private Document document = new Document("76583352311");
    private Email email = new Email("abc@gmail.com");
    private Name name = new Name("Anderson","Ribeiro");
    private Address address = new Address("B Street", "A Street");
    private User user = new User(name, address, email, document, EType.ADMIN);
    private UserRepository repository = new UserRepository();

    @Test
    public void Should_Return_True_If_Document_Already_Exists(){
        repository.AddUser(user);
        Document document = new Document("76583352311");
        boolean result = repository.CheckIfDocumentAlreadyExists(document.toString());
        assertTrue(result);
    }

    @Test
    public void Should_Return_True_If_Email_Already_Exists(){
        repository.AddUser(user);
        Email email = new Email("abc@gmail.com");
        boolean result = repository.CheckIfEmailAlreadyExists(email.toString());
        assertTrue(result);
    }

    @Test
    public void Should_Add_User_In_Repository(){
        repository.AddUser(user);
        assertFalse(repository.GetAll().isEmpty());
    }
}
