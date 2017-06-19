package com.project.piechartcallendarexample.ui.calendar.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.adapter.holder.HeaderHolder;
import com.project.piechartcallendarexample.ui.calendar.adapter.holder.ItemHolder;
import com.project.piechartcallendarexample.ui.calendar.adapter.model.CalendarModel;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;
import com.project.piechartcallendarexample.ui.calendar.view.CalendarDaysView;
import com.project.piechartcallendarexample.ui.calendar.view.CalendarProgressView;
import com.project.piechartcallendarexample.ui.calendar.view.ProgressView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by gleb on 6/15/17.
 */

public class CalendarAdapter extends BaseAdapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;
    private static final String DATE_TEMPLATE = "MMMM \nyyyy";

    private HeaderHolder headHolder;
    private ItemHolder itemHolder;

    private TaskController taskController;
    private OnClickCallback onClickCallback;
    private DateFormat dateFormatter = new DateFormat();

    private int datePosition = 0;

    private Context context;
    private Calendar calendar;

    private ArrayList<RealmTaskModel> listData = new ArrayList<>();
    private ArrayList<CalendarModel> getDatesInView;

    public CalendarAdapter(Context context) {
        this.context = context;
        taskController = new TaskController(context);
    }

    public void setItem(ArrayList<RealmTaskModel> articleResultModels) {
        this.listData = articleResultModels;
        notifyDataSetChanged();
    }

    public void setCalendarDated(ArrayList<CalendarModel> calendarDated) {
        this.getDatesInView = calendarDated;
        notifyDataSetChanged();
    }

    public void setCalendar(Calendar calendar) {
        this.calendar = calendar;
        notifyDataSetChanged();
    }

    @Override
    public int getItemViewType(int position) {
        if(position == 0)
            return TYPE_HEAD;
        else
            return TYPE_ITEM;
    }

    @Override
    public int getViewTypeCount() {
        return super.getViewTypeCount() + 1;
    }

    @Override
    public int getCount() {
        return listData.size() + 1;
    }

    @Override
    public RealmTaskModel getItem(int position) {
        if(position == 0) return null;
        return listData.size() > 0 ? listData.get(position - 1) : new RealmTaskModel();
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = ((Activity) context).getLayoutInflater();
        int type = getItemViewType(position);
        TableRow.LayoutParams rowParams = new TableRow.LayoutParams(TableRow.LayoutParams.MATCH_PARENT, TableRow.LayoutParams.WRAP_CONTENT, 0.1f);
        TableRow tableRow = new TableRow(context);
        tableRow.setOrientation(TableLayout.HORIZONTAL);
        switch(type) {
            case TYPE_HEAD:
                convertView = inflater.inflate(R.layout.item_calendar_days, parent, false);
                headHolder = new HeaderHolder(convertView);

                headHolder.currentMonth.setFirstCupText(dateFormatter.format(DATE_TEMPLATE, calendar.getTime()));

                for(int i = 0; i < 7; i++) {
                    CalendarDaysView daysView = new CalendarDaysView(context);
                    daysView.setDate(String.valueOf(getDatesInView.get(i).getDay()));
                    daysView.setDayOfWeek(getDatesInView.get(i).getDayOfWeek());
                    tableRow.addView(daysView, rowParams);
                }
                headHolder.daysRow.addView(tableRow);

                headHolder.nextMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        datePosition += 7;
                        onClickCallback.onNextClick();
                    }
                });
                headHolder.prevMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (datePosition > 0)
                            datePosition -= 7;
                        onClickCallback.onPrevClick();
                    }
                });
                headHolder.currentMonth.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onClickCallback.onDateClick(datePosition);
                    }
                });
                break;
            case TYPE_ITEM:
                convertView = inflater.inflate(R.layout.item_calendar_progress, parent, false);
                itemHolder = new ItemHolder(convertView);

                final RealmTaskModel model = getItem(position);
                itemHolder.title.setText(model.getTitle());
                itemHolder.days.setText(String.format(context.getString(R.string.task_details_times_day), model.getCountRepeats(), model.getCountDays()));

                for(int numOfDaysInc = 0; numOfDaysInc < 7; numOfDaysInc++) {
                    CalendarProgressView progressView = new CalendarProgressView(context);
                    for (int daysInc = 0; daysInc < (7 + datePosition); daysInc++) {
                        if (compareDates(getFullDate(numOfDaysInc), getRealmDate(position, daysInc))) {
                            setRepeatsInfo(progressView.getText(), model, daysInc, model.getFixDaysList().get(numOfDaysInc).isFixDay());
                            onProgressClick(progressView.getProgressView(), model, daysInc, model.getFixDaysList().get(numOfDaysInc).isFixDay(), position);
                        }
                    }
                    tableRow.addView(progressView, rowParams);
                }
                itemHolder.progressRow.addView(tableRow);
                break;
        }
        return convertView;
    }

    private void onProgressClick(ProgressView progressView, final RealmTaskModel model, final int daysInc, boolean isFixed, final int position) {
        if(isFixed) {
            progressView.setValue(model.getRealmTaskHistoryModels().get(daysInc).getProgress());
            progressView.setProgressMaximum(model.getCountRepeats());
            progressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int progress = model.getRealmTaskHistoryModels().get(daysInc).getProgress() + 1;
                    int id = model.getRealmTaskHistoryModels().get(daysInc).getId();
                    if (progress <= model.getCountRepeats())
                        setProgress(progress, position, daysInc, id);
                    else
                        setProgress(0, position, daysInc, id);
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void setProgress(int progress, int position, int daysInc, int id) {
        if (compareDates(getRealmDate(position, daysInc), getCurrentDate()))
            taskController.updateTaskProgress(id, progress);
    }

    private void setRepeatsInfo(TextView repeatText, RealmTaskModel model, int i, boolean isFixed) {
        if(isFixed) {
            if (!TextUtils.isEmpty(model.getRealmTaskHistoryModels().get(i).getDate())) {
                repeatText.setVisibility(View.VISIBLE);
                repeatText.setText(model.getCountRepeats() + "/" + model.getRealmTaskHistoryModels().get(i).getProgress());
            }
        }
    }

    public boolean compareDates(String date1, String date2) {
        if (date1.trim().equals(date2.trim())) {
            return true;
        } else {
            return false;
        }
    }

    private String getRealmDate(int position, int id) {
        String value = "1900 01 01";
        if (id < getItem(position).getRealmTaskHistoryModels().size())
            value = getItem(position).getRealmTaskHistoryModels().get(id).getDate();
        return value;
    }

    private String getFullDate(int position) {
        int year = getDatesInView.get(position).getYear();
        int month = getDatesInView.get(position).getMonth();
        int day = getDatesInView.get(position).getDay();
        return year + " " + month + " " + day;
    }

    private String getCurrentDate() {
        Calendar cal = Calendar.getInstance();
        return cal.get(Calendar.YEAR) + " " + cal.get(Calendar.MONTH) + " " + cal.get(Calendar.DATE);
    }

    public void setOnClickListener(OnClickCallback onNextMonthClickListener) {
        this.onClickCallback = onNextMonthClickListener;
    }

    public interface OnClickCallback {
        void onNextClick();
        void onPrevClick();
        void onDateClick(int dayCount);
    }
}