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
    
    @Override
    public String toString(){
        return Name.toString(); 
    }
}
