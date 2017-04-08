package bhglove.cpsc482.edu.simplechat.view;

import bhglove.cpsc482.edu.simplechat.model.User;

/**
 * Created by Benjamin on 10/23/16.
 */

public interface ActivityCallback {
    void onUserCreated();
    void onMessageUpdateComplete();
    void onError(String error);

}
