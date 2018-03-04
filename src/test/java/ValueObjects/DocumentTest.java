package ValueObjects;

import alis.store.domain.valueObjects.Document;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;

public class DocumentTest {

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

}
