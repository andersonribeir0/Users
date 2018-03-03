package alis.store.shared.notification;

import java.util.List;

public abstract class Notifiable {
    private List<Notification> Notification;
    
    public boolean isValid(){
        return Notification.isEmpty();
    }
    
    public void AddNotification(String object, String message){
        
    }
}
