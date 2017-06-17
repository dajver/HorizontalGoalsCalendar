package com.project.piechartcallendarexample.ui.calendar.db;

import android.content.Context;

import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskHistoryModel;
import com.project.piechartcallendarexample.ui.calendar.db.model.RealmTaskModel;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmResults;

/**
 * Created by gleb on 6/15/17.
 */

public class TaskController {

    private Context context;
    private Realm realm;

    public TaskController(Context context) {
        this.context = context;

        RealmConfiguration config = new RealmConfiguration.Builder(context).build();
        realm.setDefaultConfiguration(config);
        realm = Realm.getInstance(context);
    }

    public void addTask(RealmTaskModel model) {
        realm.beginTransaction();
        RealmTaskModel realmTaskModel = realm.createObject(RealmTaskModel.class);
        realmTaskModel.setId(getNextKey());
        realmTaskModel.setTitle(model.getTitle());
        realmTaskModel.setDateStart(model.getDateStart());
        realmTaskModel.setDateFinish(model.getDateFinish());
        realmTaskModel.setCountDays(model.getCountDays());
        realmTaskModel.setCountRepeats(model.getCountRepeats());
        realmTaskModel.setMonday(model.isMonday());
        realmTaskModel.setThuesday(model.isThuesday());
        realmTaskModel.setWednessday(model.isWednessday());
        realmTaskModel.setThuersday(model.isThuersday());
        realmTaskModel.setFriday(model.isFriday());
        realmTaskModel.setSuthurday(model.isSuthurday());
        realmTaskModel.setSunday(model.isSunday());
        for (RealmTaskHistoryModel taskHistoryModel : model.getRealmTaskHistoryModels())
            realmTaskModel.getRealmTaskHistoryModels().add(taskHistoryModel);
        realm.commitTransaction();
    }

    private int getNextKey() {
        return realm.where(RealmTaskModel.class).max("id").intValue() + 1;
    }

    public ArrayList<RealmTaskModel> getAllTasks() {
        ArrayList<RealmTaskModel> realmTaskModels = new ArrayList<>();
        RealmResults<RealmTaskModel> results = realm.where(RealmTaskModel.class).findAll();
        for (int i = 0; i < results.size(); i++) {
            RealmTaskModel u = results.get(i);
            realmTaskModels.add(u);
        }
        return realmTaskModels;
    }

    public void updateTaskProgress(int id, int progress) {
        RealmTaskHistoryModel realmTargetModel = realm.where(RealmTaskHistoryModel.class).equalTo("id", id).findFirst();
        realm.beginTransaction();
        realmTargetModel.setProgress(progress);
        realm.commitTransaction();
    }

    public void clearDatabase() {
        realm.beginTransaction();
        realm.clear(RealmTaskModel.class);
        realm.commitTransaction();
    }
}
