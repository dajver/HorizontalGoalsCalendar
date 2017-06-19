package com.project.piechartcallendarexample.ui.calendar.db.model;

import io.realm.RealmObject;

/**
 * Created by gleb on 6/19/17.
 */

public class RealmBooleanModel extends RealmObject {
    private int id;
    private boolean fixDay;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFixDay() {
        return fixDay;
    }

    public void setFixDay(boolean fixDay) {
        this.fixDay = fixDay;
    }
}
