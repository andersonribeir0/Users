
package alis.store.shared.notification;

public class Notification {
    
    private String Attribute;
    private String Message;
    
    public Notification(String attribute, String message){
        this.Attribute = attribute;
        this.Message = message;
    }

    public String getAttribute() {
        return Attribute;
    }

    public String getMessage() {
        return Message;
    }
    
    
}
