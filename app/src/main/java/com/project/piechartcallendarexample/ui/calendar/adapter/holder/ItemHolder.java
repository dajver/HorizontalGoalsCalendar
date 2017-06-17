package com.project.piechartcallendarexample.ui.calendar.adapter.holder;

import android.view.View;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.view.ProgressView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/15/17.
 */

public class ItemHolder {
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.days)
    public TextView days;

    @BindView(R.id.progresBar)
    public ProgressView progressView;
    @BindView(R.id.progresBar2)
    public ProgressView progressView2;
    @BindView(R.id.progresBar3)
    public ProgressView progressView3;
    @BindView(R.id.progresBar4)
    public ProgressView progressView4;
    @BindView(R.id.progresBar5)
    public ProgressView progressView5;
    @BindView(R.id.progresBar6)
    public ProgressView progressView6;
    @BindView(R.id.progresBar7)
    public ProgressView progressView7;

    @BindView(R.id.repeatFirst)
    public TextView repeatFirst;
    @BindView(R.id.repeatSecond)
    public TextView repeatSecond;
    @BindView(R.id.repeatThird)
    public TextView repeatThird;
    @BindView(R.id.repeatForth)
    public TextView repeatForth;
    @BindView(R.id.repeatfifth)
    public TextView repeatFifth;
    @BindView(R.id.repeatSixth)
    public TextView repeatSixth;
    @BindView(R.id.repeatSeventh)
    public TextView repeatSeventh;

    public ItemHolder(View view){
        ButterKnife.bind(this, view);
    }
}
