package com.example.realtimesubway.PositionSection.LineList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.realtimesubway.R;

import java.util.ArrayList;

public class LineListAdapter extends BaseAdapter {
    Context mContext = null;
    LayoutInflater layoutInflater = null;
    ArrayList<LineListData> lineListData;
    public LineListAdapter(Context context, ArrayList<LineListData> data){
        mContext = context;
        lineListData = data;
        layoutInflater = LayoutInflater.from(mContext);
    }

    @Override
    public int getCount() {
        return lineListData.size();
    }

    @Override
    public Object getItem(int position) {
        return lineListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = layoutInflater.inflate(R.layout.postioin_item_linelist, null);

        ImageView imageView = (ImageView) view.findViewById(R.id.postion_item_lineImage);
        TextView lineText = (TextView) view.findViewById(R.id.position_item_lineText);

        imageView.setImageResource(lineListData.get(position).getLineImage());
        lineText.setText(lineListData.get(position).getLineNm());
        return view;
    }
}
