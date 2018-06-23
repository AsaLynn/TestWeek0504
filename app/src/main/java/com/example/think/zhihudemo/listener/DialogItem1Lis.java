package com.example.think.zhihudemo.listener;

import android.app.Activity;

import com.example.demonstrate.adapter.testname.p1.w4.BaseT5P1W4ILis;
import com.example.think.zhihudemo.MainActivity;
import com.example.think.zhihudemo.R;
import com.example.think.zhihudemo.activity.Test1Activity;

/**
 * Created by think on 2018/3/25.
 *
 */

public class DialogItem1Lis extends BaseT5P1W4ILis {
    public DialogItem1Lis(Activity activity) {
        super(activity);
    }

    @Override
    public Class<?> getStartActivity(int which) {
        if (which == 0) {
            return Test1Activity.class;
        } else if (which == 1) {
            return MainActivity.class;
        } else if (which == 3) {

        }
        return null;
    }

    @Override
    public int getDialogListId() {
        return R.array.test5_week3_dialog1_items;
    }
}
