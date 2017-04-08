package bhglove.cpsc482.edu.simplechat.network;

import android.os.AsyncTask;

import java.util.Collections;
import java.util.List;

import bhglove.cpsc482.edu.simplechat.model.Message;

/**
 * Created by Benjamin on 10/23/16.
 */

public abstract class SendMessage extends AsyncTask<Message, Void, Boolean> {
    private List<Message> messages;

    protected abstract void onComplete(List<Message> messages);

    protected SendMessage(){

    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean result) {
        super.onPostExecute(result);
        onComplete(this.messages);
    }

    @Override
    protected Boolean doInBackground(Message... messages) {
        Collections.addAll(this.messages, messages);


        return null;
    }
}
