package com.example.realtimesubway.ArrivalSection.Data.SearchFilter;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
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
        SearchItem selected = searchItemArrayList.get(position);
        holder.stationName.setText(selected.getStationName());
        Glide.with(activity).load(selected.getImage()).fitCenter().into(holder.imageView);
        Glide.with(activity).load(selected.getImage2()).fitCenter().into(holder.imageView2);
        Glide.with(activity).load(selected.getImage3()).fitCenter().into(holder.imageView3);
        Glide.with(activity).load(selected.getImage4()).fitCenter().into(holder.booleanSelect);
    }

    @Override
    public int getItemCount() {
        return searchItemArrayList != null ? searchItemArrayList.size() : 0;
    }

    public void  filterList(ArrayList<SearchItem> filteredList) {
        searchItemArrayList = filteredList;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView, imageView2, imageView3, booleanSelect;
        TextView stationName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            this.stationName=itemView.findViewById(R.id.stationName);
            this.imageView = itemView.findViewById(R.id.imagev1);
            this.imageView2 = itemView.findViewById(R.id.imagev2);
            this.imageView3 = itemView.findViewById(R.id.imagev3);
            this.booleanSelect = itemView.findViewById(R.id.booleanSelect);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
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
