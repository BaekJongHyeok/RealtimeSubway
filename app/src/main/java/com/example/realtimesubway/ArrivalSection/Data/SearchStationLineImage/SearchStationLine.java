package com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.example.realtimesubway.R;

public class SearchStationLine {
    public Bitmap image;
    private Resources res;

    public SearchStationLine(Context ctx) {
        this.res = ctx.getResources();
    }

    public Bitmap search(String line) {
        if (line.equals("01호선")) {
            image = BitmapFactory.decodeResource(res, R.drawable.line1);
        } else if (line.equals("02호선")) {
            image = BitmapFactory.decodeResource(res, R.drawable.line2);
        } else if (line.equals("03호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line3);
        } else if (line.equals("04호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line4);
        } else if (line.equals("05호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line5);
        } else if (line.equals("06호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line6);
        } else if (line.equals("07호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line7);
        } else if (line.equals("08호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line8);
        } else if (line.equals("09호선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line9);
        } else if (line.equals("수인분당선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line10);
        } else if (line.equals("경춘선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line11);
        } else if (line.equals("공항철도")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line12);
        } else if (line.equals("신분당선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line13);
        } else if (line.equals("우이신설선")) {
            image  = BitmapFactory.decodeResource(res, R.drawable.line14);
        } else if(line == null){
            image  = BitmapFactory.decodeResource(res, R.drawable.line24);;
        }
        return image;
    }
}
