package com.project.piechartcallendarexample.ui.add.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class WeeksView extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.monBtn)
    TextView monBtn;
    @BindView(R.id.thuBtn)
    TextView thuBtn;
    @BindView(R.id.wedBtn)
    TextView wedBtn;
    @BindView(R.id.thurBtn)
    TextView thurBtn;
    @BindView(R.id.frBtn)
    TextView frBtn;
    @BindView(R.id.surthBtn)
    TextView sutBtn;
    @BindView(R.id.sunBtn)
    TextView sunBtn;

    @BindColor(R.color.colorPrimary)
    int blueColor;
    @BindColor(R.color.dark_gray_text)
    int grayColor;

    private boolean monday;
    private boolean tuesday;
    private boolean wednesday;
    private boolean thursday;
    private boolean friday;
    private boolean saturday;
    private boolean sunday;
    private List<Boolean> fixDaysList;

    public WeeksView(Context context) {
        super(context);
        init(context);
    }

    public WeeksView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeeksView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_tasks_weeks, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
        setupViews();
    }

    private void setupViews() {
        monBtn.setOnClickListener(this);
        thuBtn.setOnClickListener(this);
        wedBtn.setOnClickListener(this);
        thurBtn.setOnClickListener(this);
        frBtn.setOnClickListener(this);
        sutBtn.setOnClickListener(this);
        sunBtn.setOnClickListener(this);

        fixDaysList = new ArrayList<>(6);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.monBtn:
            case R.id.thuBtn:
            case R.id.wedBtn:
            case R.id.thurBtn:
            case R.id.frBtn:
            case R.id.surthBtn:
            case R.id.sunBtn:
                onWeekChecked(view);
                break;
        }
    }

    public void onWeekChecked(View v) {
        switch(v.getId()) {
            case R.id.monBtn:
                monday = selectDayOfWeek(v, monday);
                fixDaysList.add(monday);
                break;
            case R.id.thuBtn:
                tuesday = selectDayOfWeek(v, tuesday);
                fixDaysList.add(tuesday);
                break;
            case R.id.wedBtn:
                wednesday = selectDayOfWeek(v, wednesday);
                fixDaysList.add(wednesday);
                break;
            case R.id.thurBtn:
                thursday = selectDayOfWeek(v, thursday);
                fixDaysList.add(thursday);
                break;
            case R.id.frBtn:
                friday = selectDayOfWeek(v, friday);
                fixDaysList.add(friday);
                break;
            case R.id.surthBtn:
                saturday = selectDayOfWeek(v, saturday);
                fixDaysList.add(saturday);
                break;
            case R.id.sunBtn:
                sunday = selectDayOfWeek(v, sunday);
                fixDaysList.add(sunday);
                break;
        }
    }

    private boolean selectDayOfWeek(View v, boolean day) {
        if(!day) {
            ((TextView) v).setTextColor(blueColor);
            day = true;
        } else {
            ((TextView) v).setTextColor(grayColor);
            day = false;
        }
        return day;
    }

    public List<Boolean> getFixDaysList() {
        fixDaysList.add(0 ,monday);
        fixDaysList.add(1, tuesday);
        fixDaysList.add(2, wednesday);
        fixDaysList.add(3, thursday);
        fixDaysList.add(4, friday);
        fixDaysList.add(5, saturday);
        fixDaysList.add(6, sunday);
        return fixDaysList;
    }
}