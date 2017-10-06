package bhglove.cpsc482.edu.simplechat.network;

import android.os.AsyncTask;
import android.util.ArrayMap;

import java.io.IOException;
import java.security.KeyManagementException;

import bhglove.cpsc482.edu.simplechat.service.callable.CallableInterface;

/**
 * Created by Benjamin on 10/1/2017.
 */

public abstract class LoginAction extends AsyncTask<String, Void, String> implements CallableInterface {
    private static final String TAG = "LoginAction";
    private String response = null;
    // callable to view
    public abstract void onCreate();
    public abstract void onDestroy();
    public abstract void onSuccess(String message);
    public abstract void onError(String message);


    public String getResponse(){
        return this.response;
    }

    @Override
    protected void onPreExecute() {
        onCreate();
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(String aBoolean) {
        onDestroy();

        if(aBoolean != null){
            onSuccess(this.response);
        }

        super.onPostExecute(aBoolean);
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected void onCancelled(String aBoolean) {
        super.onCancelled(aBoolean);
    }

    @Override
    protected String doInBackground(String... strings) {

        try {
            HTTPClient client = new HTTPClient();
            //Prepare and set parameters
            ArrayMap<String, String> data = new ArrayMap<String, String>();
            data.put("token", strings[0]);
            data.put("type", strings[1]);
            client.setData(data);

            //Set up connection
            client.setMethod("POST");
            client.send("/api/login");

            //Get response
            String response = client.getResponse();

            if(response != null){
                this.response = response;
            }
        } catch (KeyManagementException | IOException e) {
            onError("hmm something happened");
        }

        return this.response;
    }
}
