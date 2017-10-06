package bhglove.cpsc482.edu.simplechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 10/1/2017.
 */

public class Inbox {
    @SerializedName("_id")
    private int _id;
    @SerializedName("sender")
    private int sender;
    @SerializedName("user")
    private int user;
    @SerializedName("conversations")
    private int conversation;
    @SerializedName("message")
    private String message;

    public Inbox(int _id, int sender, int user, int conversation, String message) {
        this._id = _id;
        this.sender = sender;
        this.user = user;
        this.conversation = conversation;
        this.message = message;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getSender() {
        return sender;
    }

    public void setSender(int sender) {
        this.sender = sender;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
