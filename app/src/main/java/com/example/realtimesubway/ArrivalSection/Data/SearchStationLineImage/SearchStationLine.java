package com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;

import com.example.realtimesubway.R;

public class SearchStationLine {
    public Bitmap image;
    private Context ctx;

    public SearchStationLine(Context ctx) {
        this.ctx = ctx;
    }

    public Bitmap search(String line) {
        if (line.equals("01호선")) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) ctx.getResources().getDrawable(R.drawable.line1);
            image = bitmapDrawable.getBitmap();
        } else if (line.equals("02호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line2);
        } else if (line.equals("03호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line3);
        } else if (line.equals("04호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line4);
        } else if (line.equals("05호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line5);
        } else if (line.equals("06호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line6);
        } else if (line.equals("07호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line7);
        } else if (line.equals("08호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line8);
        } else if (line.equals("09호선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line9);
        } else if (line.equals("수인분당선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line10);
        } else if (line.equals("경춘선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line11);
        } else if (line.equals("공항철도")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line12);
        } else if (line.equals("신분당선")) {
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line13);
        } else if (line.equals("우이신설선")) {
            BitmapDrawable bitmapDrawable = (BitmapDrawable) ctx.getResources().getDrawable(R.drawable.line14);
            image = bitmapDrawable.getBitmap();
        } else if(line == null){
            image  = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.line24);;
        }
        return image;
    }
}
