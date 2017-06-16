package com.project.piechartcallendarexample.ui.calendar.db.model;

import io.realm.RealmObject;

/**
 * Created by gleb on 6/15/17.
 */

public class RealmTaskHistoryModel extends RealmObject {

    private int id = 0;
    private String date = "";
    private int progress = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getProgress() {
        return progress;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }
}