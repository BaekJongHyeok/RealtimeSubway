package com.example.realtimesubway.Line;

import android.content.Intent;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.realtimesubway.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStateAdapter {
    int tabCount;
    ArrayList<String> stationLineName;
    String stationNm;

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity, int lineCount, ArrayList<String> stationLines, String stationName) {
        super(fragmentActivity);
        tabCount = lineCount;
        stationLineName = stationLines;
        stationNm = stationName;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position){
            case 0:
                return SubwayDataPrintFragment.newInstance(position, stationLineName.get(0), stationNm);
            case 1:
                return SubwayDataPrintFragment2.newInstance(position, stationLineName.get(1), stationNm);
            case 2:
                return SubwayDataPrintFragment3.newInstance(position, stationLineName.get(2), stationNm);
            default:
                return null;
        }
        //return SubwayDataPrintFragment.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return tabCount;
    }
}
