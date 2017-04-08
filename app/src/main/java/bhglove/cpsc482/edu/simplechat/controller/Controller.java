package bhglove.cpsc482.edu.simplechat.controller;

import android.content.Context;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import bhglove.cpsc482.edu.simplechat.model.Conversation;
import bhglove.cpsc482.edu.simplechat.model.Message;
import bhglove.cpsc482.edu.simplechat.model.Database;
import bhglove.cpsc482.edu.simplechat.model.User;
import bhglove.cpsc482.edu.simplechat.network.GetMessages;
import bhglove.cpsc482.edu.simplechat.network.SendMessage;
import bhglove.cpsc482.edu.simplechat.view.ActivityCallback;

/**
 * Created by Benjamin on 10/22/16.
 */

public class Controller {
    //User -> Receiver; Message -> Messages sent and received by primary User
    private static Controller controller = null;
    private Database mDatabase;
    private User primaryUser;

    private Controller(Context context){
        mDatabase = new Database(context);
    }

    public static Controller getInstance(Context context){
        if(controller == null){
            controller = new Controller(context);
        }
        return controller;
    }

    public void updateConversations(final ActivityCallback callback){
        GetMessages getMessages = new GetMessages() {
            @Override
            protected void onComplete(ArrayList<Message> messages) {

                callback.onMessageUpdateComplete();
            }

            @Override
            protected void onError(String error) {
                callback.onError(error);
            }

        };
        getMessages.execute();
    }
    public ArrayList<Conversation> getConversationList(){

        return mDatabase.getLastConversation();
    }

    public ArrayList<Message> getMessages(User receiver){
        return mDatabase.getMessagesReceivedFromUser(receiver);
    }

    public void createUser(User user){
        mDatabase.insertUser(user);
    }

    public void editUser(User user){
        mDatabase.editUser(user);
    }

    public void createMessage(User receiver, String content, final ActivityCallback callback){
        Message message = new Message(primaryUser, receiver, content);
        mDatabase.insertMessage(message);

        SendMessage sendMessage = new SendMessage() {
            @Override
            public void onComplete(List<Message> messages) {
                for(Message item : messages) {
                    mDatabase.editMessage(item);
                }
                callback.onUserCreated();
            }
        };
        sendMessage.execute(message);
    }

}
