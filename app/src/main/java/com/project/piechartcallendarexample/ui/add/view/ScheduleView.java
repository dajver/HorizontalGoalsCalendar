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

import java.util.List;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class ScheduleView extends LinearLayout implements View.OnClickListener {

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

    private boolean withoutDeadline;
    private int repeatValue;

    private Context context;

    public ScheduleView(Context context) {
        super(context);
        init(context);
    }

    public ScheduleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ScheduleView(Context context, AttributeSet attrs, int defStyleAttr) {
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
            case R.id.withoutDeadLine:
                onWithoutDeadlineChecked(view);
                break;
        }
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

    public String getDeadline() {
        return deadLine.getText().toString();
    }

    public int getRepeatValue() {
        return repeatValue;
    }

    public List<Boolean> getFixDaysList() {
        return weeksView.getFixDaysList();
    }

    public boolean isWithoutDeadline() {
        return withoutDeadline;
    }
}