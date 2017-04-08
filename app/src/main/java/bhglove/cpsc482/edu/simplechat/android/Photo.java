package bhglove.cpsc482.edu.simplechat.android;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by Benjamin Glover on 8/24/16.
 */
public class Photo {

    public static Bitmap correctOrientation(Bitmap bitmap, String filename) throws IOException {
        ExifInterface exifInterface = new ExifInterface(filename);
        int orientation = exifInterface.getAttributeInt(ExifInterface.TAG_ORIENTATION, ExifInterface.ORIENTATION_NORMAL);
        Matrix matrix = new Matrix();
        //ordered by constant 2 - 7
        switch (orientation){
            case ExifInterface.ORIENTATION_FLIP_HORIZONTAL:
                matrix.postScale(-1, 1);
                break;
            case ExifInterface.ORIENTATION_ROTATE_180:
                matrix.postRotate(180);
                break;
            case ExifInterface.ORIENTATION_FLIP_VERTICAL:
                matrix.postScale(1, -1);
                break;
            case ExifInterface.ORIENTATION_TRANSPOSE:
                break;
            case ExifInterface.ORIENTATION_ROTATE_90:
                matrix.postRotate(90);
                break;
            case ExifInterface.ORIENTATION_TRANSVERSE:
                break;
            case ExifInterface.ORIENTATION_ROTATE_270:
                matrix.postRotate(270);
                break;
            default:
                break;
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return bitmap;
    }

    // Decodes image and scales it to reduce memory consumption
    public static Bitmap decodeFile(File f, int imageWidth, int imageHeight, boolean compress) throws IOException {
        Bitmap bitmap = null;
        // Decode image size
        BitmapFactory.Options o = new BitmapFactory.Options();
        o.inJustDecodeBounds = true;
        //get the original file width and height
        bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, o);
        if(compress) {
            final int height = o.outHeight;
            final int width = o.outWidth;
            // Find the correct scale value. It should be the power of 2.
            int scale = 1;
            if (width > imageWidth || height > imageHeight) {
                // The new size we want to scale to
                final int REQUIRED_SIZE = 100;
                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                while (halfWidth / scale / 2 >= REQUIRED_SIZE &&
                        halfHeight / scale / 2 >= REQUIRED_SIZE) {
                    scale *= 2;
                }
            }
            // Decode with inSampleSize
            BitmapFactory.Options o2 = new BitmapFactory.Options();
            o2.inSampleSize = scale;
            bitmap = BitmapFactory.decodeStream(new FileInputStream(f), null, o2);
        }
        bitmap = Photo.correctOrientation(bitmap, f.getAbsolutePath());
        return bitmap;
    }
}
