package com.project.piechartcallendarexample.ui.calendar.adapter.holder;

import android.view.View;
import android.widget.TableLayout;
import android.widget.TextView;

import com.project.piechartcallendarexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by gleb on 6/15/17.
 */

public class ItemHolder {
    @BindView(R.id.title)
    public TextView title;
    @BindView(R.id.days)
    public TextView days;

    @BindView(R.id.progressRow)
    public TableLayout progressRow;

    public ItemHolder(View view){
        ButterKnife.bind(this, view);
    }
}
