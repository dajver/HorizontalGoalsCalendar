package com.project.piechartcallendarexample.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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

public class BaseCalendarFragment extends BaseFragment {

    @BindView(R.id.listView)
    public ListView listView;

    public static final int RESULT = 256;

    public TaskController taskController;
    public CalendarAdapter calendarAdapter;
    public static final String[] dayOfWeeksArray = { "ПН", "ВТ", "СР", "ЧТ", "ПТ", "СБ", "ВС" };
    public Calendar current;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskController = new TaskController(context);
        current = Calendar.getInstance();
        current.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
    }

    public String getDayOfWeek(Calendar calendar) {
        return new SimpleDateFormat("EE").format(calendar.getTime());
    }

    public void nextWeek() {
        current.add(Calendar.DATE, 0);
        refreshAdapter();
    }

    public void previousWeek() {
        current.add(Calendar.DATE, -14);
        refreshAdapter();
    }

    public void refreshAdapter() {
        calendarAdapter.setCalendar(current).setCalendarDated(showDatesInView());
        listView.setAdapter(calendarAdapter);
    }

    public void refreshAdapter(ArrayList<RealmTaskModel> model) {
        calendarAdapter.setCalendar(current).setItem(model);
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
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_calendar, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                startActivityForResult(new Intent(context, NewTaskActivity.class), RESULT);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}