package com.example.realtimesubway.Line;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.realtimesubway.MainActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewPagerAdapter extends FragmentStateAdapter {

    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return FragmentPractice.newInstance(position);
    }

    @Override
    public int getItemCount() {
        return 2;
    }
}
