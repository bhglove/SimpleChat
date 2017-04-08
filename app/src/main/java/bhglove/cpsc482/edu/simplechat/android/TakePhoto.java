package bhglove.cpsc482.edu.simplechat.android;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import bhglove.cpsc482.edu.simplechat.R;

/**
 * This task is a sub-activity used to wrap the functionality of the Android intent ACTION_IMAGE_CAPTURE.
 * Readmore: https://developer.android.com/training/camera/photobasics.html
 *
 * This is done as a sub-activity, because a class must extend activities to execute intents.
 * Created by Benjamin Glover on 7/12/2016.
 */
public class TakePhoto extends Activity {
    public final static int REQUEST_PERMISSIONS = 0;
    public final static String RECEIVE_PHOTO = "IMAGE_FILE";
    private String mImagePath;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            int cameraPermission = ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA);
            int writePermission =  ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if ( (cameraPermission != PackageManager.PERMISSION_GRANTED) ||
                    (writePermission != PackageManager.PERMISSION_GRANTED)) {

                ActivityCompat.requestPermissions(TakePhoto.this,
                        new String[]{Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_PERMISSIONS);
            }


            if ( cameraPermission == PackageManager.PERMISSION_GRANTED &&
                    writePermission == PackageManager.PERMISSION_GRANTED) {
                takePicture();
            }
        } else {
            takePicture();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSIONS: {
                if (grantResults.length > 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED)
                    takePicture();
                else {
                    setResult(Activity.RESULT_CANCELED);
                    finish();
                }
                break;
            }
        }
    }

    private void takePicture() {
        File photoFile = null;
        try {
            photoFile = createImageFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (photoFile != null) {
            mImagePath = photoFile.getAbsolutePath();
            dispatchTakePictureIntent(photoFile);
        }
    }

    private void dispatchTakePictureIntent(File file) {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
            startActivityForResult(takePictureIntent, REQUEST_PERMISSIONS);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_PERMISSIONS && resultCode == Activity.RESULT_OK) {
            if (mImagePath != null) {
                Intent intent = new Intent();
                intent.putExtra(RECEIVE_PHOTO, mImagePath);

                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        }
        if (requestCode == REQUEST_PERMISSIONS && resultCode == RESULT_CANCELED) {
            setResult(Activity.RESULT_CANCELED);
            finish();
        }
    }

    String mCurrentPhotoPath;

    private File createImageFile() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss", Locale.getDefault()).format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );
        mCurrentPhotoPath = image.getAbsolutePath();
        return image;
    }

}
