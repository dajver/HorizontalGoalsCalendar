package com.project.piechartcallendarexample.ui.add;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.etc.RandomUtils;
import com.project.piechartcallendarexample.ui.BaseFragment;
import com.project.piechartcallendarexample.ui.add.view.SchletudeViews;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskHistoryModel;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.util.Calendar;

import butterknife.BindColor;
import butterknife.BindView;
import io.realm.RealmList;

/**
 * Created by gleb on 6/16/17.
 */

public class NewTaskFragment extends BaseFragment {

    public static final int WITHOUD_DEADLINE = 5475;
    public static final int START_DATE = 0;

    @BindView(R.id.schletudeView)
    public SchletudeViews schletudeViews;
    @BindView(R.id.target)
    public EditText target;

    @BindColor(R.color.colorPrimary)
    int blueColor;
    @BindColor(R.color.dark_gray_text)
    int grayColor;

    private RealmTaskModel realmTaskModel;
    private RealmList<RealmTaskHistoryModel> taskHistoryModels;
    public TaskController taskController;

    @Override
    public int getViewId() {
        return R.layout.fragment_add_task;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        taskController = new TaskController(context);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        taskHistoryModels = new RealmList<>();
        realmTaskModel = new RealmTaskModel();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add:
                int deadlineValue = WITHOUD_DEADLINE;
                if(!TextUtils.isEmpty(schletudeViews.getDeadline().getText().toString()))
                    deadlineValue = Integer.valueOf(schletudeViews.getDeadline().getText().toString());
                realmTaskModel.setTitle(target.getText().toString());
                realmTaskModel.setDateStart(getDate(START_DATE));
                realmTaskModel.setDateFinish(getDate(daysBetweenDates(deadlineValue)));
                realmTaskModel.setCountDays(schletudeViews.isWithoutDeadline() ? WITHOUD_DEADLINE : deadlineValue);
                realmTaskModel.setCountRepeats(schletudeViews.getRepeatValue());
                realmTaskModel.setMonday(schletudeViews.isMonday());
                realmTaskModel.setThuesday(schletudeViews.isTuesday());
                realmTaskModel.setWednessday(schletudeViews.isWednesday());
                realmTaskModel.setThuersday(schletudeViews.isThursday());
                realmTaskModel.setFriday(schletudeViews.isFriday());
                realmTaskModel.setSuthurday(schletudeViews.isSaturday());
                realmTaskModel.setSunday(schletudeViews.isSunday());

                Calendar calendar = Calendar.getInstance();
                calendar.add(Calendar.DATE, -1);
                for(int i = 0; i < daysBetweenDates(deadlineValue); i++) {
                    RealmTaskHistoryModel realmTaskHistoryModel = new RealmTaskHistoryModel();
                    calendar.add(Calendar.DATE, 1);
                    realmTaskHistoryModel.setId(RandomUtils.getRandomValue());
                    realmTaskHistoryModel.setDate(calendar.get(Calendar.YEAR) + " " + calendar.get(Calendar.MONTH) + " " + calendar.get(Calendar.DATE));
                    taskHistoryModels.add(realmTaskHistoryModel);
                }
                realmTaskModel.setRealmTaskHistoryModels(taskHistoryModels);

                if (schletudeViews.isMonday() || schletudeViews.isTuesday() || schletudeViews.isWednesday() || schletudeViews.isThursday() || schletudeViews.isFriday() || schletudeViews.isSaturday() || schletudeViews.isSunday())
                    new InsertDataAboutTask().execute();
                else
                    Toast.makeText(context, getString(R.string.task_new_target_toast_error), Toast.LENGTH_LONG).show();
                return true;
            default:
                return super.onOptionsItemSelected(item);
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

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    private class InsertDataAboutTask extends AsyncTask<Void, Void, Void> {

        private TaskController taskController;

        @Override
        protected Void doInBackground(Void... params) {
            taskController = new TaskController(context);
            taskController.addTask(realmTaskModel);
            return null;
        }

        @Override
        protected void onPreExecute() { }

        @Override
        protected void onPostExecute(Void sum) {
            Intent intent = new Intent();
            context.setResult(context.RESULT_OK, intent);
            context.finish();
        }
    }
}