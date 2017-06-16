package com.project.piechartcallendarexample.ui.calendar.db.model;

import android.support.annotation.NonNull;

import io.realm.RealmObject;

/**
 * Created by gleb on 6/15/17.
 */

public class RealmFileAttachModel extends RealmObject {
    @NonNull
    private String fileName;
    private int type;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}