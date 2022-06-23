package com.example.realtimesubway.PositionSection;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePositionList;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.AllStation;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.Row;
import com.example.realtimesubway.PositionSection.AllStation.Retrofit.AllStationApi;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RealtimePositionLine2 extends AppCompatActivity{
    public static final String KEY_LINENUM = "linenum";
    int position;

    SwipeRefreshLayout swipeRefreshLayout;
    TreeMap<String, String> sortedStationMap, reverseSortedStationMap;
    ArrayList<AllStationData> upAllStationData, downAllStationData;
    ListView upLineListView, downLineListView;
    String lineNum; // 변환받은 라인 이름을 넣어줄 변수
    List<PositionData> upPositionList;
    List<PositionData> downPositionList;
    Dialog dialog;
    CustomDialog upCustomDialog, downCustomDialog;
    Button btn_On, btn_Off, btn_refresh;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_realtime_postion_line);
        upLineListView = (ListView)findViewById(R.id.lineListView);
        downLineListView = (ListView)findViewById(R.id.lineListView2);
        btn_On = (Button)findViewById(R.id.btn_alramOn);
        btn_Off = (Button)findViewById(R.id.btn_alramOff);
        btn_refresh = (Button)findViewById(R.id.btn_refresh);
        position = getIntent().getIntExtra(KEY_LINENUM, 0);

        lineChange(position);
        allStation();

        btn_On.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    if (upCustomDialog.trainNo != null){
                        Intent intent = new Intent(getApplicationContext(), AlarmService.class);
                        intent.putExtra("lineNum",lineNum);
                        intent.putExtra("destination", upCustomDialog.destination);
                        intent.putExtra("trainNo", upCustomDialog.trainNo);
                        intent.putExtra("beforePosition", upCustomDialog.beforePosition);
                        startService(intent);
                    } else {
                        Toast.makeText(RealtimePositionLine2.this, "설정된 알람이 없습니다.",Toast.LENGTH_SHORT).show();
                    }
                } catch (Exception e){
                    Toast.makeText(RealtimePositionLine2.this, "설정된 알람이 없습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_Off.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AlarmService.class);
                stopService(intent);
            }
        });
        btn_refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lineChange(position);
                allStation();
            }
        });

    }

    private void allStation() {
        // 전체역 api 받아와서 리싸이클러뷰 출력
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/7859586b766a6f6e373963546f6b48/json/SearchSTNBySubwayLineInfo/1/100/%20/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        AllStationApi allStationApi = retrofit.create(AllStationApi.class);

        Call<AllStation> call = allStationApi.getAllStationData(lineNum);
        call.enqueue(new Callback<AllStation>() {
            @Override
            public void onResponse(Call<AllStation> call, Response<AllStation> response) {
                if(response.isSuccessful()){
                    AllStation result = response.body();
                    List<Row> rowList =  result.getSearchSTNBySubwayLineInfo().getRow();

                    // hashmap 형태로 만들기
                    HashMap<String, String> dic = new HashMap<String, String>();
                    for(int i=0; i<rowList.size(); i++){
                        String key = rowList.get(i).getFrCode();
                        String value = rowList.get(i).getStationNm();
                        dic.put(key,value);
                    }

                    // 맵 key 기준 정렬
                    sortedStationMap = new TreeMap<>(dic);

                    // 역순 정렬
                    reverseSortedStationMap = new TreeMap<>(Collections.reverseOrder());
                    reverseSortedStationMap.putAll(dic);

                    realtimePosition(lineNum);
                }
            }
            @Override
            public void onFailure(Call<AllStation> call, Throwable t) {
            }
        });
        // 전체역 api 받아와서 리싸이클러뷰 출력
    }

    // 리스트뷰 출력
    private void listviewPrint(Map<String,String> sortedMap, Map<String,String> reverseSortedMap) {
        upAllStationData = new ArrayList<AllStationData>();
        downAllStationData = new ArrayList<AllStationData>();

        Iterator<Map.Entry<String,String>> it = reverseSortedMap.entrySet().iterator();
        while(it.hasNext()){
            Map.Entry<String,String> entrySet = (Map.Entry<String, String>) it.next();
            upAllStationData.add(new AllStationData(entrySet.getKey(), entrySet.getValue(), upPositionList));
        }
        Iterator<Map.Entry<String,String>> it2 = sortedMap.entrySet().iterator();
        while(it2.hasNext()){
            Map.Entry<String,String> entrySet = (Map.Entry<String, String>) it2.next();
            downAllStationData.add(new AllStationData(entrySet.getKey(), entrySet.getValue(), downPositionList));
        }


        final AllStationAdapter allStationAdapter = new AllStationAdapter(this, upAllStationData,lineNum);
        final AllStationAdapter2 allStationAdapter2 = new AllStationAdapter2(this, downAllStationData,lineNum);
        upLineListView.setAdapter(allStationAdapter);
        downLineListView.setAdapter(allStationAdapter2);

        upLineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {        // 상행선 클릭시
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                upCustomDialog = new CustomDialog(RealtimePositionLine2.this);
                upCustomDialog.callFunction(upAllStationData, upPositionList, position);;
            }
        });
        downLineListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {        //하행선 클릭시
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {
                downCustomDialog = new CustomDialog(RealtimePositionLine2.this);
                downCustomDialog.callFunction(downAllStationData, downPositionList, position);
            }
        });
    }

    // 실시간 지하철 위치 api 받아와서 출력
    private void realtimePosition(String lineNum) {
        if(lineNum.equals("우이신설경전철")){
            lineNum = "우이신설선";
        }

        Retrofit realtimeRetrofit = new Retrofit.Builder()
                .baseUrl("http://swopenapi.seoul.go.kr/api/subway/65425773516a6f6e36396452775575/json/realtimePosition/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = realtimeRetrofit.create(RetrofitApi.class);
        Call<RealtimePositionList> realtimeCall = retrofitApi.getRealtimePositionList(lineNum);
        realtimeCall.enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, Response<RealtimePositionList> response) {
                if(response.isSuccessful()){
                    RealtimePositionList result2 = response.body();
                    List<RealtimePosition> realtimePositionList = result2.getRealtimePositionList();

                    upPositionList = new ArrayList<>(); // 상행열차 담을 리스트
                    downPositionList = new ArrayList<>(); // 하행열차 담을 리스트


                    for(RealtimePosition position: realtimePositionList) {
                        // 상행이거나 외선일 경우
                        if (position.getUpdnLine().equals("0")) {
                            PositionData arrTemp = new PositionData();
                            arrTemp.setTrainNo(position.getTrainNo());
                            arrTemp.setStatnNm(position.getStatnNm());
                            arrTemp.setUpdnLine(position.getUpdnLine());
                            arrTemp.setTrainSttus(position.getTrainSttus());
                            arrTemp.setDirectAt(position.getDirectAt());
                            arrTemp.setStatnTnm(position.getStatnTnm());
                            upPositionList.add(arrTemp);
                        } else {
                            PositionData arrTemp = new PositionData();
                            arrTemp.setTrainNo(position.getTrainNo());
                            arrTemp.setStatnNm(position.getStatnNm());
                            arrTemp.setUpdnLine(position.getUpdnLine());
                            arrTemp.setTrainSttus(position.getTrainSttus());
                            arrTemp.setDirectAt(position.getDirectAt());
                            arrTemp.setStatnTnm(position.getStatnTnm());
                            downPositionList.add(arrTemp);
                        }
                    }
                    listviewPrint(sortedStationMap, reverseSortedStationMap);
                }
            }
            @Override
            public void onFailure(Call<RealtimePositionList> call, Throwable t) {

            }
        });
        // 실시간 지하철 위치 api 받아와서 출력
    }

    private void lineChange(int position) {
        if(position == 1){
            lineNum = "1호선";
        } else if(position == 2){
            lineNum = "2호선";
        } else if(position == 3){
            lineNum = "3호선";
        } else if(position == 4){
            lineNum = "4호선";
        } else if(position == 5){
            lineNum = "5호선";
        } else if(position == 6){
            lineNum = "6호선";
        } else if(position == 7){
            lineNum = "7호선";
        } else if(position == 8){
            lineNum = "8호선";
        } else if(position == 9){
            lineNum = "9호선";
        } else if(position == 10){
            lineNum = "수인분당선";
        } else if(position == 11){
            lineNum = "경춘선";
        } else if(position == 12){
            lineNum = "공항철도";
        } else if(position == 13){
            lineNum = "신분당선";
        } else {
            lineNum = "우이신설경전철";
        }

    }

}