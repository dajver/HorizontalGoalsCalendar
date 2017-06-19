package com.project.piechartcallendarexample.ui.calendar.view;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/19/17.
 */

public class CalendarDaysView extends LinearLayout {

    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.dayOfWeek)
    TextView dayOfWeek;

    public CalendarDaysView(Context context) {
        super(context);
        init(context);
    }

    public CalendarDaysView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarDaysView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_calendar_days_item, this);
        setOrientation(HORIZONTAL);
        ButterKnife.bind(this);
        setup();
    }

    private void setup() {

    }

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek.setText(dayOfWeek);
    }
}
