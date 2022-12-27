package com.example.realtimesubway.ArrivalSection.Data.SearchFilter;

import android.graphics.Bitmap;

import java.util.ArrayList;

public class SearchItem {
    Bitmap image, image2, image3, image4;
    ArrayList<Bitmap> imageList;
    String stationName;
    public SearchItem(ArrayList<Bitmap> imageList, String stationName){
        this.imageList = imageList;
        this.stationName = stationName;
    }

    public Bitmap getImage() {
        return imageList.get(0);
    }

    public void setImage(Bitmap image) {
        this.image = imageList.get(0);
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public Bitmap getImage2() {
        return imageList.get(1);
    }

    public void setImage2(Bitmap image2) {
        this.image2 = imageList.get(1);
    }

    public Bitmap getImage3() {
        return imageList.get(2);
    }

    public void setImage3(Bitmap image3) {
        this.image3 = imageList.get(2);
    }

    public Bitmap getImage4() {
        return imageList.get(3);
    }

    public void setImage4(Bitmap image4) {
        this.image4 = imageList.get(3);
    }
}
