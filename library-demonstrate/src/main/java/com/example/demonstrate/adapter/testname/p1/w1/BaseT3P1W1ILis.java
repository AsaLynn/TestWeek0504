package com.example.demonstrate.adapter.testname.p1.w1;

import android.app.Activity;

import com.example.demonstrate.R;
import com.example.demonstrate.adapter.testname.p1.BaseT3P1ILis;

/**
 * Created by think on 2018/3/13.
 */

public abstract class BaseT3P1W1ILis extends BaseT3P1ILis {
    public BaseT3P1W1ILis(Activity activity) {
        super(activity);
    }

    @Override
    protected int getWeekNumId() {
        return R.string.week_num1;
    }

}
