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

public class CalendarProgressView extends LinearLayout {

    @BindView(R.id.progresBar)
    ProgressView progresBar;
    @BindView(R.id.text)
    TextView text;

    public CalendarProgressView(Context context) {
        super(context);
        init(context);
    }

    public CalendarProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public CalendarProgressView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_calendar_progress_item, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
        setup();
    }

    private void setup() {

    }

    public ProgressView getProgressView() {
        return progresBar;
    }

    public TextView getText() {
        return text;
    }
}
