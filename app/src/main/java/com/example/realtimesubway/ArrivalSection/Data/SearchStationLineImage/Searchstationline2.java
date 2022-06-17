package com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage;

import android.graphics.Bitmap;

import com.example.realtimesubway.ArrivalMainActivity;

public class Searchstationline2 {
    public Bitmap secondImage;
    Bitmap image1 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image1;
    Bitmap image2 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image2;
    Bitmap image3 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image3;
    Bitmap image4 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image4;
    Bitmap image5 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image5;
    Bitmap image6 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image6;
    Bitmap image7 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image7;
    Bitmap image8 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image8;
    Bitmap image9 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image9;
    Bitmap image10 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image10;
    Bitmap image11 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image11;
    Bitmap image12 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image12;
    Bitmap image13 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image13;
    Bitmap image14 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image14;
    Bitmap image24 = ((ArrivalMainActivity)ArrivalMainActivity.mContext).image24;

    int i = 1;
    public Searchstationline2(String value) {
        if ("01호선".equals(value)) {
            secondImage = image1;
        } else if ("02호선".equals(value)) {
            secondImage = image2;
        } else if ("03호선".equals(value)) {
            secondImage  = image3;
        } else if ("04호선".equals(value)) {
            secondImage  = image4;
        } else if ("05호선".equals(value)) {
            secondImage  = image5;
        } else if ("06호선".equals(value)) {
            secondImage  = image6;
        } else if ("07호선".equals(value)) {
            secondImage  = image7;
        } else if ("08호선".equals(value)) {
            secondImage  = image8;
        } else if ("09호선".equals(value)) {
            secondImage  = image9;
        } else if ("수인분당선".equals(value)) {
            secondImage  = image10;
        } else if ("경춘선".equals(value)) {
            secondImage  = image11;
        } else if ("공항철도".equals(value)) {
            secondImage  = image12;
        } else if ("신분당선".equals(value)) {
            secondImage  = image13;
        } else if ("우이신설선".equals(value)) {
            secondImage  = image14;
        } else if(value == null){
            secondImage  = image24;
        }
    }
}
