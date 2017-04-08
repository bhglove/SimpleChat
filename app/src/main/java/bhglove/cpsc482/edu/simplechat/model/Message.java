package bhglove.cpsc482.edu.simplechat.model;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by Benjamin on 10/22/16.
 */

public class Message {
    protected int id;
    private String date;
    private int sender;
    private int receiver;
    private String content;
    protected String lastSync;
    private boolean sent = false;

    public Message(User sender, User receiver, String content){
        this.sender = sender.id;
        this.receiver = receiver.id;
        this.content = content;
    }

    /**
     * Constructor that is only avaiable to the database class, so that the id or the date is not
     * mutable.
     * @param id
     * @param sender
     * @param receiver
     * @param content
     * @param date
     */
    protected Message(int id, User sender, User receiver, String content, String date){
        this(sender, receiver, content);
        this.id = id;
        this.date = date;
    }

    public int getSender(){
        return this.sender;
    }

    public int getReceiver(){
        return this.receiver;
    }

    public String getDate(){
        return this.date;
    }

    public String getContent(){
        return this.content;
    }

    protected void setSentStatus(boolean value){
        this.sent = value;
    }

    public boolean isSent(){
        return sent;
    }

    public boolean checkSent(String date){
        //Check that date vs your date
        return isSent();
    }
    public String checkInstance() {
        return "Message: My id is: " + this.id
                + "\n Sent from " + this.sender + " Received by " + this.receiver
                + "\n Content: " + this.content
                + "\n Date: " + this.date + "\n";
    }
}
