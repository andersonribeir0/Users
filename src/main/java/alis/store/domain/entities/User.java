package alis.store.domain.entities;

import alis.store.domain.ValueObjects.Address;
import alis.store.domain.ValueObjects.Email;
import alis.store.domain.ValueObjects.Name;

import alis.store.domain.ValueObjects.Document;
import alis.store.shared.enums.EType;

public class User {
    
    private Name Name;
    private Address Address;
    private Email Email;
    private Document Document;
    private EType Type;

    public User(Name Name, Address Address, Email Email, Document Document, EType Type) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Document = Document;
        this.Type = Type;
    }
    
    public Name getName() {
        return Name;
    }

    public Address getAddress() {
        return Address;
    }

    public Email getEmail() {
        return Email;
    }

    public Document getDocument() {
        return Document;
    }

    public EType getType() {
        return Type;
    }
    
    @Override
    public String toString(){
        return Name.toString(); 
    }

}
