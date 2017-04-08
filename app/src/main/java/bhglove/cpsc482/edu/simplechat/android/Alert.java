package bhglove.cpsc482.edu.simplechat.android;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.util.Log;

/**
 * Wrapper class used to reduce repetitive code needed to display an alert.
 *
 * Created by Benjamin Glover on 8/2/2016.
 */
public class Alert {
    private static AlertDialog.Builder dialog;


    public static void okayAlert(Context context, String title, String message){
        dialog = new AlertDialog.Builder(context);
        if(title != null) {
            dialog.setTitle(title);
        }
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static void okayAlert(Context context, String title, String message, Dialog.OnClickListener confirm){
        dialog = new AlertDialog.Builder(context);
        if(title != null) {
            dialog.setTitle(title);
        }
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Okay", confirm);
        dialog.show();
    }

    public static void confirmAlert(Context context, String title, String message, Dialog.OnClickListener confirm){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Okay", confirm);
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    public static boolean confirmYesNoAlert(Context context, String title, String message, Dialog.OnClickListener yesClick, Dialog.OnClickListener noClick){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setPositiveButton("Yes", yesClick);
        dialog.setNeutralButton("No", noClick);
        dialog.show();
        return false;
    }

    public static boolean confirmYesNoAlertForPhotos(Context context, String title, String message, Dialog.OnClickListener yesClick, Dialog.OnClickListener noClick){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setNeutralButton("High Quaility", yesClick);
        dialog.setPositiveButton("Low Quality", noClick);
        dialog.show();
        return false;
    }

    public static void errorAlert(Context context, String message){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle("Error");
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        Log.e("Error", "Error dialog shown: " + message);
        dialog.show();
    }

    public static void errorAlert(Context context, String type,  String message){
        dialog = new AlertDialog.Builder(context);
        dialog.setTitle(type + " Error");
        dialog.setMessage(message);
        dialog.setCancelable(false);
        dialog.setNegativeButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
