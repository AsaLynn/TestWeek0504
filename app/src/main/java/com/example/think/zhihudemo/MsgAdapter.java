package com.example.think.zhihudemo;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.facebook.drawee.view.SimpleDraweeView;

import cn.bingoogolapple.androidcommon.adapter.BGARecyclerViewAdapter;
import cn.bingoogolapple.androidcommon.adapter.BGAViewHolderHelper;

/**
 * Created by think on 2017/12/21.
 */

public class MsgAdapter extends BGARecyclerViewAdapter<MsgInfo.StoriesBean> {


    private String TAG = "MsgAdapter";

    public MsgAdapter(RecyclerView recyclerView) {
        super(recyclerView, R.layout.item_msg);
    }

    @Override
    protected void fillData(BGAViewHolderHelper helper, int position, MsgInfo.StoriesBean model) {
        Log.i(TAG, "fillData: "+model.toString());
        helper.setText(R.id.tv,model.getTitle());
        SimpleDraweeView iv = helper.getView(R.id.iv);
        iv.setImageURI(Uri.parse(model.getImages().get(0)));
        //过期消息接口中图片地址,无法访问的!!!
        //最新消息接口中图片地址,可以访问的!!!
    }
}
