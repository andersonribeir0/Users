package alis.store.domain.entities;

import alis.store.domain.valueObjects.Address;
import alis.store.domain.valueObjects.Email;
import alis.store.domain.valueObjects.Name;

import alis.store.domain.valueObjects.Document;
import alis.store.shared.Entity;
import alis.store.domain.enums.EType;

public class User extends Entity{
    
    private Name Name;
    private Address Address;
    private Email Email;
    private Document Document;
    private EType Type;
    private String Password;

    public User(String Password, Name Name, Address Address, Email Email, Document Document, EType Type) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Document = Document;
        this.Type = Type;
        this.Password = Password;
    }

    public User(Name Name, Address Address, Email Email, Document Document, EType Type) {
        this.Name = Name;
        this.Address = Address;
        this.Email = Email;
        this.Document = Document;
        this.Type = Type;
    }

    public String getPassword() {
        return Password;
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
