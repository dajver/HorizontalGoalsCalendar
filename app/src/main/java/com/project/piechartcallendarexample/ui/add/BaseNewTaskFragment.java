package com.project.piechartcallendarexample.ui.add;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.BaseFragment;
import com.project.piechartcallendarexample.ui.add.view.SchletudeViews;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import butterknife.BindColor;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class BaseNewTaskFragment extends BaseFragment {

    public static final int START_DATE = 0;

    public boolean monday;
    public boolean tuesday;
    public boolean wednesday;
    public boolean thursday;
    public boolean friday;
    public boolean saturday;
    public boolean sunday;

    public boolean withoutDeadline;

    public int repeatValue;

    @BindView(R.id.schletudeView)
    public SchletudeViews schletudeViews;
    @BindView(R.id.target)
    public EditText target;

    @BindColor(R.color.colorPrimary)
    int blueColor;
    @BindColor(R.color.dark_gray_text)
    int grayColor;

    public TaskController taskController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskController = new TaskController(context);
        repeatValue = Integer.valueOf(getResources().getStringArray(R.array.task_repeats)[0]);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_add_task, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    public void setRepeatSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.task_repeats));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schletudeViews.getRepeatSpinner().setAdapter(adapter);
        schletudeViews.getRepeatSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                repeatValue = Integer.valueOf(getResources().getStringArray(R.array.task_repeats)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
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
                schletudeViews.getDeadline().setEnabled(false);
                withoutDeadline = true;
            } else {
                ((TextView) v).setTextColor(grayColor);
                schletudeViews.getDeadline().setEnabled(true);
                withoutDeadline = false;
            }
        }
    }

    public String getDate(int daysCount) {
        Time time = new Time();
        Calendar now = Calendar.getInstance();
        if(daysCount == START_DATE) {
            time.set(now.get(Calendar.DATE), now.get(Calendar.MONTH), now.get(Calendar.YEAR));
        } else {
            now.add(Calendar.DATE, daysCount);
            time.set(now.get(Calendar.DATE), now.get(Calendar.MONTH), now.get(Calendar.YEAR));
        }
        return time.format("%d %m %Y");
    }

    public int daysBetweenDates(int daysCount) {
        DateTimeFormatter formatter = DateTimeFormat.forPattern("dd MM yyyy");
        DateTime d1 = formatter.parseDateTime(getDate(START_DATE));
        DateTime d2 = formatter.parseDateTime(getDate(daysCount));
        Duration duration = new Duration(d1, d2);
        int days = (int)duration.getStandardDays();
        return days;
    }
}