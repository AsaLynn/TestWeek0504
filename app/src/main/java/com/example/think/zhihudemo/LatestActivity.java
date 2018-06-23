package com.example.think.zhihudemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.zhihudemo.green.dao.MsgGreenInfoDao;
import com.example.think.zhihudemo.green.entity.MsgGreenInfo;
import com.facebook.drawee.backends.pipeline.Fresco;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LatestActivity extends AppCompatActivity {

    protected RecyclerView rv;
    public String baseUrl = "https://news-at.zhihu.com/";
    private String url = "https://news-at.zhihu.com/api/4/news/latest";
    private MsgAdapter adapter;
    private List<MsgInfo.StoriesBean> mBeans;
    private MsgGreenInfoDao greenInfoDao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setContentView(R.layout.activity_latest);
        greenInfoDao = MyApplication.getInstance().getSession().getMsgGreenInfoDao();
        Fresco.initialize(this);
        initView();
        request();
    }

    private void request() {
        Observable.create(new ObservableOnSubscribe<MsgInfo>() {


            @Override
            public void subscribe(ObservableEmitter<MsgInfo> e) throws Exception {
                MsgInfo info = new Retrofit.Builder()
                        .baseUrl(baseUrl)
                        .addConverterFactory(GsonConverterFactory.create())
                        .build()
                        .create(NetInterface.class)
                        .getData(url)
                        .execute()
                        .body();
                e.onNext(info);
            }
        }).map(new Function<MsgInfo, List<MsgInfo.StoriesBean>>() {

            @Override
            public List<MsgInfo.StoriesBean> apply(MsgInfo msgInfo) throws Exception {
                return msgInfo.getStories();
            }
        })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<MsgInfo.StoriesBean>>() {

                    @Override
                    public void accept(List<MsgInfo.StoriesBean> storiesBeans) throws Exception {
                        mBeans = storiesBeans;
                        adapter.setData(storiesBeans);
                        if (greenInfoDao.loadAll() == null) {
                            for (final MsgInfo.StoriesBean info : mBeans) {
                                greenInfoDao.insert(new MsgGreenInfo(){
                                    {
                                        setImageUrl(info.getImages().get(0));
                                        setTitle(info.getTitle());
                                        setId(info.getId());
                                    }
                                });
                            }
                        }

                    }
                });
    }

    private void initView() {
        rv = (RecyclerView) findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setHasFixedSize(true);
        adapter = new MsgAdapter(rv);
        rv.setAdapter(adapter);

        adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
//                Toast.makeText(LatestActivity.this, "" + mBeans.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                //往新页面传递数据.
                EventBus.getDefault().postSticky(mBeans.get(position).getId()+"");
                Intent intent = new Intent(LatestActivity.this, MsgDetailsActivity.class);
                startActivity(intent);
            }
        });

    }
}
