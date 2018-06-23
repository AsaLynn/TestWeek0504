package com.example.think.zhihudemo.presenter;

import com.example.think.zhihudemo.MsgInfo;
import com.example.think.zhihudemo.NetInterface;
import com.example.think.zhihudemo.utils.UC;
import com.example.think.zhihudemo.view.TabView;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by think on 2018/3/25.
 */

public class TabPresenterImpl implements TabPresenter {


    private final TabView mView;

    public TabPresenterImpl(TabView view) {
        mView = view;
    }

    @Override
    public void request(final String url) {
        Observable.create(new ObservableOnSubscribe<MsgInfo>() {
            @Override
            public void subscribe(ObservableEmitter<MsgInfo> e) throws Exception {
                MsgInfo info = new Retrofit.Builder()
                        .baseUrl(UC.BASE_URL)
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
                        mView.refreshView(storiesBeans);
                    }
                });
    }
}
