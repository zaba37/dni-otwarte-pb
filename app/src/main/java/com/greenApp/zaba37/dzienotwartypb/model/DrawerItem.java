package com.greenApp.zaba37.dzienotwartypb.model;

import android.graphics.drawable.Drawable;

/**
 * Created by zaba3 on 25.02.2016.
 */
public class DrawerItem {
    private String itemName;
    private Drawable imgRes;
    private String tag;

    public DrawerItem(String itemName, Drawable imgRes, String tag) {
        this.itemName = itemName;
        this.imgRes = imgRes;
        this.tag = tag;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Drawable getImgRes() {
        return imgRes;
    }

    public void setImgRes(Drawable imgRes) {
        this.imgRes = imgRes;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }
}
