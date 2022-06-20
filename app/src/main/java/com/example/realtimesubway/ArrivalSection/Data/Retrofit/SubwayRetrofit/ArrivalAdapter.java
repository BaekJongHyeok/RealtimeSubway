package com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.SubwayArrival.Arrival;
import com.example.realtimesubway.R;

import java.util.List;

public class ArrivalAdapter extends RecyclerView.Adapter<ArrivalAdapter.MyViewHolder> {
    private Context c;
    private List<Arrival> arrivalList;

    public ArrivalAdapter(Context c, List<Arrival> dataList){
        this.c = c;
        this.arrivalList = dataList;
    }



    @NonNull
    @Override
    public ArrivalAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = LayoutInflater.from(c).inflate(R.layout.item_data,null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Arrival getPosition = arrivalList.get(position);
        holder.trainLineNm.setText(getPosition.getTrainLineNm());
        holder.arvlMsg2.setText(getPosition.getArvlMsg2());
    }

    @Override
    public int getItemCount(){
        return arrivalList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView trainLineNm, arvlMsg2;

        public MyViewHolder(@NonNull View itemView){
            super(itemView);
            trainLineNm = itemView.findViewById(R.id.trainLineNm);
            arvlMsg2 = itemView.findViewById(R.id.arvlMsg2);
        }
    }
}
