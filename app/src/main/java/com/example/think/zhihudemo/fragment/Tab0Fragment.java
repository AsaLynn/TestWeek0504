package com.example.think.zhihudemo.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.think.zhihudemo.MsgAdapter;
import com.example.think.zhihudemo.MsgDetailsActivity;
import com.example.think.zhihudemo.MsgInfo;
import com.example.think.zhihudemo.R;
import com.example.think.zhihudemo.presenter.TabPresenter;
import com.example.think.zhihudemo.presenter.TabPresenterImpl;
import com.example.think.zhihudemo.utils.UC;
import com.example.think.zhihudemo.view.TabView;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import cn.bingoogolapple.androidcommon.adapter.BGAOnRVItemClickListener;

/**
 * Created by think on 2018/3/25.
 *
 */

public class Tab0Fragment extends android.support.v4.app.Fragment implements TabView<List<MsgInfo.StoriesBean>> {

    private TabPresenter presenter;
    private MsgAdapter adapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TabPresenterImpl(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_latest, null);
        view.setBackgroundColor(getResources().getColor(R.color.c_ffffff));
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RecyclerView rv = (RecyclerView) view.findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        rv.setHasFixedSize(true);
        adapter = new MsgAdapter(rv);
        rv.setAdapter(adapter);
        adapter.setOnRVItemClickListener(new BGAOnRVItemClickListener() {
            @Override
            public void onRVItemClick(ViewGroup parent, View itemView, int position) {
                //往新页面传递数据.
                EventBus.getDefault().postSticky(adapter.getData().get(position).getId()+"");
                Intent intent = new Intent(getActivity(), MsgDetailsActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.request(UC.LATEST_URL);
    }

    @Override
    public void refreshView(List<MsgInfo.StoriesBean> bean) {
        adapter.addNewData(bean);
    }
}
