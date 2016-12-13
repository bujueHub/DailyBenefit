package com.bujue.dailybenefit.entity;

import java.io.Serializable;

/**
 * Created by huxq17 on 2016/4/13.
 */
public class ContentItem extends BaseItem implements Serializable{
    private int width;
    private int height;
    private String imageurl;
    private String url;
    private String title;
    private String type;
    private int groupid;
    private int order;


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getGroupid() {
        return groupid;
    }

    public void setGroupid(int groupid) {
        this.groupid = groupid;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    @Override
    public int getStatus() {
        return 0;
    }

    @Override
    public int getPage() {
        return 0;
    }
}
