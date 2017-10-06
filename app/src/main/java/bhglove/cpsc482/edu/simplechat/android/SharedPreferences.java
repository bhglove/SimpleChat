package bhglove.cpsc482.edu.simplechat.android;

import android.content.Context;

import com.google.gson.Gson;

import bhglove.cpsc482.edu.simplechat.model.User;

/**
 * Created by Benjamin on 10/4/2017.
 */

public class SharedPreferences {

    private static final String PREFS = "SIMPLE_CHAT_PREFS";

    private static android.content.SharedPreferences getPrefs(Context context){
        return context.getSharedPreferences(PREFS, Context.MODE_PRIVATE);
    }

    public static void storeDefaultUser(Context context, User user){

        android.content.SharedPreferences.Editor editor = getPrefs(context).edit();
        Gson gson = new Gson();
        String json = gson.toJson(user);

        editor.putString("DefaultUser", json);
        editor.apply();
    }

    public static User getDefaultUser(Context context){
        String json = getPrefs(context).getString("DefaultUser", "");
        Gson gson = new Gson();

        return gson.fromJson(json, User.class);
    }
}
