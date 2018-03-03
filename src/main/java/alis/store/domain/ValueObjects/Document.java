package alis.store.domain.ValueObjects;

import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

public class Document {
    
    @Size(min = 11, max = 11, message = "Document must has 11 characters")
    @Positive(message = "Only numbers are permitted")
    private String Number;
    
    public Document(String Number) {
        this.Number = Number;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String Number) {
        this.Number = Number;
    }

    @Override
    public String toString() {
        return Number;
    }
    
}
