package bhglove.cpsc482.edu.simplechat.service.callable;

/**
 * Created by Benjamin on 10/1/2017.
 */

public interface CallableInterface {
    public void onSuccess(String message);
    public void onError(String message);
    public void onCreate();
    public void onDestroy();
}
