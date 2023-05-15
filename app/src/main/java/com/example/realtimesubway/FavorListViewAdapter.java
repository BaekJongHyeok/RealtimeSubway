package com.example.realtimesubway;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;

import java.util.ArrayList;
import java.util.Map;

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
        SingerViewer singerViewer = new SingerViewer(ctx, sample);
        singerViewer.setItem(sample.get(position));

        return singerViewer;
    }

    public void removeByPosition(int position) {
        SearchItem item = sample.get(position);
        sample.remove(item);
        notifyDataSetChanged();
    }
}
