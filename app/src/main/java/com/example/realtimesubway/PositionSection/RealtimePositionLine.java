package com.example.realtimesubway.PositionSection;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realtimesubway.ArrivalSection.Data.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.PositionSection.LineList.CallRetrofitGetApi;
import com.example.realtimesubway.PositionSection.AllStation.CallRetrofitGetApi2;
import com.example.realtimesubway.PositionSection.AllStation.LineAllStationData;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.Map;

public class RealtimePositionLine extends AppCompatActivity {
    public static final String KEY_LINENUM = "linenum";
    RetrofitApi retrofitApi;
    StationApi stationApi;
    String lineNum, lineNumYesZero;
    int position;
    ArrayList<LineAllStationData> lineAllStationData;
    Map<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_postion_line);
        position = getIntent().getIntExtra(KEY_LINENUM, 0);

        lineChange(position);

        String realtimePositionURL = "http://swopenapi.seoul.go.kr/api/subway/65425773516a6f6e36396452775575/json/realtimePosition/0/";
        String allStationURL = "http://openapi.seoul.go.kr:8088/635a446f6d6a6f6e3131304e52586866/json/SearchInfoBySubwayNameService/";
        // 실시간 위치정보
        BuildRetrofit buildRetrofit = new BuildRetrofit(realtimePositionURL);
        retrofitApi = buildRetrofit.retrofit.create(RetrofitApi.class);
        CallRetrofitGetApi callRetrofitGetApi = new CallRetrofitGetApi(retrofitApi, lineNum);
        // 전체 역 정보
        BuildRetrofit buildRetrofit2 = new BuildRetrofit(allStationURL);
        stationApi = buildRetrofit2.retrofit.create(StationApi.class);
        CallRetrofitGetApi2 callRetrofitGetApi2 = new CallRetrofitGetApi2(stationApi, lineNumYesZero); // 아니 얘가 도대체 왜 안받아와지는거임 이해가 안되네

        Log.d("test","test");


    }

    private void lineChange(int position) {
        if(position == 0){
            lineNum = "1호선";
            lineNumYesZero = "01호선";
        } else if(position == 1){
            lineNum = "2호선";
            lineNumYesZero = "02호선";
        } else if(position == 2){
            lineNum = "3호선";
            lineNumYesZero = "03호선";
        } else if(position == 3){
            lineNum = "4호선";
            lineNumYesZero = "04호선";
        } else if(position == 4){
            lineNum = "5호선";
            lineNumYesZero = "05호선";
        } else if(position == 5){
            lineNum = "6호선";
            lineNumYesZero = "06호선";
        } else if(position == 6){
            lineNum = "7호선";
            lineNumYesZero = "07호선";
        } else if(position == 7){
            lineNum = "8호선";
            lineNumYesZero = "08호선";
        } else if(position == 8){
            lineNum = "9호선";
            lineNumYesZero = "09호선";
        } else if(position == 9){
            lineNum = "수인분당선";
            lineNumYesZero = "수인분당선";
        } else if(position == 10){
            lineNum = "경춘선";
            lineNumYesZero = "경춘선";
        } else if(position == 11){
            lineNum = "공항철도";
            lineNumYesZero = "공항철도";
        } else if(position == 12){
            lineNum = "신분당선";
            lineNumYesZero = "신분당선";
        } else if(position == 13) {
            lineNum = "우이신설선";
            lineNumYesZero = "우이신설선";
        }
    }
}