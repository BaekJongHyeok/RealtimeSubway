package com.example.realtimesubway.PositionSection;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.List;

public class AllStationAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<AllStationData> allStationData;
    List<PositionData> upPosList, downPosList;
    String lineNume = null;
    CustomDialog upCustomDialog, downCustomDialog;

    public AllStationAdapter(Context context, ArrayList<AllStationData> data, String lineNum, List<PositionData> upPositionList, List<PositionData> downPositionList){
        mContext = context;
        allStationData = data;
        layoutInflater = LayoutInflater.from(context);
        lineNume = lineNum;
        upPosList = upPositionList;
        downPosList = downPositionList;
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
        ImageButton image_statusUp = (ImageButton) view.findViewById(R.id.imageUp);
        ImageButton image_statusDown = (ImageButton) view.findViewById(R.id.imageDown);
        String confirmStatnNm = allStationData.get(position).getStationName();
    // ??????????????? ?????? ???????????? ??????
        text_boundary.setVisibility(View.INVISIBLE);

        if(lineNume.equals("1??????")){
            if(confirmStatnNm.equals(allStationData.get(0).getStationName())){
                text_boundary.setText(allStationData.get(0).getStationName() + " >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("??????")){
                text_boundary.setText("?????? >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("??????")){
                text_boundary.setText("?????? >> ?????????");
                text_boundary.setVisibility(View.VISIBLE);
            }
        } else if(lineNume.equals("5??????")){
            if(confirmStatnNm.equals(allStationData.get(0).getStationName())){
                text_boundary.setText(allStationData.get(0).getStationName() + " >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("???????????????")){
                text_boundary.setText("??????????????? >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("??????")){
                text_boundary.setText("?????? >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            }
        } else if(lineNume.equals("?????????")){
            if(confirmStatnNm.equals(allStationData.get(0).getStationName())){
                text_boundary.setText(allStationData.get(0).getStationName() + " >> ??????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("??????")){
                text_boundary.setText("?????? >> ?????????");
                text_boundary.setVisibility(View.VISIBLE);
            } else if(confirmStatnNm.equals("?????????")){
                text_boundary.setText("?????? >> ?????????");
                text_boundary.setVisibility(View.VISIBLE);
            }
        } else {
            if(confirmStatnNm.equals(allStationData.get(0).getStationName())){
                text_boundary.setText(allStationData.get(0).getStationName() + " >> " + allStationData.get(allStationData.size()-1).getStationName());
                text_boundary.setVisibility(View.VISIBLE);
            }
        }

    // ??? ??????
        text_lineList.setText(confirmStatnNm);
        AllStationData path = allStationData.get(position);

        for(int i=0; i<path.getUpLine().size(); i++){
            if(path.getUpLine().get(i).getStatnNm().equals(confirmStatnNm)){
                image_statusUp.setImageResource(R.drawable.updirection);
            }
        }

        for(int i=0; i<path.getDownLine().size(); i++){
            if(path.getDownLine().get(i).getStatnNm().equals(confirmStatnNm)){
                image_statusDown.setImageResource(R.drawable.downdirection);
            }
        }
        image_statusUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                upCustomDialog = new CustomDialog(mContext);
                upCustomDialog.callFunction(allStationData, upPosList, position);;
            }
        });

        image_statusDown.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                downCustomDialog = new CustomDialog(mContext);
                downCustomDialog.callFunction(allStationData, downPosList, position);
            }
        });

        return view;
    }
}
