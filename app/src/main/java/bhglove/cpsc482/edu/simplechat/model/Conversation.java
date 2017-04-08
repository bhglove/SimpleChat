package bhglove.cpsc482.edu.simplechat.model;

/**
 * Created by Benjamin on 10/23/16.
 */

public class Conversation {
    private User receiver;
    private Message message;

    public Conversation(User receiver, Message message){
        this.receiver = receiver;
        this.message = message;
    }
    public User getUser(){
        return this.receiver;
    }
    public String getName(){
        return this.receiver.getName();
    }

    public String getMessage(){
        return this.message.getContent();
    }

    public String getDate(){
        return this.message.getDate();
    }

}
