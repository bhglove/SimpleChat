package bhglove.cpsc482.edu.simplechat.network;

import android.util.ArrayMap;
import android.util.Log;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.KeyManagementException;

/**
 * Created by Benjamin on 10/1/2017.
 */

public class HTTPClient {
    private static final String BASE_URL = "http://simplechat.benjios.com/";
    private static final String TAG = "HTTPClient";
    private byte[] postData = null;
    private String response = null;
    private String mHTTPMethod = "GET";

    public HTTPClient(){

    }

    public String getResponse(){
        return response;
    }

    public void setData(ArrayMap<String, String> data){
       String postData = "";
        for(int i = 0; i < data.size(); i++){
            String key = data.keyAt(i);
            String value = data.valueAt(i);

            if(i > 0) postData += "&";
            postData += key + "=" + value;
       }

       Log.d(TAG, postData);

       this.postData = postData.getBytes();
    }

    public void setMethod(String method){
        this.mHTTPMethod = method;
    }

    public void send(String path) throws IOException, KeyManagementException {

            URL base = new URL(BASE_URL);
            URL url = new URL(base, path);
            Log.d(TAG, url.toString());
            // create url connection
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod(this.mHTTPMethod);
            conn.setRequestProperty("Connection", "Keep-Alive");
            conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            conn.setRequestProperty("charset", "utf-8");
            if(postData != null) {
                conn.setRequestProperty("Content-Length", Integer.toString(postData.length));
            }
            conn.setUseCaches(false);

           /* if(BASE_URL.contains("https")) {
                //add ssl certificate status
                SSLContext sc;
                sc = SSLContext.getInstance("TLS");
                sc.init(null, null, new java.security.SecureRandom());
                conn.setSSLSocketFactory(sc.getSocketFactory());
            }*/

            conn.connect();

            //Write variables to post body
            if(postData != null) {
                DataOutputStream wr = new DataOutputStream(conn.getOutputStream());
                wr.write(postData);
                wr.close();
            }
            //Read response
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder builder = new StringBuilder();
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                builder.append(line);
            }

            reader.close();

            //Set response
            response = builder.toString();
        }


}
