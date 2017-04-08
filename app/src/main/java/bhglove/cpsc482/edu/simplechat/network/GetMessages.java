package bhglove.cpsc482.edu.simplechat.network;

import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;

import bhglove.cpsc482.edu.simplechat.model.Message;
import bhglove.cpsc482.edu.simplechat.model.User;

/**
 * Created by Benjamin on 10/23/16.
 */

public abstract class GetMessages extends AsyncTask<User, Void, Boolean>{

    protected GetMessages(){

    }

    protected abstract void onComplete(ArrayList<Message> messages);
    protected abstract void onError(String error);

    private ArrayList<Message> convertToMessageArray(String json){
        ArrayList<Message> messages = new ArrayList<>();
        //Convert JSON TO Messages
        return messages;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected void onPostExecute(Boolean value) {
        super.onPostExecute(value);
    }

    @Override
    protected Boolean doInBackground(User... users) {
        byte[] postData = users[0].toString().getBytes();

        try {
            URL url = new URL("url");
            // create url connection
            HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
            conn.setUseCaches(false);

            //add ssl certificate status
            SSLContext sc;
            sc = SSLContext.getInstance("TLS");
            sc.init(null, null, new java.security.SecureRandom());
            conn.setSSLSocketFactory(sc.getSocketFactory());
            conn.connect();

            //Write variables to post body
            DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
            wr.write(postData);
            wr.close();

            //Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line);
            }

            reader.close();

            convertToMessageArray(builder.toString());
        } catch (IOException | NoSuchAlgorithmException | KeyManagementException e) {
            e.printStackTrace();
        }
        return null;
    }


}
