package com.project.piechartcallendarexample.ui.calendar.db.model;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by gleb on 6/15/17.
 */

public class RealmTaskModel extends RealmObject {

    private int id;
    private String title;
    private int countDays;
    private int countRepeats;
    private String dateStart;
    private String dateFinish;
    private RealmList<RealmBooleanModel> fixDaysList;
    private RealmList<RealmTaskHistoryModel> realmTaskHistoryModels = new RealmList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDateStart() {
        return dateStart;
    }

    public void setDateStart(String dateStart) {
        this.dateStart = dateStart;
    }

    public String getDateFinish() {
        return dateFinish;
    }

    public void setDateFinish(String dateFinish) {
        this.dateFinish = dateFinish;
    }

    public RealmList<RealmTaskHistoryModel> getRealmTaskHistoryModels() {
        return realmTaskHistoryModels;
    }

    public void setRealmTaskHistoryModels(RealmList<RealmTaskHistoryModel> realmTaskHistoryModels) {
        this.realmTaskHistoryModels = realmTaskHistoryModels;
    }

    public int getCountDays() {
        return countDays;
    }

    public void setCountDays(int countDays) {
        this.countDays = countDays;
    }

    public int getCountRepeats() {
        return countRepeats;
    }

    public void setCountRepeats(int countRepeats) {
        this.countRepeats = countRepeats;
    }

    public RealmList<RealmBooleanModel> getFixDaysList() {
        return fixDaysList;
    }

    public void setFixDaysList(RealmList<RealmBooleanModel> fixDaysList) {
        this.fixDaysList = fixDaysList;
    }
}