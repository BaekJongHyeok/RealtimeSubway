package com.example.realtimesubway.Retrofit.SubwayRetrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimesubway.Data.Subway.Arrival;
import com.example.realtimesubway.R;

import java.util.List;

public class RetrofitAdapter extends RecyclerView.Adapter<RetrofitAdapter.MyViewHolder> {

    private Context c;
    private List<Arrival> dataList;

    public RetrofitAdapter(Context c,List<Arrival> dataList){
        this.c = c;
        this.dataList = dataList;
    }



    @NonNull
    @Override
    public RetrofitAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(c).inflate(R.layout.item_data,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.trainLineNm.setText(dataList.get(position).getTrainLineNm());
        holder.subwayHeading.setText("타는 곳 : " + dataList.get(position).getSubwayHeading());
        holder.bstatnNm.setText("종착역 : " + dataList.get(position).getBstatnNm());
        holder.arvlMsg2.setText(dataList.get(position).getArvlMsg2());
        holder.arvlMsg3.setText("현재위치 : " + dataList.get(position).getArvlMsg3());

    }

    @Override
    public int getItemCount(){
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView trainLineNm;
        TextView subwayHeading;
        TextView bstatnNm;
        TextView arvlMsg2;
        TextView arvlMsg3;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);

            trainLineNm = itemView.findViewById(R.id.trainLineNm);
            subwayHeading = itemView.findViewById(R.id.subwayHeading);
            bstatnNm = itemView.findViewById(R.id.bstatnNm);
            arvlMsg2 = itemView.findViewById(R.id.arvlMsg2);
            arvlMsg3 = itemView.findViewById(R.id.arvlMsg3);
        }
    }
}
