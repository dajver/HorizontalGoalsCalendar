package com.project.piechartcallendarexample.ui.calendar.adapter.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;

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

    @BindView(R.id.daysRow)
    public TableRow daysRow;

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
