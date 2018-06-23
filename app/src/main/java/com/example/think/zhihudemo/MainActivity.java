package com.example.think.zhihudemo;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.think.zhihudemo.model.VersionInfo;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    protected Button btn01;
    protected Button btn02;
    private OkHttpClient client;
//    private String url = "https://news-at.zhihu.com/api/4/version/android/2.3.0";
    private String url = "http://116.62.142.20:85/Public/register";
    private String TAG = this.getClass().getSimpleName();
    private Gson gson;
    private VersionInfo info;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_main);
        client = new OkHttpClient.Builder()
                .build();
        gson = new Gson();
        checkNewVersion();
        initView();
    }

    private void checkNewVersion() {
        FormBody body = new FormBody.Builder().build();

        Call call = client
                .newCall(
                        new Request
                                .Builder()
                                .url(url)
                                .put(body)
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
               /* info = gson.fromJson(result, VersionInfo.class);
                MainActivity.this.runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog();
                    }
                });*/
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
                            Toast.makeText(MainActivity.this, "升级666!", Toast.LENGTH_SHORT).show();
                        }
                    }).setNegativeButton("取消", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(MainActivity.this, "取消!", Toast.LENGTH_SHORT).show();
                }
            }).create().show();
        } else {
            Toast.makeText(this, "当前版本是:" + info.getLatest(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_01) {
            startActivity(new Intent(this,LatestActivity.class));
        } else if (view.getId() == R.id.btn_02) {
            startActivity(new Intent(this,MsgOldActivity.class));
        }
    }

    private void initView() {
        btn01 = (Button) findViewById(R.id.btn_01);
        btn01.setOnClickListener(MainActivity.this);
        btn02 = (Button) findViewById(R.id.btn_02);
        btn02.setOnClickListener(MainActivity.this);
    }
}
