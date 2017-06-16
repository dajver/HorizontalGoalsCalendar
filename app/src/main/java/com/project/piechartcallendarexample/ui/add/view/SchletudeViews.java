package com.project.piechartcallendarexample.ui.add.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class SchletudeViews extends LinearLayout {

    @BindView(R.id.weeksView)
    WeeksView weeksView;

    @BindView(R.id.repeatSpinner)
    Spinner repeatSpinner;
    @BindView(R.id.deadLine)
    TextView deadLine;
    @BindView(R.id.withoutDeadLine)
    TextView withoutDeadLine;

    @BindView(R.id.schletudeQuestion)
    ImageView schletudeQuestion;

    public SchletudeViews(Context context) {
        super(context);
        init(context);
    }

    public SchletudeViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SchletudeViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_new_target_shletude, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
    }

    public WeeksView getWeeksView() {
        return weeksView;
    }

    public Spinner getRepeatSpinner() {
        return repeatSpinner;
    }

    public TextView getDeadline() {
        return deadLine;
    }

    public ImageView getSchletudeQuestion() {
        return schletudeQuestion;
    }

    public TextView getWithoutDeadLine() {
        return withoutDeadLine;
    }
}