package com.project.piechartcallendarexample.ui.calendar.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by gleb on 6/16/17.
 */

public class CapFirstLetterTextView extends TextView {

    public CapFirstLetterTextView(Context context) {
        super(context);
    }

    public CapFirstLetterTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CapFirstLetterTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setFirstCupText(CharSequence text) {
        String upperString = text.toString().substring(0, 1).toUpperCase() + text.toString().substring(1);
        setText(upperString);
    }
}