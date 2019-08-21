package com.example.challange.offline;

import android.content.Context;

import com.example.challange.network.response.ResponsePhotos;
import com.example.challange.util.GeneralUtil;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;


public class OfflineDb {

    private Context context;
    public OfflineDb(Context c){

        this.context = c;


    }

    /*-----------------------Start Save and get Active Loads-----------------------------*/
    public void savePhoto(ResponsePhotos offlineActiveLoad){


        Realm realm = GeneralUtil.getRealmInstanse();
        realm.beginTransaction();
        realm.copyToRealm(offlineActiveLoad);

        realm.commitTransaction();

    }

    public  List<ResponsePhotos>  getSavingList(){

        List<ResponsePhotos> detailList = new ArrayList<>(0);

        Realm realmDB = GeneralUtil.getRealmInstanse();
        RealmResults<ResponsePhotos> list =realmDB.where(ResponsePhotos.class).findAll();
        if(list.size()>0) {
            detailList = list;
        }

        return detailList;
    }









}
