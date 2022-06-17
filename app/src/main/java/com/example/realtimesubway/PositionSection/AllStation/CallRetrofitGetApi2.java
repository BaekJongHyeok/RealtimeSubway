package com.example.realtimesubway.PositionSection.AllStation;

import android.util.Log;
import android.widget.Toast;

import com.example.realtimesubway.ArrivalSection.Data.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline1;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline2;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline3;
import com.example.realtimesubway.ArrivalSection.Data.Station.Row;
import com.example.realtimesubway.ArrivalSection.Data.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePositionList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;

public class CallRetrofitGetApi2 {
    public Map<String, String> sortedAllStationMap;
    public CallRetrofitGetApi2(StationApi stationApi, String LineNum) {
        Call<SearchInfoBySubwayNameService> call = stationApi.getStationData(1000);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if (response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList = result2.getSearchInfoBySubwayNameService().getRow();

                    // hashmap 형태로 만들기
                    HashMap<String, String> dic = new HashMap<String, String>();
                    for (int i = 0; i < rowList.size(); i++) {
                        if(rowList.get(i).getLineNum().equals(LineNum)){
                            String stationIdkey = rowList.get(i).getFrCode();
                            String stationNmValue = rowList.get(i).getStationNm();
                            dic.put(stationIdkey, stationNmValue);
                        }
                    }

                    // 맵 key 기준 정렬
                    sortedAllStationMap = new TreeMap<>(dic);


                    Log.d("test","test");
                }
            }

            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {

            }
        });
    }
}
