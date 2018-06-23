package com.example.think.zhihudemo;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONObject;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MsgDetailsActivity extends AppCompatActivity {

    protected ImageView iv;
    protected TextView titleTv;
    protected WebView wv;
    protected TextView tvNum;
    private String TAG = this.getClass().getSimpleName();
    private Gson gson;
    private String image;
    private String title;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_msg_details);
        EventBus.getDefault().register(this);
//        String event = EventBus.getDefault().getStickyEvent(String.class);
//        Log.i(TAG, "onCreate: " + event);
//        onMessageEvent(event);
        gson = new Gson();
        initView();
    }

    private void initView() {
        iv = (ImageView) findViewById(R.id.iv);
        titleTv = (TextView) findViewById(R.id.title_tv);
        wv = (WebView) findViewById(R.id.wv);
        //支持javascript
        wv.getSettings().setJavaScriptEnabled(true);
        // 设置可以支持缩放
//        wv.getSettings().setSupportZoom(true);
        // 设置出现缩放工具
//        wv.getSettings().setBuiltInZoomControls(true);
        //扩大比例的缩放
        wv.getSettings().setUseWideViewPort(true);
        //自适应屏幕
        wv.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        wv.getSettings().setLoadWithOverviewMode(true);
        tvNum = (TextView) findViewById(R.id.tv_num);


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onEvent(String event) {
        get1(event);
        get2(event);
    }

//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void onMessageEvent(String event) {
//        get1(event);
//        get2(event);
//    }

    private void get2(String event) {
        String url = "https://news-at.zhihu.com/api/4/story-extra/" + event;
        String baseUrl = "https://news-at.zhihu.com/";
        new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(NetInterface.class)
                .getGoodData(url)
                .enqueue(new Callback<MsgGoodInfo>() {
                    @Override
                    public void onResponse(Call<MsgGoodInfo> call, Response<MsgGoodInfo> response) {
                        MsgGoodInfo goodInfo = response.body();
                        tvNum.setText("新闻被赞的数目:"+goodInfo.getPopularity());
                    }

                    @Override
                    public void onFailure(Call<MsgGoodInfo> call, Throwable t) {

                    }
                });
    }

    private void get1(String event) {
        String url = "https://news-at.zhihu.com/api/4/news/" + event;
        String baseUrl = "https://news-at.zhihu.com/";
        new Retrofit.Builder()
                .baseUrl(baseUrl)
                .build()
                .create(NetInterface.class)
                .getDetailsData(url)
                .enqueue(new Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                        try {
                            MediaType mediaType = response.body().contentType();
                            Log.i(TAG, "****mediaType" + mediaType);

                            String result = response.body().string();
                            Log.i(TAG, "****" + result);
                            Toast.makeText(MsgDetailsActivity.this, result, Toast.LENGTH_SHORT).show();
                            JSONObject object = new JSONObject(result);
                            image = object.getString("image");
                            iv.setImageURI(Uri.parse(image));
                            title = object.getString("title");
                            titleTv.setText(title);
                            String body = object.getString("body");
//                            wv.loadData(body,"text/html","utf-8");
                            wv.loadData(body, "text/html; charset=UTF-8", null);
                            image = object.getString("image");
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {
                        //
                    }
                });
    }
}
