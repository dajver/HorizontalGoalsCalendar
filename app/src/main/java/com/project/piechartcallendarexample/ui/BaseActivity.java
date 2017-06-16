package com.project.piechartcallendarexample.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by gleb on 6/17/17.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getViewId());
    }

    public abstract int getViewId();
}
