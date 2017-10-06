package bhglove.cpsc482.edu.simplechat.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Benjamin on 10/23/16.
 */

public class Conversation {
    @SerializedName("_id")
    private int _id;
    @SerializedName("name")
    private String name;
}
