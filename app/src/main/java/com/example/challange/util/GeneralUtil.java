package com.example.challange.util;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.afollestad.materialdialogs.MaterialDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.exceptions.RealmMigrationNeededException;


/**
 * Created by mac on 15/12/2016.
 */

public class GeneralUtil {

    public static final String TAG = "GeneralUtil";




    public static boolean isEmptyOrNullStr(final String pStr) {
        return pStr == null || pStr.length() < 1;
    }


    public static MaterialDialog getWait(Context context,String msg) {
        return new MaterialDialog.Builder(context)
                .content(msg)
                .autoDismiss(false)
                .cancelable(false)
                .progress(true, 0)
                .build();
    }




    public static boolean isNetworkAvailable(Context context) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return true;
            } else {

                   Toast.makeText(context, "Internet is not available",
                        Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            Log.e("isNetworkAvailable", "" + e);
        }
        return false;
    }


    public static boolean isNetworkAvailable(Context context, boolean isMessageShown) {
        try {
            ConnectivityManager connectivityManager = (ConnectivityManager) context
                    .getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = connectivityManager
                    .getActiveNetworkInfo();
            if (activeNetworkInfo != null) {
                return true;
            } else {


                return false;
            }
        } catch (Exception e) {
            Log.e("isNetworkAvailable", "" + e);
        }
        return false;
    }
    public static Realm getRealmInstanse() {


        RealmConfiguration config = new RealmConfiguration
                .Builder()
                .deleteRealmIfMigrationNeeded()
                .build();
        try {
            return Realm.getInstance(config);
        } catch (RealmMigrationNeededException e) {
            try {
                Realm.deleteRealm(config);
                //Realm file has been deleted.
                return Realm.getInstance(config);
            } catch (Exception ex) {
                throw ex;
                //No Realm file to remove.
            }
        }

    }



}
