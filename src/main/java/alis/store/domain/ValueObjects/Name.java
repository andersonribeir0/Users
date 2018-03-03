package alis.store.domain.ValueObjects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Name {
    
    @NotNull(message = "First name cannot be null")
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 characters")
    private String FirstName;
    
    @NotNull(message = "Last name cannot be null")
    @Size(min = 2, max = 20, message = "Last name length must be between 3 and 20 characters")
    private String LastName;

    public Name(String FirstName, String LastName) {
        this.FirstName = FirstName;
        this.LastName = LastName;
    }
    
    public String getFirstName() {
        return FirstName;
    }

    public String getLastName() {
        return LastName;
    }

    @Override
    public String toString(){
        return FirstName + " " + LastName;
    }
} 
