package com.example.realtimesubway.ArrivalSection.Data.Line;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import com.example.realtimesubway.R;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.List;

public class SubwayDataPrintViewPager extends AppCompatActivity implements View.OnClickListener {
    public static final String KEY_STATION_NAME = "stationname";
    public static final String KEY_STATION_LINES = "stationlines";
    public static final String KEY_LINECOUNT = "linecount";

    //Ui
    private ImageButton btn_favor;
    private TextView tvStationName;
    private ImageView firImage, secImage, thdImage;
    private TabLayout tablayout;

    private ViewPager2 viewpager2;
    private ViewPagerAdapter viewPagerAdapter;
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;

    ArrayList<String> stationLines;
    String stationName;
    int resId; // 변수명 참조해서 drawable에서 가져온 리소스의 아이디
    boolean isFavor = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewpager_test);

        init();
        addEventListener();
    }

    private void init() {
        stationName = getIntent().getStringExtra(KEY_STATION_NAME);
        stationLines = getIntent().getStringArrayListExtra(KEY_STATION_LINES);

        pref = getSharedPreferences("favor", MODE_PRIVATE);
        editor = pref.edit();

        btn_favor = findViewById(R.id.btn_select);
        btn_favor.setOnClickListener(this);
        firImage = findViewById(R.id.firstImage);
        secImage = findViewById(R.id.secondImage);
        thdImage = findViewById(R.id.thirdImage);
        tvStationName = findViewById(R.id.textStNm);
        tvStationName.setText(stationName +" 역"); // 역이름 출력

        tablayout = findViewById(R.id.layout_tab);
        viewpager2 = findViewById(R.id.viewpager2_container);

        viewPagerAdapter = new ViewPagerAdapter(this, stationLines.size(), stationLines, stationName);
        viewpager2.setAdapter(viewPagerAdapter);

        isFavor = pref.getBoolean(stationName, false);
        if(isFavor) {
            btn_favor.setBackground(getDrawable(R.drawable.selected));
        } else {
            btn_favor.setBackground(getDrawable(R.drawable.select));
        }

        // 선택된 역의 호선 아이콘 표시
        for(String line : stationLines) {
            String imageKey = drawableSort(line);
            resId = getResources().getIdentifier("drawable/" + imageKey, "drawable", this.getPackageName());
            if(firImage.getVisibility() != View.VISIBLE) {
                firImage.setVisibility(View.VISIBLE);
                firImage.setImageResource(resId);
            } else {
                if(secImage.getVisibility() != View.VISIBLE) {
                    secImage.setVisibility(View.VISIBLE);
                    secImage.setImageResource(resId);
                } else {
                    if(thdImage.getVisibility() != View.VISIBLE) {
                        thdImage.setVisibility(View.VISIBLE);
                        thdImage.setImageResource(resId);
                    }
                }
            }
        }
    }

    private void addEventListener() {
        // Viewpager 후보
        final List<String> tabElement = new ArrayList<String>();
        for(String stationLine : stationLines) {
            tabElement.add(stationLine);
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

    @Override
    public void onClick(View view) {
        isFavor = pref.getBoolean(stationName, false);
        if(view == btn_favor) {
            if(isFavor) {
                btn_favor.setBackground(getDrawable(R.drawable.select));
                editor.remove(stationName);
            } else {
                btn_favor.setBackground(getDrawable(R.drawable.selected));
                editor.putBoolean(stationName, true);
            }
            editor.apply();
        }
    }

    private String drawableSort(String stationLine) { // ex) 01호선 -> line1로 변환
        switch (stationLine) {
            case "01호선":
                return "line1";
            case "02호선":
                return "line2";
            case "03호선":
                return "line3";
            case "04호선":
                return "line4";
            case "05호선":
                return "line5";
            case "06호선":
                return "line6";
            case "07호선":
                return "line7";
            case "08호선":
                return "line8";
            case "09호선":
                return "line9";
            case "수인분당선":
                return "line10";
            case "경춘선":
                return "line11";
            case "공항철도":
                return "line12";
            case "신분당선":
                return "line13";
            case "우이신설선":
                return "line14";
            default :
                return "";
        }
    }
}