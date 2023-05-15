package com.example.realtimesubway.PositionSection.LineList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.R;
import com.example.realtimesubway.data.Line;

import java.util.ArrayList;

public class LineListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<Line> mLineListData;
    private LayoutInflater mLayoutInflater;

    public LineListAdapter(Context context, ArrayList<Line> data) {
        mContext = context;
        mLineListData = data;
        mLayoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return mLineListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mLineListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = mLayoutInflater.inflate(R.layout.postioin_item_linelist, parent, false);
            holder = new ViewHolder();
            holder.imageView = convertView.findViewById(R.id.postion_item_lineImage);
            holder.lineText = convertView.findViewById(R.id.position_item_lineText);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Line data = mLineListData.get(position);
        holder.imageView.setImageResource(data.getImageResource());
        holder.lineText.setText(data.getName());

        return convertView;
    }

    static class ViewHolder {
        ImageView imageView;
        TextView lineText;
    }
}