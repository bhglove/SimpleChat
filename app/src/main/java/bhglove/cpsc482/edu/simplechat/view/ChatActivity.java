package bhglove.cpsc482.edu.simplechat.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import bhglove.cpsc482.edu.simplechat.R;
import bhglove.cpsc482.edu.simplechat.adapter.MessageAdapter;
import bhglove.cpsc482.edu.simplechat.model.User;

public class ChatActivity extends AppCompatActivity {
    private ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        mListView = (ListView) findViewById(R.id.conversation_list);
        User user = (User) getIntent().getExtras().get("User");

        if(user != null){
            //Controller controller = Controller.getInstance(getApplicationContext());
            //MessageAdapter adapter = new MessageAdapter(getApplicationContext(), controller.getMessages(user));
            //mListView.setAdapter(adapter);
            Toast.makeText(getApplicationContext(), "Chat Activity", Toast.LENGTH_SHORT).show();
        }
    }
}
