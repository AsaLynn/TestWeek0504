package com.example.think.zhihudemo.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.demonstrate.DemonstrateUtil;
import com.example.think.zhihudemo.R;
import com.example.think.zhihudemo.factory.FragmentFactory;
import com.example.think.zhihudemo.model.VersionInfo;
import com.example.think.zhihudemo.utils.UC;
import com.google.gson.Gson;
import com.roughike.bottombar.BottomBar;
import com.roughike.bottombar.BottomBarTab;
import com.roughike.bottombar.OnTabSelectListener;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class Test1Activity extends AppCompatActivity {


    private String TAG = this.getClass().getSimpleName();
    private Gson gson;
    private VersionInfo info;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);
        initActionBar();
        initBottomBar();
        checkNewVersion();
    }

    private void checkNewVersion() {
        if (null == gson) {
            gson = new Gson();
        }
        OkHttpClient client = new OkHttpClient.Builder()
                .build();
        Call call = client
                .newCall(
                        new Request
                                .Builder()
                                .url(UC.VERSION_URL)
                                .build()
                );
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String result = response.body().string();
                Log.i(TAG, "***" + result);
                info = gson.fromJson(result, VersionInfo.class);
                Test1Activity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog();
                    }
                });
            }
        });
    }

    private void dialog() {
        if (info.getStatus() == 1) {
            new AlertDialog.Builder(this)
                    .setIcon(R.mipmap.ic_launcher_round)
                    .setTitle("发现新版本")
                    .setMessage(info.getMsg())
                    .setPositiveButton("升级", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            Toast.makeText(Test1Activity.this, "升级666!", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(Test1Activity.this, "取消!", Toast.LENGTH_SHORT).show();
                }
            }).create().show();
        } else {
            Toast.makeText(this, "当前版本是:" + info.getLatest(), Toast.LENGTH_SHORT).show();
        }
    }

    private void initActionBar() {
        ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.setTitle("");
    }

    private void initBottomBar() {
        final BottomBar bottomBar = (BottomBar) findViewById(R.id.bottomBar);
        bottomBar.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelected(@IdRes int tabId) {
                int position = bottomBar.findPositionForTabWithId(tabId);
                Log.i(TAG, "getCurrentTabPosition: --->" + position);
                DemonstrateUtil
                        .showToastResult(getApplicationContext(), bottomBar.getTabWithId(tabId).getTitle());
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();

                //隐藏其他
                fragmentTransaction.hide(FragmentFactory.getFragment(0));
                fragmentTransaction.hide(FragmentFactory.getFragment(1));

                //显示当前
                //判断是否已经添加了，如果添加了，则显示，否则先添加再显示
                Fragment fragment = FragmentFactory.getFragment(position);
                if (!fragment.isAdded()) {
                    fragmentTransaction.add(R.id.contentContainer, fragment, position + "");
                }
                fragmentTransaction.show(fragment);
                fragmentTransaction.commit();

                switch (tabId) {
                    case R.id.tab_favorites:
                        BottomBarTab bottomBarTab = bottomBar.getTabWithId(tabId);
                        bottomBar.setBadgesHideWhenActive(true);
                        break;
                    case R.id.tab_nearby:
                        break;
                    default:
                        break;
                }
            }
        });
        bottomBar.selectTabAtPosition(0);
//        BottomBarTab nearby = bottomBar.getTabWithId(R.id.tab_favorites);
//        nearby.setBadgeCount(1);

    }
}
