package bhglove.cpsc482.edu.simplechat.adapter;

import android.content.Context;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import bhglove.cpsc482.edu.simplechat.R;
import bhglove.cpsc482.edu.simplechat.model.Message;

/**
 * Created by Benjamin on 10/22/16.
 */

public class MessageAdapter extends ArrayAdapter<Message> {
    private Context mContext;
    private ArrayList<Message> messages;


    public MessageAdapter(Context context, ArrayList<Message> messages) {
        super(context, R.layout.receiver_layout);
        mContext = context;
        this.messages = messages;
    }

    @Override
    public View getView(int position, View contentView, ViewGroup parent) {
        View view = contentView;
        Message message = messages.get(position);

        if (view == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            int layout;

            //TODO check shared prefs for primary user _id; if sender == user receiver_layout
            if(message.getSender() % 2 == 0)
                layout = R.layout.sender_layout;
            else
                layout = R.layout.receiver_layout;


            view = vi.inflate(layout, null);
        }

        TextView chat = (TextView) view.findViewById(R.id.chat_content);
        chat.setText(message.getMessage());
        return view;
    }

    @Override
    public int getCount() {
        return messages.size();
    }

    @Nullable
    @Override
    public Message getItem(int position) {
        return messages.get(position);
    }
}
