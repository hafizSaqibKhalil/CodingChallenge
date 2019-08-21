package com.example.challange.base;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.view.View;




public class BaseFragment extends Fragment implements View.OnClickListener {

    protected Context localContext = null;

    @Override
    public void onClick(View v) {

    }

    /**
     * @return the localContext
     */
    protected Context getLocalContext() {
        return localContext;
    }

    /**
     * @param localContext the localContext to set
     */
    protected void setLocalContext(Context localContext) {
        this.localContext = localContext;
    }

    public void launchActivity(Class<?> className) {
        Intent i = new Intent(getActivity(), className);
        startActivity(i);
        getActivity().finish();
    }
}
