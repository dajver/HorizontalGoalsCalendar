package com.project.piechartcallendarexample.ui.calendar.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.view.CapFirstLetterTextView;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/15/17.
 */

public class HeaderHolder {
    @BindView(R.id.currentMonth)
    public CapFirstLetterTextView currentMonth;
    @BindView(R.id.prevMonth)
    public ImageView prevMonth;
    @BindView(R.id.nextMonth)
    public ImageView nextMonth;

    @BindView(R.id.mondayTw)
    public TextView mondayTw;
    @BindView(R.id.thusdayTw)
    public TextView thusdayTw;
    @BindView(R.id.wednasdayTw)
    public TextView wednasdayTw;
    @BindView(R.id.suthardayTw)
    public TextView thursday;
    @BindView(R.id.fridayTw)
    public TextView fridayTw;
    @BindView(R.id.sutardayTw)
    public TextView sutardayTw;
    @BindView(R.id.sundayTw)
    public TextView sundayTw;

    @BindView(R.id.firstDayOfWeek)
    public TextView firstDayOfWeek;
    @BindView(R.id.secondDayOfWeek)
    public TextView secondDayOfWeek;
    @BindView(R.id.thirdDayOfWeek)
    public TextView thirdDayOfWeek;
    @BindView(R.id.forthDayOfWeek)
    public TextView forthDayOfWeek;
    @BindView(R.id.fifthDayOfWeek)
    public TextView fifthDayOfWeek;
    @BindView(R.id.sixthDayOfWeek)
    public TextView sixthDayOfWeek;
    @BindView(R.id.seventhDayOfWeek)
    public TextView seventhDayOfWeek;

    @BindColor(R.color.colorPrimary)
    public int primaryColor;
    @BindColor(R.color.colorPrimaryDark)
    public int primaryDarcColor;
    @BindColor(R.color.colorGrayLight)
    public int colorGrayLight;
    @BindColor(R.color.enter_button_color)
    public int backgroundColor;

    public HeaderHolder(View view){
        ButterKnife.bind(this, view);
    }
}
