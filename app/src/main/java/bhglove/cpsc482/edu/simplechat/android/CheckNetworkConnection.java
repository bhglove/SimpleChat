package bhglove.cpsc482.edu.simplechat.android;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Utility class used to determine if internet connect is available.
 *
 * Source:
 * http://androidopentutorials.com/android-image-slideshow-using-viewpager/
 *
 * Created by Benjamin Glover on 8/16/2016.
 */
public class CheckNetworkConnection {

    public static boolean isConnectionAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo netInfo = connectivityManager.getActiveNetworkInfo();
            if (netInfo != null && netInfo.isConnected()
                    && netInfo.isConnectedOrConnecting()
                    && netInfo.isAvailable()) {
                return true;
            }
        }
        return false;
    }
}
