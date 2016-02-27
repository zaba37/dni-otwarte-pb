package com.example.zaba37.dniotwartepb.model;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.media.Image;

/**
 * Created by zaba3 on 27.02.2016.
 */
public class Place {
    private Drawable placeImage;
    private String placeName;

    public Place(String placeName, Drawable placeImage){
        this.placeName = placeName;
        this.placeImage = placeImage;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Drawable getPlaceImage() {
        return placeImage;
    }

    public void setPlaceImage(Drawable placeImage) {
        this.placeImage = placeImage;
    }
}
