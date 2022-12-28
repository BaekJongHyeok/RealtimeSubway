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
    String imageKey; // 01호선 -> line1로 변환된 이름 받아올 string
    String resName, packName, stationName;
    int resId; // 변수명 참조해서 drawable에서 가져온 리소스의 아이디
    int lineCount;
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
        lineCount = getIntent().getIntExtra(KEY_LINECOUNT,0);

        pref = getSharedPreferences("favor", MODE_PRIVATE);
        editor = pref.edit();

        btn_favor = findViewById(R.id.btn_select);
        btn_favor.setOnClickListener(this);
        firImage = findViewById(R.id.firstImage);
        secImage = findViewById(R.id.secondImage);
        thdImage = findViewById(R.id.thirdImage);
        tvStationName = findViewById(R.id.textStNm);
        tvStationName.setText(stationName +" 역"); // 역이름 출력

        isFavor = pref.getBoolean(stationName, false);
        if(isFavor) {
            btn_favor.setBackground(getDrawable(R.drawable.selected));
        } else {
            btn_favor.setBackground(getDrawable(R.drawable.select));
        }
    }

    private void addEventListener() {
        // 선택된 역의 호선 아이콘 표시
        if(lineCount == 1){ // 1개 노선일때
            drawableSort(stationLines.get(0));
            resName = "drawable/"+imageKey;
            packName = this.getPackageName();
            resId = getResources().getIdentifier(resName, "drawable", packName);
            secImage.setImageResource(resId);
        } else if(lineCount == 2){ // 2개 노선일 때
            for(int i=0; i<2; i++){
                drawableSort(stationLines.get(i));
                resName = "drawable/"+imageKey;
                packName = this.getPackageName();
                resId = getResources().getIdentifier(resName, "drawable", packName);
                if(i == 0){
                    firImage.setImageResource(resId);
                } else if(i == 1){
                    thdImage.setImageResource(resId);
                }
            }
        } else if(lineCount == 3){ // 3개 노선일 때
            for(int i=0; i<3; i++){
                drawableSort(stationLines.get(i));
                resName = "drawable/"+imageKey;
                packName = this.getPackageName();
                resId = getResources().getIdentifier(resName, "drawable", packName);
                if(i == 0){
                    firImage.setImageResource(resId);
                } else if(i == 1){
                    secImage.setImageResource(resId);
                } else {
                    thdImage.setImageResource(resId);
                }
            }
        }

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

    private void drawableSort(String stationLine) { // ex) 01호선 -> line1로 변환
        if(stationLine.equals("01호선")){
            imageKey = "line1";
        } else if(stationLine.equals("02호선")){
            imageKey = "line2";
        } else if(stationLine.equals("03호선")){
            imageKey = "line3";
        } else if(stationLine.equals("04호선")){
            imageKey = "line4";
        } else if(stationLine.equals("05호선")){
            imageKey = "line5";
        } else if(stationLine.equals("06호선")){
            imageKey = "line6";
        } else if(stationLine.equals("07호선")){
            imageKey = "line7";
        } else if(stationLine.equals("08호선")){
            imageKey = "line8";
        } else if(stationLine.equals("09호선")){
            imageKey = "line9";
        } else if(stationLine.equals("수인분당선")){
            imageKey = "line10";
        } else if(stationLine.equals("경춘선")){
            imageKey = "line11";
        } else if(stationLine.equals("공항철도")){
            imageKey = "line12";
        } else if(stationLine.equals("신분당선")){
            imageKey = "line13";
        } else if(stationLine.equals("우이신설선")){
            imageKey = "line14";
        }
    }


}