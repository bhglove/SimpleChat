package bhglove.cpsc482.edu.simplechat.adapter;

import android.content.Context;
import android.graphics.Color;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

import bhglove.cpsc482.edu.simplechat.R;
import bhglove.cpsc482.edu.simplechat.model.Conversation;

/**
 * Created by Benjamin on 10/23/16.
 */

public class ConversationAdapter extends ArrayAdapter<Conversation> {
    private Context mContext;
    private ArrayList<Conversation> conversations;

    public ConversationAdapter(Context context, ArrayList<Conversation> conversations) {
        super(context, R.layout.conversation_layout);
        this.mContext = context;
        this.conversations = new ArrayList<>();
        Log.d("ConversationAdapter", "Created");
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater vi = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = vi.inflate(R.layout.conversation_layout, null);
        }

        TextView name = (TextView) view.findViewById(R.id.name);
        TextView date = (TextView) view.findViewById(R.id.date);

        return view;
    }

    @Nullable
    @Override
    public Conversation getItem(int position) {
        return conversations.get(position);
    }

    @Override
    public int getCount() {
        return conversations.size();
    }
}
