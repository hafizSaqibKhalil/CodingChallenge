package com.example.challange;

import android.os.Bundle;

import com.example.challange.base.BaseActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        replaceFragment(PhotosFragment.newInstance(), PhotosFragment.TAG);
    }
}
