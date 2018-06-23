package com.example.think.zhihudemo.activity;

import com.example.demonstrate.DialogPage;
import com.example.demonstrate.FirstActivity;
import com.example.think.zhihudemo.listener.DialogItem1Lis;

public class EnterActivity extends FirstActivity {

    @Override
    protected void click0() {
        DialogPage.getInstance()
                .setOnDialogItemListener(new DialogItem1Lis(this));
    }
}
