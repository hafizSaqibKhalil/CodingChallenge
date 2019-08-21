package com.example.challange;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;


import com.afollestad.materialdialogs.MaterialDialog;
import com.example.challange.adapters.PhotosAdapter;
import com.example.challange.base.BaseFragment;
import com.example.challange.databinding.ActiveScreenBinding;
import com.example.challange.network.ServiceGenerator;
import com.example.challange.network.response.ResponsePhotos;
import com.example.challange.offline.OfflineDb;
import com.example.challange.util.GeneralUtil;
import com.timehop.stickyheadersrecyclerview.StickyRecyclerHeadersDecoration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PhotosFragment extends BaseFragment {

    public static final String TAG = PhotosFragment.class.getSimpleName();

    private List<ResponsePhotos> listData = new ArrayList<>(0);
    private LinearLayoutManager mLayoutManager;
    private PhotosAdapter mAdapter;

    private ActiveScreenBinding binding;

    public static PhotosFragment newInstance() {

        Bundle args = new Bundle();

        PhotosFragment fragment = new PhotosFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        setLocalContext(container.getContext());
        if (binding == null) {
            binding = DataBindingUtil.inflate(inflater, R.layout.active_screen, container, false);
            binding.setF(this);


        }
        return binding.getRoot();
    }

   public void initializeAdapterView() {
        mLayoutManager = new LinearLayoutManager(getLocalContext());
        binding.rv.setLayoutManager(mLayoutManager);
        mAdapter = new PhotosAdapter(getActivity());
        binding.rv.setAdapter(mAdapter);
        binding.rv.addItemDecoration(new StickyRecyclerHeadersDecoration(mAdapter));

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        OfflineDb offlineDb = new OfflineDb(getLocalContext());
        listData = offlineDb.getSavingList();
        initializeAdapterView();
        if (listData.size() < 1) {
            callPhotosApi();
        } else {
            offlineResultsShown();

        }




    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void onPause() {

        super.onPause();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private void offlineResultsShown() {

        mAdapter.setList(listData);
    }

    private void callPhotosApi() {
        if (GeneralUtil.isNetworkAvailable(getLocalContext(), false)) {
            final MaterialDialog wait = GeneralUtil.getWait(getLocalContext(),"Data is Fetching.....") ;
            wait.show();


            Call<List<ResponsePhotos>> call = ServiceGenerator.getService().photos();


            call.enqueue(new Callback<List<ResponsePhotos>>() {
                @Override
                public void onResponse(Call<List<ResponsePhotos>> call, Response<List<ResponsePhotos>> response) {
                    wait.dismiss();
                    listData = response.body();
                    new AsyncCaller().execute();

                }

                @Override
                public void onFailure(Call<List<ResponsePhotos>> call, Throwable t) {
                    wait.dismiss();
                    Toast.makeText(getActivity(), t.getMessage(), Toast.LENGTH_SHORT).show();
                }
            });


        } else {


            //    getOfflineActiveLoadData();


        }
    }



    private class AsyncCaller extends AsyncTask<Void, Void, Void>
    {
        MaterialDialog progress = GeneralUtil.getWait(getLocalContext(),"Storing Data to Db....");
        public AsyncCaller( ){

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progress.show();

        }
        @Override
        protected Void doInBackground(Void... params) {
            try {
                for (int i = 0; i < listData.size(); i++) {
                    ResponsePhotos photos = listData.get(i);
                    long primatyiDd = System.currentTimeMillis();
                    primatyiDd = primatyiDd + 1;
                    photos.setId(primatyiDd);
                    OfflineDb offlineDb = new OfflineDb(getLocalContext());
                    offlineDb.savePhoto(photos);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            progress.dismiss();
            mAdapter.setList(listData);
        }

    }

}
