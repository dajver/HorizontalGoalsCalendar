package com.project.piechartcallendarexample.ui.add.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/16/17.
 */

public class WeeksView extends LinearLayout {

    @BindView(R.id.monBtn)
    TextView monBtn;
    @BindView(R.id.thuBtn)
    TextView thuBtn;
    @BindView(R.id.wedBtn)
    TextView wedBtn;
    @BindView(R.id.thurBtn)
    TextView thurBtn;
    @BindView(R.id.frBtn)
    TextView frBtn;
    @BindView(R.id.surthBtn)
    TextView sutBtn;
    @BindView(R.id.sunBtn)
    TextView sunBtn;

    public WeeksView(Context context) {
        super(context);
        init(context);
    }

    public WeeksView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WeeksView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        inflate(context, R.layout.view_tasks_weeks, this);
        setOrientation(VERTICAL);
        ButterKnife.bind(this);
    }

    public TextView getMonBtn() {
        return monBtn;
    }

    public TextView getThuBtn() {
        return thuBtn;
    }

    public TextView getWedBtn() {
        return wedBtn;
    }

    public TextView getThurBtn() {
        return thurBtn;
    }

    public TextView getFrBtn() {
        return frBtn;
    }

    public TextView getSutBtn() {
        return sutBtn;
    }

    public TextView getSunBtn() {
        return sunBtn;
    }
}