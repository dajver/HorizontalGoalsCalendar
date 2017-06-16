package com.project.piechartcallendarexample.ui.calendar.holder;

import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    @BindView(R.id.daysCount)
    public TextView daysCount;
    @BindView(R.id.dayCountLayout)
    public RelativeLayout dayCountLayout;

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

    @BindView(R.id.monLayout)
    public LinearLayout monLayout;
    @BindView(R.id.thuLayout)
    public LinearLayout thuLayout;
    @BindView(R.id.wedLayout)
    public LinearLayout wedLayout;
    @BindView(R.id.thusLayout)
    public LinearLayout thusLayout;
    @BindView(R.id.frLayout)
    public LinearLayout frLayout;
    @BindView(R.id.sutLayout)
    public LinearLayout sutLayout;
    @BindView(R.id.sunLayout)
    public LinearLayout sunLayout;
    @BindView(R.id.nameLayout)
    public LinearLayout nameLayout;

    public ItemHolder(View view){
        ButterKnife.bind(this, view);
    }
}
