package alis.store.shared.notification;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public abstract class Notifiable {
    
    private List<Notification> Notifications;
    private boolean isValid = isValid();
    
    protected Notifiable(){
        Notifications = new ArrayList<>();
    }
    
    private boolean isValid(){
        return Notifications.isEmpty();
    }
        
    public void AddNotification(String attribute, String message){
        Notifications.add(new Notification(attribute,message));
    }
    
    public void AddNotifications(List<Notification> notifications){
        Notifications.addAll(notifications);
    }
    
    public void AddNotifications(Notifiable item){
        AddNotifications(item.Notifications);
    }
    
}
