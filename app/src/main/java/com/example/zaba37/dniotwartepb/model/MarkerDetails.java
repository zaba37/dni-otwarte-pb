package com.example.zaba37.dniotwartepb.model;

/**
 * Created by Marcin on 04.03.2016.
 */
public class MarkerDetails {

    private int x;
    private int y;
    private double latitude;
    private double longtitude;
    private String title;

    public MarkerDetails(int x, int y, double latitude, double longtitude, String title) {
        this.x = x;
        this.y = y;
        this.latitude = latitude;
        this.longtitude = longtitude;
        this.title = title;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getLongtitude() {
        return longtitude;
    }

    public void setLongtitude(double longtitude) {
        this.longtitude = longtitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
