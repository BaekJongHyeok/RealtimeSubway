package com.example.realtimesubway.Line;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.realtimesubway.Data.Station.Row;
import com.example.realtimesubway.Data.Station.StationLine;
import com.example.realtimesubway.MainActivity;
import com.example.realtimesubway.R;
import com.example.realtimesubway.Retrofit.StationRetrofit.RetrofitClient2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ViewpagerTest extends AppCompatActivity {

    TabLayout tablayout;
    private ViewPager2 viewpager2;
    private ViewPagerAdapter viewPagerAdapter;
    private RetrofitClient2 retrofitClient2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_test);

        tablayout = findViewById(R.id.layout_tab);
        viewpager2 = findViewById(R.id.viewpager2_container);

        viewPagerAdapter = new ViewPagerAdapter(this);
        viewpager2.setAdapter(viewPagerAdapter);

        // Viewpager 후보
        final List<String> tabElement = Arrays.asList("1호선", "2호선","3호선","4호선","5호선", "6호선","7호선","8호선","9호선");

        new TabLayoutMediator(tablayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull com.google.android.material.tabs.TabLayout.Tab tab, int position) {
                TextView textView = new TextView(ViewpagerTest.this);
                textView.setText(tabElement.get(position));
                tab.setCustomView(textView);
            }
        }).attach();
    }
}