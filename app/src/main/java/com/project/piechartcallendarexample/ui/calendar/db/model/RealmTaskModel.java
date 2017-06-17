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
    private boolean monday;
    private boolean thuesday;
    private boolean wednessday;
    private boolean thuersday;
    private boolean friday;
    private boolean suthurday;
    private boolean sunday;
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

    public boolean isMonday() {
        return monday;
    }

    public void setMonday(boolean monday) {
        this.monday = monday;
    }

    public boolean isThuesday() {
        return thuesday;
    }

    public void setThuesday(boolean thuesday) {
        this.thuesday = thuesday;
    }

    public boolean isWednessday() {
        return wednessday;
    }

    public void setWednessday(boolean wednessday) {
        this.wednessday = wednessday;
    }

    public boolean isThuersday() {
        return thuersday;
    }

    public void setThuersday(boolean thuersday) {
        this.thuersday = thuersday;
    }

    public boolean isFriday() {
        return friday;
    }

    public void setFriday(boolean friday) {
        this.friday = friday;
    }

    public boolean isSuthurday() {
        return suthurday;
    }

    public void setSuthurday(boolean suthurday) {
        this.suthurday = suthurday;
    }

    public boolean isSunday() {
        return sunday;
    }

    public void setSunday(boolean sunday) {
        this.sunday = sunday;
    }
}