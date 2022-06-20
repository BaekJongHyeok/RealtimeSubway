package com.example.realtimesubway.PositionSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.R;

import java.util.ArrayList;

public class AllStationAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<AllStationData> allStationData;

    public AllStationAdapter(Context context, ArrayList<AllStationData> data){
        mContext = context;
        allStationData = data;
        layoutInflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return allStationData.size();
    }

    @Override
    public Object getItem(int position) {
        return allStationData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.position_item_stationlinemap, null);
        TextView text_lineList = (TextView)view.findViewById(R.id.Text_lineList);
        ImageView image_status = (ImageView) view.findViewById(R.id.image);
        //TextView text_downLine = (TextView)view.findViewById(R.id.Text_downline);
        text_lineList.setText(allStationData.get(position).getStationName());
        AllStationData path = allStationData.get(position);

        for(int i=0; i<path.getUpdnLine().size(); i++){
            if(path.getUpdnLine().get(i).getStatnNm().equals(path.getStationName())){
                image_status.setImageResource(R.drawable.upline);
            }
        }

       // text_downLine.setText(allStationData.get(position).getDownLine().getTrainSttus());
        return view;
    }
}
