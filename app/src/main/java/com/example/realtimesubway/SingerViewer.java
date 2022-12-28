package com.example.realtimesubway;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;

import java.util.ArrayList;

public class SingerViewer extends LinearLayout {
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<SearchItem> sample;

    TextView tvStName;
    ImageView ivFirst;
    ImageView ivSecond;
    ImageView ivThird;

    public SingerViewer(Context ctx,  ArrayList<SearchItem> data) {
        super(ctx);
        sample = data;
        mLayoutInflater = LayoutInflater.from(ctx);

        init(ctx);
    }

    public void init(Context ctx) {
        LayoutInflater inflater = (LayoutInflater) ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.item_favorlist,this,true);

        tvStName = (TextView) findViewById(R.id.tvStationName);
        ivFirst = (ImageView) findViewById(R.id.imagev1);
        ivSecond = (ImageView) findViewById(R.id.imagev2);
        ivThird = (ImageView) findViewById(R.id.imagev3);
    }

    public void setItem(SearchItem item) {
        ArrayList<Bitmap> imageList = item.getImageList();
        if(imageList != null){
            int listSize = imageList.size();

            if(listSize > 0) {
                ivFirst.setImageBitmap(imageList.get(0));
                if(listSize > 1) {
                    ivSecond.setImageBitmap(imageList.get(1));
                    if(listSize > 2){
                        ivThird.setImageBitmap(imageList.get(2));
                    }
                }
            }

            tvStName.setText(item.getStationName());
        }
    }
}
