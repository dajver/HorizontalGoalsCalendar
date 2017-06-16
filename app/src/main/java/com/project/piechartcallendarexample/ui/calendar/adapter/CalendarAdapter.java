package com.project.piechartcallendarexample.ui.calendar.adapter;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.ui.calendar.adapter.model.CalendarModel;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;
import com.project.piechartcallendarexample.ui.calendar.holder.HeaderHolder;
import com.project.piechartcallendarexample.ui.calendar.holder.ItemHolder;
import com.project.piechartcallendarexample.ui.calendar.view.ProgressView;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by gleb on 6/15/17.
 */

public class CalendarAdapter extends BaseAdapter {

    private static final int TYPE_HEAD = 0;
    private static final int TYPE_ITEM = 1;

    private final DateFormat dateFormatter = new DateFormat();
    private static final String dateTemplate = "MMMM \nyyyy";

    private HeaderHolder headHolder;
    private ItemHolder itemHolder;
    private TaskController taskController;

    private Context context;
    private ArrayList<RealmTaskModel> listData = new ArrayList<>();
    private ArrayList<CalendarModel> getDatesInView;
    private Calendar calendar;

    private OnClickCallback onClickCallback;

    private int datePosition = 0;

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
        switch(type) {
            case TYPE_HEAD:
                convertView = inflater.inflate(R.layout.item_calendar_days, parent, false);
                headHolder = new HeaderHolder(convertView);

                headHolder.currentMonth.setFirstCupText(dateFormatter.format(dateTemplate, calendar.getTime()));

                headHolder.mondayTw.setText(String.valueOf(getDatesInView.get(0).getDay()));
                headHolder.thusdayTw.setText(String.valueOf(getDatesInView.get(1).getDay()));
                headHolder.wednasdayTw.setText(String.valueOf(getDatesInView.get(2).getDay()));
                headHolder.thursday.setText(String.valueOf(getDatesInView.get(3).getDay()));
                headHolder.fridayTw.setText(String.valueOf(getDatesInView.get(4).getDay()));
                headHolder.sutardayTw.setText(String.valueOf(getDatesInView.get(5).getDay()));
                headHolder.sundayTw.setText(String.valueOf(getDatesInView.get(6).getDay()));

                headHolder.firstDayOfWeek.setText(getDatesInView.get(0).getDayOfWeek());
                headHolder.secondDayOfWeek.setText(getDatesInView.get(1).getDayOfWeek());
                headHolder.thirdDayOfWeek.setText(getDatesInView.get(2).getDayOfWeek());
                headHolder.forthDayOfWeek.setText(getDatesInView.get(3).getDayOfWeek());
                headHolder.fifthDayOfWeek.setText(getDatesInView.get(4).getDayOfWeek());
                headHolder.sixthDayOfWeek.setText(getDatesInView.get(5).getDayOfWeek());
                headHolder.seventhDayOfWeek.setText(getDatesInView.get(6).getDayOfWeek());

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
                        if(getItem(1) != null) {
                            String firstDate = "";
                            if(getItem(1).getRealmTaskHistoryModels().size() > 0) {
                                firstDate = getItem(1).getRealmTaskHistoryModels().get(0).getDate();
                            }

                            if(!compareDates(firstDate, getFullDate(0)) || !compareDates(firstDate, getFullDate(1)) ||
                                    !compareDates(firstDate, getFullDate(2)) || !compareDates(firstDate, getFullDate(3)) ||
                                    !compareDates(firstDate, getFullDate(4)) || !compareDates(firstDate, getFullDate(5)) ||
                                    !compareDates(firstDate, getFullDate(6))) {
                                if (datePosition > 0)
                                    datePosition -= 7;
                                onClickCallback.onPrevClick();
                            }
                        }
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

                for(int i = 0; i < (7 + datePosition); i++) {
                    if (compareDates(getFullDate(0), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatFirst, model, i, model.isMonday());
                        onProgressClick(itemHolder.progressView, model, i, model.isMonday(), position);
                    }
                    if (compareDates(getFullDate(1), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatSecond, model, i, model.isThuesday());
                        onProgressClick(itemHolder.progressView2, model, i, model.isThuesday(), position);
                    }
                    if (compareDates(getFullDate(2), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatThird, model, i, model.isWednessday());
                        onProgressClick(itemHolder.progressView3, model, i, model.isWednessday(), position);
                    }
                    if (compareDates(getFullDate(3), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatForth, model, i, model.isThuersday());
                        onProgressClick(itemHolder.progressView4, model, i, model.isThuersday(), position);
                    }
                    if (compareDates(getFullDate(4), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatFifth, model, i, model.isFriday());
                        onProgressClick(itemHolder.progressView5, model, i, model.isFriday(), position);
                    }
                    if (compareDates(getFullDate(5), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatSixth, model, i, model.isSuthurday());
                        onProgressClick(itemHolder.progressView6, model, i, model.isSuthurday(), position);
                    }
                    if (compareDates(getFullDate(6), getRealmDate(position, i))) {
                        setRepeatsInfo(itemHolder.repeatSeventh, model, i, model.isSunday());
                        onProgressClick(itemHolder.progressView7, model, i, model.isSunday(), position);
                    }
                }
                break;
        }
        return convertView;
    }

    private void onProgressClick(ProgressView progressView, final RealmTaskModel model, final int i, boolean isFixed, final int position) {
        if(isFixed) {
            progressView.setValue(model.getRealmTaskHistoryModels().get(i).getProgress());
            progressView.setProgressMaximum(model.getCountRepeats());
            progressView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int progress = model.getRealmTaskHistoryModels().get(i).getProgress() + 1;
                    int id = model.getRealmTaskHistoryModels().get(i).getId();
                    if (progress <= model.getCountRepeats())
                        setProgress(progress, position, i, id);
                    else
                        setProgress(0, position, i, id);
                    notifyDataSetChanged();
                }
            });
        }
    }

    private void setProgress(int progress, int position, int i, int id) {
        if (compareDates(getRealmDate(position, i), getCurrentDate()))
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