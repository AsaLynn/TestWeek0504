package com.example.think.zhihudemo.model;

/**
 * Created by think on 2017/12/21.
 */

public class VersionInfo {

    /**
     * latest : 2.6.0
     * msg : 【更新】  - 极大提升性能及稳定性 - 部分用户无法使用新浪微博登录 - 部分用户无图模式无法分享至微信及朋友圈
     * status : 1
     * url : http://zhstatic.zhihu.com/pkg/store/daily/zhihu-daily-zhihu-2.6.0(744)-release.apk
     */

    private String latest;
    private String msg;
    private int status;
    private String url;

    public String getLatest() {
        return latest;
    }

    public void setLatest(String latest) {
        this.latest = latest;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
