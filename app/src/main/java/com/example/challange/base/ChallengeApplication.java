package com.example.challange.base;

import android.app.Application;
import android.support.v7.app.AppCompatDelegate;
import android.widget.Toast;


import java.util.HashMap;

import io.realm.Realm;
import io.realm.RealmConfiguration;



/**
 * Created by mac on 10/04/2017.
 */
public class ChallengeApplication extends Application {

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Realm.init(this);
        RealmConfiguration config = new RealmConfiguration.Builder()
                .name("challenge.realm")
                .schemaVersion(0)
                .build();
        Realm.setDefaultConfiguration(config);



    //    JobManager.create(this).addJobCreator(new DemoJobCreator());


    }


}
