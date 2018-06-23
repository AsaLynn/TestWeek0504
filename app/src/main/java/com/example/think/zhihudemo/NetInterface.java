package com.example.think.zhihudemo;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by think on 2017/12/21.
 */

public interface NetInterface {

    @GET
    Call<MsgInfo> getData(@Url String url);

    @GET
    Call<ResponseBody> getDetailsData(@Url String url);

    @GET
    Call<MsgGoodInfo> getGoodData(@Url String url);
}
