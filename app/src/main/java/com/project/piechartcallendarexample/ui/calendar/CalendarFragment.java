package com.project.piechartcallendarexample.ui.calendar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.adapter.CalendarAdapter;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;

import java.util.ArrayList;

import butterknife.ButterKnife;

/**
 * Created by gleb on 6/15/17.
 */

public class CalendarFragment extends BaseCalendarFragment implements CalendarAdapter.OnClickCallback {

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        calendarAdapter = new CalendarAdapter(context);
        calendarAdapter
                .setItem(taskController.getAllTasks().size() > 0 ? taskController.getAllTasks() : new ArrayList<RealmTaskModel>())
                .setCalendarDated(showDatesInView())
                .setCalendar(current)
                .setDayOfWeek(getDayOfWeek(current));
        calendarAdapter.setOnClickListener(this);
        listView.setAdapter(calendarAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calendar, container, false);
        ButterKnife.bind(this, view);
        setHasOptionsMenu(true);
        return view;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        refreshAdapter(taskController.getAllTasks());
    }

    @Override
    public void onNextClick() {
        nextWeek();
    }

    @Override
    public void onPrevClick() {
        previousWeek();
    }

    @Override
    public void onDateClick(int dayCount) { }
}