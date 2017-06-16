package com.project.piechartcallendarexample.ui.add;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.CompoundButton;

import com.project.piechartcallendarexample.R;
import com.project.piechartcallendarexample.etc.RandomUtils;
import com.project.piechartcallendarexample.ui.calendar.db.TaskController;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskHistoryModel;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;

import java.util.Calendar;

import io.realm.RealmList;

/**
 * Created by gleb on 6/16/17.
 */

public class NewTaskFragment extends BaseNewTaskFragment implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {

    public static final int WITHOUD_DEADLINE = 5475;

    private RealmTaskModel realmTaskModel;
    private RealmList<RealmTaskHistoryModel> taskHistoryModels;

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setRepeatSpinnerAdapter();

        taskHistoryModels = new RealmList<>();
        realmTaskModel = new RealmTaskModel();

        schletudeViews.getFixedRadio().setOnCheckedChangeListener(this);

        schletudeViews.getWeeksView().getMonBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getThuBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getWedBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getThurBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getFrBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getSutBtn().setOnClickListener(this);
        schletudeViews.getWeeksView().getSunBtn().setOnClickListener(this);
        schletudeViews.getSchletudeQuestion().setOnClickListener(this);
        schletudeViews.getWithoutDeadLine().setOnClickListener(this);
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
                realmTaskModel.setCountDays(withoutDeadline ? WITHOUD_DEADLINE : deadlineValue);
                realmTaskModel.setCountRepeats(repeatValue);
                realmTaskModel.setQuanlityValue(quanlityValue);
                realmTaskModel.setOnceOnWeekOrMonth(onceWeekOrMonth);
                realmTaskModel.setSchletudeType(schletudeViews.getFixedRadio().isChecked() ? SCHLETUDE_TYPE_FIXED : SCHLETUDE_TYPE_FLOAT);
                realmTaskModel.setMonday(schletudeViews.getFixedRadio().isChecked() ? monday : true);
                realmTaskModel.setThuesday(schletudeViews.getFixedRadio().isChecked() ? thuesday : true);
                realmTaskModel.setWednessday(schletudeViews.getFixedRadio().isChecked() ? wednesdey : true);
                realmTaskModel.setThuersday(schletudeViews.getFixedRadio().isChecked() ? thursday : true);
                realmTaskModel.setFriday(schletudeViews.getFixedRadio().isChecked() ? friday : true);
                realmTaskModel.setSuthurday(schletudeViews.getFixedRadio().isChecked() ? suthurday : true);
                realmTaskModel.setSunday(schletudeViews.getFixedRadio().isChecked() ? sunday : true);
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

                if(schletudeViews.getFixedRadio().isChecked()) {
                    if (monday || thuesday || wednesdey || thursday || friday || suthurday || sunday)
                        new InsertDataAboutTask().execute();
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_add, menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch(buttonView.getId()) {
            case R.id.fixedRadio:
                schletudeViews.getFixedRadio().setChecked(isChecked);
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.monBtn:
            case R.id.thuBtn:
            case R.id.wedBtn:
            case R.id.thurBtn:
            case R.id.frBtn:
            case R.id.surthBtn:
            case R.id.sunBtn:
                onWeekChecked(v);
                break;
            case R.id.withoutDeadLine:
                onWithoutDeadlineChecked(v);
                break;
        }
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