package com.example.realtimesubway.SearchFilter;

import android.app.Activity;
import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimesubway.R;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.ViewHolder>{

    private OnItemClickListener mListener = null;
    ArrayList<SearchItem> searchItemArrayList;
    Activity activity;


    public SearchAdapter(ArrayList<SearchItem> searchItemArrayList, Activity activity) {
        this.searchItemArrayList = searchItemArrayList;
        this.activity = activity;
    }

    public interface OnItemClickListener {
        void onItemClick(View v, int postion);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        this.mListener = listener;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_station,null);
        ViewHolder viewHolder=new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        String test2 = searchItemArrayList.get(position).getStationName();

        holder.stationName.setText(test2);

    }

    @Override
    public int getItemCount() {
        return searchItemArrayList.size();
    }

    public void  filterList(ArrayList<SearchItem> filteredList) {
        searchItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView stationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.stationName=itemView.findViewById(R.id.stationName);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAbsoluteAdapterPosition();
                    if(pos != RecyclerView.NO_POSITION){
                        if(mListener !=null){
                            mListener.onItemClick(v, pos);
                        }
                    }
                }
            });
        }
    }
}
