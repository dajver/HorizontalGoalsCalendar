package com.project.piechartcallendarexample.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.BaseFragment;
import com.project.piechartcallendarexample.ui.add.NewTaskActivity;
import com.project.piechartcallendarexample.ui.calendar.adapter.CalendarAdapter;
import com.project.piechartcallendarexample.ui.calendar.adapter.model.CalendarModel;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import butterknife.BindView;

/**
 * Created by gleb on 6/15/17.
 */

public class CalendarFragment extends BaseFragment implements CalendarAdapter.OnClickCallback {

    public static final int REQUEST_RESULT = 256;
    public static final String[] dayOfWeeksArray = { "MON", "THU", "WED", "THU", "FRI", "SAT", "SUN" };

    @BindView(R.id.listView)
    ListView listView;

    public TaskController taskController;
    public CalendarAdapter calendarAdapter;
    public Calendar current;

    @Override
    public int getViewId() {
        return R.layout.fragment_calendar;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskController = new TaskController(context);
        current = Calendar.getInstance();
        current.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarAdapter = new CalendarAdapter(context);
        calendarAdapter.setItem(taskController.getAllTasks().size() > 0 ? taskController.getAllTasks() : new ArrayList<RealmTaskModel>());
        calendarAdapter.setCalendarDated(showDatesInView());
        calendarAdapter.setCalendar(current);
        calendarAdapter.setOnClickListener(this);
        listView.setAdapter(calendarAdapter);
    }

    public String getDayOfWeek(Calendar calendar) {
        return new SimpleDateFormat("EE").format(calendar.getTime());
    }

    public void refreshAdapter() {
        calendarAdapter.setCalendar(current);
        calendarAdapter.setCalendarDated(showDatesInView());
        listView.setAdapter(calendarAdapter);
    }

    public ArrayList<CalendarModel> showDatesInView() {
        ArrayList<CalendarModel> dates = new ArrayList<>();
        for(int i = 0; i < dayOfWeeksArray.length; i++) {
            CalendarModel model = new CalendarModel();
            model.setDay(current.get(Calendar.DATE));
            model.setMonth(current.get(Calendar.MONTH));
            model.setYear(current.get(Calendar.YEAR));
            model.setDayOfWeek(getDayOfWeek(current));
            current.add(Calendar.DATE, 1);
            dates.add(model);
        }
        return dates;
    }

    @Override
    public void onNextClick() {
        current.add(Calendar.DATE, 0);
        refreshAdapter();
    }

    @Override
    public void onPrevClick() {
        current.add(Calendar.DATE, -14);
        refreshAdapter();
    }

    @Override
    public void onDateClick(int dayCount) { }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(context, NewTaskActivity.class), REQUEST_RESULT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        calendarAdapter.setCalendar(current);
        calendarAdapter.setItem(taskController.getAllTasks());
        listView.setAdapter(calendarAdapter);
    }
}