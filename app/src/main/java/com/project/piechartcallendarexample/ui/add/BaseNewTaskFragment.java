package com.project.piechartcallendarexample.ui.add;

import android.os.Bundle;
import android.text.format.Time;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.BaseFragment;
import com.project.piechartcallendarexample.ui.add.view.ImpactionViews;
import com.project.piechartcallendarexample.ui.add.view.SchletudeViews;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class BaseNewTaskFragment extends BaseFragment {

    public static final int PICK_IMAGE = 223;
    public static final int PICK_FILE_RESULT_CODE = 333;
    public static final int START_DATE = 0;

    public static final int SCHLETUDE_TYPE_FIXED = 0;
    public static final int SCHLETUDE_TYPE_FLOAT = 1;

    public boolean monday;
    public boolean thuesday;
    public boolean wednesdey;
    public boolean thursday;
    public boolean friday;
    public boolean suthurday;
    public boolean sunday;

    public boolean withoutDeadline;

    public int onceWeekOrMonth;
    public int repeatValue;
    public int quanlityValue;
    public View view;;

    @BindView(R.id.notifyTogle)
    public ToggleButton notifyToogle;
    @BindView(R.id.timerText)
    public TextView timerText;
    @BindView(R.id.schletudeView)
    public SchletudeViews schletudeViews;
    @BindView(R.id.impactionView)
    public ImpactionViews impactionViews;
    @BindView(R.id.target)
    public EditText target;
    @BindView(R.id.descriptioEditText)
    public EditText descriptioEditText;
    @BindView(R.id.mainPhotoPath)
    public TextView mainPhotoPath;
    @BindView(R.id.addFilesImage)
    public ImageView addFilesImage;
    @BindView(R.id.addFilesTextView)
    public TextView addFilesTextView;
    @BindView(R.id.descriptionQuestion)
    public ImageView descriptionQuestion;
    @BindView(R.id.notificationQuestion)
    public ImageView notificationQuestion;
    @BindView(R.id.attachQuestion)
    public ImageView attachQuestion;

    public TaskController taskController;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskController = new TaskController(context);
        repeatValue = Integer.valueOf(getResources().getStringArray(R.array.task_repeats)[0]);
        onceWeekOrMonth = 0;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_add_task, container, false);
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

    public void setQuanlitySpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.task_quanlity));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schletudeViews.getQuanlitySpinner().setAdapter(adapter);
        schletudeViews.getQuanlitySpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                quanlityValue = Integer.valueOf(getResources().getStringArray(R.array.task_quanlity)[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) {}
        });
    }

    public void setFloatSpinnerAdapter() {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, getResources().getStringArray(R.array.task_float));
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        schletudeViews.getEachSpinner().setAdapter(adapter);
        schletudeViews.getEachSpinner().setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                onceWeekOrMonth = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> arg0) { }
        });
    }

    public void onWeekChecked(View v) {
        switch(v.getId()) {
            case R.id.monBtn:
                if(!monday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    monday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    monday = false;
                }
                break;
            case R.id.thuBtn:
                if(!thuesday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    thuesday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    thuesday = false;
                }
                break;
            case R.id.wedBtn:
                if(!wednesdey) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    wednesdey = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    wednesdey = false;
                }
                break;
            case R.id.thurBtn:
                if(!thursday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    thursday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    thursday = false;
                }
                break;
            case R.id.frBtn:
                if(!friday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    friday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    friday = false;
                }
                break;
            case R.id.surthBtn:
                if(!suthurday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    suthurday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    suthurday = false;
                }
                break;
            case R.id.sunBtn:
                if(!sunday) {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                    sunday = true;
                } else {
                    ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
                    sunday = false;
                }
                break;
        }
    }

    public void onWithoutDeadlineChecked(View v) {
        if(v.getId() == R.id.withoutDeadLine) {
            if(!withoutDeadline) {
                ((TextView) v).setTextColor(getResources().getColor(R.color.colorPrimary));
                schletudeViews.getDeadline().setEnabled(false);
                withoutDeadline = true;
            } else {
                ((TextView) v).setTextColor(getResources().getColor(R.color.dark_gray_text));
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