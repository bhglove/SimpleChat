package bhglove.cpsc482.edu.simplechat.service;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import bhglove.cpsc482.edu.simplechat.android.Alert;
import bhglove.cpsc482.edu.simplechat.model.User;
import bhglove.cpsc482.edu.simplechat.network.LoginAction;
import bhglove.cpsc482.edu.simplechat.service.callable.CallableInterface;
import bhglove.cpsc482.edu.simplechat.view.LoginActivity;

/**
 * Created by Benjamin on 10/1/2017.
 */

public class AuthenticationService {
    private static final String TAG = "AuthenticationService";
    private Context mContext;

    public AuthenticationService(Context context){
        this.mContext = context;
    }

    public User login(String idToken){

        LoginAction loginAction = new LoginAction() {
            ProgressDialog mDialog = null;

            public void onCreate() {
                mDialog = new ProgressDialog(mContext);
                mDialog.setTitle("Signing in user");
                mDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                mDialog.show();
            }

            @Override
            public void onDestroy() {
                mDialog.dismiss();
            }

            @Override
            public void onSuccess(String response) {
                if(response.matches(".*\\<[^>]+>.*")){
                    Log.d(TAG, "Got HTML back");
                }
                else {
                    Log.d(TAG, response);
                }
            }

            @Override
            public void onError(String message) {
                Toast.makeText(mContext, "Error :(", Toast.LENGTH_SHORT).show();
            }
        };

        loginAction.execute(idToken, "GOOGLE");
        String response = null;
        try {
            response = loginAction.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
            Alert.errorAlert(mContext, "Unable to create an user profile.");
        }

        return parseUser(response);
    }

    private User parseUser(String response) {
        User user = null;

        if(response != null){
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();
                user = gson.fromJson(response, User.class);
        }

        return user;
    }


    public void logout(){

    }
}
