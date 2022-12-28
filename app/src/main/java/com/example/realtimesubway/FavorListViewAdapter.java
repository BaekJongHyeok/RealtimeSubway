package com.example.realtimesubway;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;

import java.util.ArrayList;

public class FavorListViewAdapter extends BaseAdapter {
    private Context ctx = null;
    private LayoutInflater mLayoutInflater = null;
    private ArrayList<SearchItem> sample;


    public FavorListViewAdapter(Context context, ArrayList<SearchItem> data) {
        ctx = context;
        sample = data;
        mLayoutInflater = LayoutInflater.from(ctx);
    }

    @Override
    public int getCount() {
        return sample.size();
    }

    @Override
    public Object getItem(int position) {
        return sample.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View converView, ViewGroup parent) {
        View view = mLayoutInflater.inflate(R.layout.item_favorlist, null);
        ArrayList<Bitmap> imageList = sample.get(position).getImageList();

        TextView tvStName = view.findViewById(R.id.tvStationName);
        ImageView ivFirst = view.findViewById(R.id.imagev1);
        ImageView ivSecond = view.findViewById(R.id.imagev2);
        ImageView ivThird = view.findViewById(R.id.imagev3);


        tvStName.setText(sample.get(position).getStationName());
        ivFirst.setImageBitmap(imageList.get(0));

        return view;
    }
}
