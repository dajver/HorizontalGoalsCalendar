package com.project.piechartcallendarexample.ui.calendar.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.view.CapFirstLetterTextView;

import butterknife.BindColor;
import butterknife.BindString;
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

    @BindColor(R.color.colorPrimary)
    public int primaryColor;
    @BindColor(R.color.colorPrimaryDark)
    public int primaryDarcColor;
    @BindColor(R.color.colorGrayLight)
    public int colorGrayLight;
    @BindColor(R.color.enter_button_color)
    public int backgroundColor;

    @BindString(R.string.task_new_target_mon)
    public String monday;
    @BindString(R.string.task_new_target_thu)
    public String thusday;
    @BindString(R.string.task_new_target_wen)
    public String wednesday;
    @BindString(R.string.task_new_target_sut)
    public String thursdy;
    @BindString(R.string.task_new_target_fr)
    public String friday;
    @BindString(R.string.task_new_target_surth)
    public String suturday;
    @BindString(R.string.task_new_target_sun)
    public String sunday;

    public HeaderHolder(View view){
        ButterKnife.bind(this, view);
    }
}
