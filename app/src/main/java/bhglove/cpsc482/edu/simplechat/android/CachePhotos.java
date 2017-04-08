package bhglove.cpsc482.edu.simplechat.android;

import android.graphics.Bitmap;
import android.util.LruCache;
import android.widget.ImageView;

/**
 * Utility class used to self photos into memory.
 * Created by Benjamin Glover on 6/29/2016.
 */
public class CachePhotos {
    private static CachePhotos self = null;
    private LruCache<String, Bitmap> mMemoryCache;
    private static final int maxMemory = (int) (Runtime.getRuntime().maxMemory() / 1024);

    private CachePhotos() {
        int cacheSize = maxMemory / 8;
        mMemoryCache = new LruCache<String, Bitmap>(cacheSize) {
            @Override
            protected int sizeOf(String key, Bitmap bitmap) {
                return bitmap.getByteCount() / 1024;
            }
        };
    }

    public static CachePhotos getInstance() {
        if (self == null)
            self = new CachePhotos();
        return self;
    }

    protected void addBitmapToMemoryCache(String key, Bitmap bitmap) {
        if (getBitmapFromCache(key) == null && bitmap != null) {
            mMemoryCache.put(key, bitmap);
        }
    }

    private Bitmap getBitmapFromCache(String key) {
        return mMemoryCache.get(key);
    }

    public Bitmap getBitmap(String url){
        return getBitmapFromCache(url);
    }

    public void loadBitmap(String url, ImageView imageView) {
        final Bitmap bitmap = getBitmapFromCache(url);
        if (bitmap != null) {
            imageView.setImageBitmap(bitmap);
        } else {
            if (url != null) {
                if (!url.isEmpty()) {
                    LoadPhoto dl = new LoadPhoto(imageView);
                    dl.execute(url);
                }
            }
        }
    }
}
