package com.example.realtimesubway.PositionSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.PositionSection.AllStationData;
import com.example.realtimesubway.R;

import java.util.ArrayList;

public class AllStationAdapter2 extends BaseAdapter {
    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<AllStationData> allStationData;
    String lineNume = null;

    public AllStationAdapter2(Context context, ArrayList<AllStationData> data, String lineNum){
        mContext = context;
        allStationData = data;
        layoutInflater = LayoutInflater.from(context);
        lineNume = lineNum;
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
        TextView text_boundary = (TextView)view.findViewById(R.id.subwayLine_boundary);
        ImageView image_status = (ImageView) view.findViewById(R.id.image);

     // 분기점마다 어느 방면인지 출력
        text_boundary.setVisibility(View.INVISIBLE);
        String confirmStatnNm = allStationData.get(position).getStationName();
        if(lineNume.equals("1호선") || lineNume.equals("2호선") || lineNume.equals("5호선") || lineNume.equals("경춘선")){

        } else {
            if(confirmStatnNm.equals(allStationData.get(0).getStationName())){
                text_boundary.setText(allStationData.get(0).getStationName() + " >> " + allStationData.get(allStationData.size()-1).getStationName());
                text_boundary.setVisibility(View.VISIBLE);
            }
        }

    // 역 출력
        text_lineList.setText(confirmStatnNm);
        AllStationData path = allStationData.get(position);

        for(int i=0; i<path.getUpdnLine().size(); i++){
            if(path.getUpdnLine().get(i).getStatnNm().equals(confirmStatnNm)){
                if(lineNume.equals("1호선")){
                    image_status.setImageResource(R.drawable.line1);
                } else if(lineNume.equals("2호선")){
                    image_status.setImageResource(R.drawable.line2);
                } else if(lineNume.equals("3호선")){
                    image_status.setImageResource(R.drawable.line3);
                } else if(lineNume.equals("4호선")){
                    image_status.setImageResource(R.drawable.line4);
                } else if(lineNume.equals("5호선")){
                    image_status.setImageResource(R.drawable.line5);
                } else if(lineNume.equals("6호선")){
                    image_status.setImageResource(R.drawable.line6);
                } else if(lineNume.equals("7호선")){
                    image_status.setImageResource(R.drawable.line7);
                } else if(lineNume.equals("8호선")){
                    image_status.setImageResource(R.drawable.line8);
                } else if(lineNume.equals("9호선")){
                    image_status.setImageResource(R.drawable.line9);
                } else if(lineNume.equals("수인분당선")){
                    image_status.setImageResource(R.drawable.line10);
                } else if(lineNume.equals("경춘선")){
                    image_status.setImageResource(R.drawable.line11);
                } else if(lineNume.equals("공항철도")){
                    image_status.setImageResource(R.drawable.line12);
                } else if(lineNume.equals("신분당선")){
                    image_status.setImageResource(R.drawable.line13);
                } else {
                    image_status.setImageResource(R.drawable.line14);
                }
            }
        }
        return view;
    }
}
