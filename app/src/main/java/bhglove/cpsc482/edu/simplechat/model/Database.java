package bhglove.cpsc482.edu.simplechat.model;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Benjamin on 10/22/16.
 */

public class Database extends SQLiteOpenHelper{
    private static int userCount = 0;
    private static int messageCount = 0;

    private static final String DB_NAME = "SIMPLE_CHAT";
    private static final int DB_VERSION = 1;

    private static final String USER_TABLE = "USER";
    private static final String USER_ID = "USER_ID";
    private static final String USER_NAME = "NAME";
    private static final String USER_PHONE = "PHONE";

    private static final String MESSAGE_TABLE = "MESSAGE";
    private static final String MESSAGE_ID = "MESSAGE_ID";
    private static final String MESSAGE_CONTENT = "CONTENT";

    private static final String USER_MESSAGES_TABLE = "USER_MESSAGES";
    private static final String USER_MESSAGES_ID = "USER_MESSAGES_ID";
    private static final String USER_MESSAGES_SENDER = "SENDER";
    private static final String USER_MESSAGES_RECEIVER = "RECEIVER";
    private static final String USER_MESSAGES_MESSAGE = "MESSAGE";
    private static final String USER_MESSAGES_DATE = "DATE";
    private static final String USER_MESSAGES_SYNCED = "SYNCED";

    public Database(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase db) {
        String CREATE_USER_TABLE =
                "CREATE TABLE IF NOT EXISTS " + USER_TABLE + "("
                        + USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0, "
                        + USER_NAME + " TEXT, "
                        + USER_PHONE + " TEXT)";

        String CREATE_MESSAGE_TABLE =
                "CREATE TABLE IF NOT EXISTS " + MESSAGE_TABLE + "("
                        + MESSAGE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0, "
                        + MESSAGE_CONTENT + " TEXT)";

        String CREATE_USER_MESSAGES_TABLE =
                "CREATE TABLE IF NOT EXISTS " + USER_MESSAGES_TABLE + "("
                        + USER_MESSAGES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT DEFAULT 0, "
                        + USER_MESSAGES_SENDER + " INTEGER, "
                        + USER_MESSAGES_RECEIVER + " INTEGER, "
                        + USER_MESSAGES_MESSAGE + " INTEGER, "
                        + USER_MESSAGES_DATE + " TEXT,"
                        + USER_MESSAGES_SYNCED + " TEXT,"
                        + " FOREIGN KEY(" + USER_MESSAGES_SENDER + ") REFERENCES " + USER_TABLE + "(" + USER_ID + "),"
                        + " FOREIGN KEY(" + USER_MESSAGES_RECEIVER + ") REFERENCES " + USER_TABLE + "(" + USER_ID + "),"
                        + " FOREIGN KEY(" + USER_MESSAGES_MESSAGE + ") REFERENCES " + MESSAGE_TABLE + "(" + MESSAGE_ID + "),"
                        +")";
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase db, int i, int i1) {

    }

    public ArrayList<Message> getMessagesReceivedFromUser(User user){
        ArrayList<Message> messages = new ArrayList<>();
        User benji = new User("Benji", "8037071250");
        insertUser(benji);

        User erin = new User("Erin", "8031112222");
        insertUser(erin);

        Message message = new Message(benji, erin, "Your dog is so cute!");
        Message other = new Message(erin, benji, "Yeah he is cute");

        messages.add(message);
        messages.add(other);
        messages.add(new Message(benji, erin, "Lorem Ipsum is simply dummy text of the printing and type setting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s."));
        return messages;
    }


    public ArrayList<Conversation> getLastConversation(){
        //TODO get users either a message was sent to or received from
        ArrayList<Conversation> conversations = new ArrayList<>();
            User benji = new User("Benji", "8037071250");
            insertUser(benji);

            User erin = new User("Erin", "8031112222");
            insertUser(erin);

            Message message = new Message(benji, erin, "Your dog is so cute!");
            Message other = new Message(erin, benji, "Yeah he is cute");

            conversations.add(new Conversation(erin, message));
            conversations.add(new Conversation(benji, other));
        return conversations;
    }

    public void insertMessage(Message message){
       /* SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MESSAGE_CONTENT, message.getContent());

        message.id = (int) db.insert(MESSAGE_TABLE, null, values);
        values = new ContentValues();

        values.put(USER_MESSAGES_MESSAGE, message.id);
        values.put(USER_MESSAGES_SENDER, message.getSender());
        values.put(USER_MESSAGES_RECEIVER, message.getReceiver());
        values.put(USER_MESSAGES_DATE, message.getDate());

        db.insert(USER_MESSAGES_TABLE, null, values);*/
        message.id = messageCount++;
    }

    public void insertUser(User user){
        /*SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(USER_NAME, user.getName());
        values.put(USER_PHONE, user.getNumber());

        user.id = (int) db.insert(USER_TABLE, null, values);*/
        user.id = userCount++;
    }

    public void editUser(User user){

    }

    public void editMessage(Message message){

    }

    public void deleteMessage(Message message){

    }

    public void deleteUser(User user){

    }

}
