package com.example.realtimesubway.SearchStationLineImage;

import android.graphics.Bitmap;

import com.example.realtimesubway.MainActivity;

public class Searchstationline3 {
    public Bitmap thirdImage;
    Bitmap image1 = ((MainActivity)MainActivity.mContext).image1;
    Bitmap image2 = ((MainActivity)MainActivity.mContext).image2;
    Bitmap image3 = ((MainActivity)MainActivity.mContext).image3;
    Bitmap image4 = ((MainActivity)MainActivity.mContext).image4;
    Bitmap image5 = ((MainActivity)MainActivity.mContext).image5;
    Bitmap image6 = ((MainActivity)MainActivity.mContext).image6;
    Bitmap image7 = ((MainActivity)MainActivity.mContext).image7;
    Bitmap image8 = ((MainActivity)MainActivity.mContext).image8;
    Bitmap image9 = ((MainActivity)MainActivity.mContext).image9;
    Bitmap image10 = ((MainActivity)MainActivity.mContext).image10;
    Bitmap image11 = ((MainActivity)MainActivity.mContext).image11;
    Bitmap image12 = ((MainActivity)MainActivity.mContext).image12;
    Bitmap image13 = ((MainActivity)MainActivity.mContext).image13;
    Bitmap image14 = ((MainActivity)MainActivity.mContext).image14;
    Bitmap image15 = ((MainActivity)MainActivity.mContext).image15;
    Bitmap image16 = ((MainActivity)MainActivity.mContext).image16;
    Bitmap image17 = ((MainActivity)MainActivity.mContext).image17;
    Bitmap image18 = ((MainActivity)MainActivity.mContext).image18;
    Bitmap image19 = ((MainActivity)MainActivity.mContext).image19;
    Bitmap image20 = ((MainActivity)MainActivity.mContext).image20;
    Bitmap image21 = ((MainActivity)MainActivity.mContext).image21;
    Bitmap image22 = ((MainActivity)MainActivity.mContext).image22;
    Bitmap image23 = ((MainActivity)MainActivity.mContext).image23;
    Bitmap image24 = ((MainActivity)MainActivity.mContext).image24;

    public Searchstationline3(String value) {
        if ("01호선".equals(value)) {
            thirdImage = image1;
        } else if ("02호선".equals(value)) {
            thirdImage = image2;
        } else if ("03호선".equals(value)) {
            thirdImage  = image3;
        } else if ("04호선".equals(value)) {
            thirdImage  = image4;
        } else if ("05호선".equals(value)) {
            thirdImage  = image5;
        } else if ("06호선".equals(value)) {
            thirdImage  = image6;
        } else if ("07호선".equals(value)) {
            thirdImage  = image7;
        } else if ("08호선".equals(value)) {
            thirdImage  = image8;
        } else if ("09호선".equals(value)) {
            thirdImage  = image9;
        } else if ("경의선".equals(value)) {
            thirdImage  = image10;
        } else if ("수인분당선".equals(value)) {
            thirdImage  = image11;
        } else if ("경춘선".equals(value)) {
            thirdImage  = image12;
        } else if ("인천선".equals(value)) {
            thirdImage  = image13;
        } else if ("인천2호선".equals(value)) {
            thirdImage  = image14;
        } else if ("공항철도".equals(value)) {
            thirdImage  = image15;
        } else if ("신림선".equals(value)) {
            thirdImage  = image16;
        } else if ("서해선".equals(value)) {
            thirdImage  = image17;
        } else if ("김포도시철도".equals(value)) {
            thirdImage  = image18;
        } else if ("신분당선".equals(value)) {
            thirdImage  = image19;
        } else if ("용인경전철".equals(value)) {
            thirdImage  = image20;
        } else if ("의정부경전철".equals(value)) {
            thirdImage  = image21;
        } else if ("우이신설경전철".equals(value)) {
            thirdImage  = image22;
        } else if ("경강선".equals(value)) {
            thirdImage  = image23;
        } else if(value == null){
            thirdImage  = image24;
        }
    }
}
