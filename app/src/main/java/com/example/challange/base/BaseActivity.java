package com.example.challange.base;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



import com.example.challange.R;


public class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() < 1) {
            super.onBackPressed();
        } else {
            getSupportFragmentManager().popBackStack();
        }
    }

    protected void replaceFragment(Fragment targetFragment, String tagName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, targetFragment, tagName)
                .commit();
    }

    public void replaceFragmentF(Fragment targetFragment, String tagName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, targetFragment, tagName)
                .commit();
    }
    protected void replaceWithStackFragment(Fragment targetFragment, String tagName) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, targetFragment, tagName)
                .addToBackStack(tagName)
                .commit();
    }

    protected void addFragment(Fragment targetFragment, String tagName) {
        getSupportFragmentManager().beginTransaction()
                .add(R.id.fragment_container, targetFragment, tagName)
                .commit();
    }

    @Override
    public void onClick(View v) {

    }


}
