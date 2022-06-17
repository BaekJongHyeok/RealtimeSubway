package com.example.realtimesubway.ArrivalSection.Data.Line;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

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
