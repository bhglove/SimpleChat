package bhglove.cpsc482.edu.simplechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 10/1/2017.
 */

public class Participant {
    @SerializedName("id")
    private int _id;
    @SerializedName("conversations")
    private int conversation;
    @SerializedName("user")
    private int user;

    public Participant(int _id, int conversation, int user) {
        this._id = _id;
        this.conversation = conversation;
        this.user = user;
    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public int getConversation() {
        return conversation;
    }

    public void setConversation(int conversation) {
        this.conversation = conversation;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }
}
