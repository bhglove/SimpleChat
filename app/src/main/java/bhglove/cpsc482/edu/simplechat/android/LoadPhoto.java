package bhglove.cpsc482.edu.simplechat.android;

import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.File;
import java.io.IOException;
import java.lang.ref.WeakReference;

/**
 * Created by Benjamin Glover on 6/28/2016.
 */
class LoadPhoto extends AsyncTask<String, Void, Bitmap> {
    private final WeakReference<ImageView> imageView;
    private CachePhotos cachePhotos;
    private int width;
    private int height;

    LoadPhoto(ImageView imageView) {
        this.imageView = new WeakReference<>(imageView);
        cachePhotos = CachePhotos.getInstance();
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap result = null;

        try {
           result = Photo.decodeFile(new File(params[0]), width, height, true);
         } catch (IOException e) {
             Log.e("Photo", "Image could not be found.");
         }

        cachePhotos.addBitmapToMemoryCache(String.valueOf(params[0]), result);
        return result;
    }

    @Override
    protected void onPreExecute() {
        this.imageView.get().setImageBitmap(null);
        width = this.imageView.get().getWidth();
        height = this.imageView.get().getHeight();
    }

    @Override
    protected void onPostExecute(Bitmap params) {
        if(params != null)
            this.imageView.get().setImageBitmap(params);
    }
}
