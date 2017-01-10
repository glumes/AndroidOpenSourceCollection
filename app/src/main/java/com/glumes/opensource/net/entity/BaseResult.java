package com.glumes.opensource.net.entity;

import java.util.List;

/**
 * Created by zhaoying on 16/11/8.
 */

public class BaseResult {
    /**
     * _id : 58730032421aa9315ea79923
     * createdAt : 2017-01-09T11:14:58.44Z
     * desc : 仿饿了么加入购物车旋转控件 - 自带闪转腾挪动画 的按钮。UI已适配View复用。
     * images : ["http://img.gank.io/e7e8dc73-fe8a-4295-8850-a8333c23da01","http://img.gank
     * .io/6c8ccfc1-c398-43fc-9b6c-82285be0675e"]
     * publishedAt : 2017-01-09T11:46:59.821Z
     * source : web
     * type : Android
     * url : https://github.com/mcxtzhang/AnimShopButton
     * used : true
     * who : 张旭童
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;
    private List<String> images;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "BaseData{" +
                "_id='" + _id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                ", images=" + images +
                '}';
    }
}
