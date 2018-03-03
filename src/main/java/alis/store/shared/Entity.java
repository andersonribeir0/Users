package alis.store.shared;

import java.util.UUID;

public abstract class Entity {
    private String Id;

    protected Entity() {
        this.Id = UUID.randomUUID().toString();
    }

    public String getId() {
        return Id;
    }
    
}
