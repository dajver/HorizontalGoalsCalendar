package com.project.piechartcallendarexample.ui.add.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioGroup;

import com.project.piechartcallendarexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class ImpactionViews extends LinearLayout {

    @BindView(R.id.radioGroup)
    RadioGroup impactGroup;
    @BindView(R.id.impactionQuestion)
    ImageView impactionQuestion;

    public ImpactionViews(Context context) {
        super(context);
        init(context);
    }

    public ImpactionViews(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public ImpactionViews(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_new_target_impact, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
    }

    public RadioGroup getImpactGroup() {
        return impactGroup;
    }

    public ImageView getImpactionQuestion() {
        return impactionQuestion;
    }
}