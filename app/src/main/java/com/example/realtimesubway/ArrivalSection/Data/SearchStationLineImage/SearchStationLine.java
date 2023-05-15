package com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.example.realtimesubway.R;

public class SearchStationLine {
    private Bitmap image1, image2, image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,image13, image14;

    public SearchStationLine(Context ctx) {
        image1 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line1);
        image2 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line2);
        image3 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line3);
        image4 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line4);
        image5 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line5);
        image6 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line6);
        image7 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line7);
        image8 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line8);
        image9 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line9);
        image10 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line10);
        image11 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line11);
        image12 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line12);
        image13 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line13);
        image14 = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line14);

    }

    public Bitmap search(String line) {
        switch (line) {
            case "01호선":
                return image1;
            case "02호선":
                return image2;
            case "03호선":
                return image3;
            case "04호선":
                return image4;
            case "05호선":
                return image5;
            case "06호선":
                return image6;
            case "07호선":
                return image7;
            case "08호선":
                return image8;
            case "09호선":
                return image9;
            case "수인분당선":
                return image10;
            case "경춘선":
                return image11;
            case "공항철도":
                return image12;
            case "신분당선":
                return image13;
            case "우이신설선":
                return image14;
            default:
                return null;
        }
    }
}
