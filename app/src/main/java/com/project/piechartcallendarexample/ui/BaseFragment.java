package com.project.piechartcallendarexample.ui;

import android.app.Activity;
import android.support.v4.app.Fragment;

/**
 * Created by gleb on 6/15/17.
 */

public class BaseFragment extends Fragment {

    public Activity context;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        context = activity;
    }
}