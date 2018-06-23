package com.example.think.zhihudemo.green.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Property;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by think on 2017/12/23.
 */

@Entity(nameInDb = "msg_tb")
public class MsgGreenInfo {
    @Id(autoincrement = true)
    private Long msgId;
    @Property(nameInDb = "title")
    private String title;
    @Property(nameInDb = "image_url")
    private String imageUrl;
    @Property(nameInDb = "id")
    private int id;
    @Property(nameInDb = "web_content")
    private String webContent;
    @Property(nameInDb = "good_count")
    private int goodCount;
    @Generated(hash = 257490599)
    public MsgGreenInfo(Long msgId, String title, String imageUrl, int id,
            String webContent, int goodCount) {
        this.msgId = msgId;
        this.title = title;
        this.imageUrl = imageUrl;
        this.id = id;
        this.webContent = webContent;
        this.goodCount = goodCount;
    }
    @Generated(hash = 1877672212)
    public MsgGreenInfo() {
    }
    public Long getMsgId() {
        return this.msgId;
    }
    public void setMsgId(Long msgId) {
        this.msgId = msgId;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getImageUrl() {
        return this.imageUrl;
    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
    public int getId() {
        return this.id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getWebContent() {
        return this.webContent;
    }
    public void setWebContent(String webContent) {
        this.webContent = webContent;
    }
    public int getGoodCount() {
        return this.goodCount;
    }
    public void setGoodCount(int goodCount) {
        this.goodCount = goodCount;
    }




}
