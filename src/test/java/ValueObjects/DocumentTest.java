package ValueObjects;

import alis.store.domain.ValueObjects.Document;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class DocumentTest {

    private static javax.validation.Validator validator;


    @BeforeAll
    public static void setUp(){
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }


    @Test
    public void Should_Return_True_If_Document_Is_Valid() {
        Document validDocument = new Document("42697113029");
        boolean isValid = validDocument.isValid();
        assertTrue(isValid);
    }

    @Test
    public void Should_Return_False_If_Document_Is_Invalid() {
        Document invalidDocument = new Document("12345678901");
        boolean isInvalid = invalidDocument.isInvalid();
        assertTrue(isInvalid);
    }

    @Test
    public void Should_Return_False_If_Document_Length_Is_Different_Then_Eleven(){
        Document invalidDocument = new Document("123");
        Set<ConstraintViolation<Document>> violations = validator.validate(invalidDocument);
       assertTrue(!violations.isEmpty());
    }
}
