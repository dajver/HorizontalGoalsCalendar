package com.project.piechartcallendarexample.ui.add.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class SchletudeViews extends LinearLayout implements View.OnClickListener {

    @BindView(R.id.weeksView)
    WeeksView weeksView;
    @BindView(R.id.repeatSpinner)
    Spinner repeatSpinner;
    @BindView(R.id.deadLine)
    TextView deadLine;
    @BindView(R.id.withoutDeadLine)
    TextView withoutDeadLine;
    @BindView(R.id.schletudeQuestion)
    ImageView schletudeQuestion;

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

    private boolean withoutDeadline;
    private int repeatValue;

    private Context context;

    public SchletudeViews(Context context) {
        super(context);
        init(context);
    }

    public SchletudeViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SchletudeViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        this.context = context;
        inflate(context, R.layout.view_new_target_shletude, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);

        setupViews();
    }

    private void setupViews() {
        weeksView.getMonBtn().setOnClickListener(this);
        weeksView.getThuBtn().setOnClickListener(this);
        weeksView.getWedBtn().setOnClickListener(this);
        weeksView.getThurBtn().setOnClickListener(this);
        weeksView.getFrBtn().setOnClickListener(this);
        weeksView.getSutBtn().setOnClickListener(this);
        weeksView.getSunBtn().setOnClickListener(this);
        schletudeQuestion.setOnClickListener(this);
        withoutDeadLine.setOnClickListener(this);

        repeatValue = Integer.valueOf(getResources().getStringArray(R.array.task_repeats)[0]);
        setRepeatSpinnerAdapter();
    }

    public void setRepeatSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.task_repeats));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        repeatSpinner.setAdapter(adapter);
        repeatSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repeatValue = Integer.valueOf(getResources().getStringArray(R.array.task_repeats)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
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
            case R.id.withoutDeadLine:
                onWithoutDeadlineChecked(view);
                break;
        }
    }

    public void onWeekChecked(View v) {
        switch(v.getId()) {
            case R.id.monBtn:
                monday = selectDayOfWeek(v, monday);
                break;
            case R.id.thuBtn:
                tuesday = selectDayOfWeek(v, tuesday);
                break;
            case R.id.wedBtn:
                wednesday = selectDayOfWeek(v, wednesday);
                break;
            case R.id.thurBtn:
                thursday = selectDayOfWeek(v, thursday);
                break;
            case R.id.frBtn:
                friday = selectDayOfWeek(v, friday);
                break;
            case R.id.surthBtn:
                saturday = selectDayOfWeek(v, saturday);
                break;
            case R.id.sunBtn:
                sunday = selectDayOfWeek(v, sunday);
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

    public void onWithoutDeadlineChecked(View v) {
        if(v.getId() == R.id.withoutDeadLine) {
            if(!withoutDeadline) {
                ((TextView) v).setTextColor(blueColor);
                deadLine.setEnabled(false);
                withoutDeadline = true;
            } else {
                ((TextView) v).setTextColor(grayColor);
                deadLine.setEnabled(true);
                withoutDeadline = false;
            }
        }
    }

    public TextView getDeadline() {
        return deadLine;
    }

    public int getRepeatValue() {
        return repeatValue;
    }

    public boolean isMonday() {
        return monday;
    }

    public boolean isTuesday() {
        return tuesday;
    }

    public boolean isWednesday() {
        return wednesday;
    }

    public boolean isThursday() {
        return thursday;
    }

    public boolean isFriday() {
        return friday;
    }

    public boolean isSaturday() {
        return saturday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public boolean isWithoutDeadline() {
        return withoutDeadline;
    }
}