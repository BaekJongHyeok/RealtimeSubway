package com.example.realtimesubway.Line;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.realtimesubway.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SubwayDataPrintViewPager extends AppCompatActivity {
    public static final String KEY_STATION_NAME = "stationname";
    public static final String KEY_STATION_LINES = "stationlines";
    public static final String KEY_LINECOUNT = "linecount";
    String stationName;
    ArrayList<String> stationLines;
    TabLayout tablayout;
    int lineCount;
    private TextView textView;
    private ViewPager2 viewpager2;
    private ViewPagerAdapter viewPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_test);
        textView = (TextView) findViewById(R.id.textStNm);
        stationName = getIntent().getStringExtra(KEY_STATION_NAME);
        stationLines = getIntent().getStringArrayListExtra(KEY_STATION_LINES);
        lineCount = getIntent().getIntExtra(KEY_LINECOUNT,0);

        textView.setText(stationName +" 역");
        tablayout = findViewById(R.id.layout_tab);
        viewpager2 = findViewById(R.id.viewpager2_container);

        viewPagerAdapter = new ViewPagerAdapter(this, lineCount, stationLines, stationName);
        viewpager2.setAdapter(viewPagerAdapter);


        // Viewpager 후보
        final List<String> tabElement = new ArrayList<String>();
        for(int i=0; i<stationLines.size(); i++){
            tabElement.add(stationLines.get(i));
        }

        new TabLayoutMediator(tablayout, viewpager2, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                TextView textView = new TextView(SubwayDataPrintViewPager.this);
                textView.setText(tabElement.get(position));
                tab.setCustomView(textView);
            }
        }).attach();
    }
}