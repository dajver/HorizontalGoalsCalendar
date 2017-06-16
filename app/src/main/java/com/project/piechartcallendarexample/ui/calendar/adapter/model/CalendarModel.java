package com.project.piechartcallendarexample.ui.calendar.adapter.model;

/**
 * Created by gleb on 6/15/17.
 */

public class CalendarModel implements Comparable<CalendarModel> {

    private String dayOfWeek;
    private Integer day;
    private Integer month;
    private Integer year;

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int date) {
        this.day = date;
    }

    @Override
    public int compareTo(CalendarModel another) {
        return day.compareTo(another.day);
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }
}