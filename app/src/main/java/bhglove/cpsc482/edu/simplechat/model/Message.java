package bhglove.cpsc482.edu.simplechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 10/22/16.
 */

public class Message {
    @SerializedName("id")
    protected int _id;
    @SerializedName("date")
    private String date;
    @SerializedName("sender")
    private int sender;
    @SerializedName("receiver")
    private int receiver;
    @SerializedName("message")
    private String message;
    protected String lastSync;
    private boolean sent = false;

    public Message(User sender, User receiver, String message){
        this.sender = sender.get_id();
        this.receiver = receiver.get_id();
        this.message = message;
    }

    /**
     * Constructor that is only avaiable to the database class, so that the _id or the date is not
     * mutable.
     * @param _id
     * @param sender
     * @param receiver
     * @param message
     * @param date
     */
    protected Message(int _id, User sender, User receiver, String message, String date){
        this(sender, receiver, message);
        this._id = _id;
        this.date = date;
    }

    public int get_id(){
        return this._id;
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

    public String getMessage(){
        return this.message;
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
        return "Message: My _id is: " + this._id
                + "\n Sent from " + this.sender + " Received by " + this.receiver
                + "\n Content: " + this.message
                + "\n Date: " + this.date + "\n";
    }
}
