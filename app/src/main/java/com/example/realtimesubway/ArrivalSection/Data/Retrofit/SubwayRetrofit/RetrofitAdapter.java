package com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimesubway.ArrivalSection.Data.Subway.PositionData;
import com.example.realtimesubway.R;

import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {
    private Context c;
    private List<PositionData> positionData;

    public RetrofitAdapter(Context c,List<PositionData> dataList){
        this.c = c;
        this.positionData = dataList;
    }



    @NonNull
    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(c).inflate(R.layout.item_data,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        PositionData getPosition = positionData.get(position);
        holder.subwayNm.setText(getPosition.getSubwayNm());
        holder.statnNm.setText(getPosition.getStatnNm());
        holder.recptnDt.setText(getPosition.getRecptnDt());

        //도착 여부
        switch (getPosition.getTrainSttus()){
            case "0":
                holder.trainSttus.setText("현재 열차 상태 : 진입");
                break;
            case "1":
                holder.trainSttus.setText("현재 열차 상태 : 도착");
                break;
            default:
                holder.trainSttus.setText("현재 열차 상태 : 출발");
                break;
        }

        //급행 여부
        switch (getPosition.getDirectAt()){
            case "0":
                holder.directAt.setText("완행");
                break;
            case "1":
                holder.directAt.setText("[급행]");
                break;
        }
    }

    @Override
    public int getItemCount(){
        return positionData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView subwayNm, statnNm, recptnDt, trainSttus, directAt;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            subwayNm = itemView.findViewById(R.id.trainLineNm);
            trainSttus = itemView.findViewById(R.id.arvlMsg2);
        }
    }
}
