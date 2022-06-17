package com.example.realtimesubway.PositionSection.AllStation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.realtimesubway.R;

import java.util.ArrayList;

public class LineAllStationAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<LineAllStationData> lineAllStationData;
    public LineAllStationAdapter(Context context1, ArrayList<LineAllStationData> data){
        mContext = context1;
        lineAllStationData = data;
        layoutInflater = LayoutInflater.from(mContext);
    }
    @Override
    public int getCount() {
        return lineAllStationData.size();
    }

    @Override
    public Object getItem(int position) {
        return lineAllStationData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.position_item_stationlinemap, null);

        TextView lineText = (TextView) view.findViewById(R.id.position_item_lineText);

        lineText.setText(lineAllStationData.get(position).getValue());
        return view;
    }
}
