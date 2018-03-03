package alis.store.domain.ValueObjects;

public class Document {
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
