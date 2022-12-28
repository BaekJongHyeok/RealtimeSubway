package com.example.realtimesubway.ArrivalSection.Data.SearchFilter;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class SearchItem {
    ArrayList<Bitmap> imageList;
    String stationName;
    public SearchItem(ArrayList<Bitmap> imageList, String stationName){
        this.imageList = imageList;
        this.stationName = stationName;
    }

    public ArrayList<Bitmap> getImageList() { return imageList; }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

}
