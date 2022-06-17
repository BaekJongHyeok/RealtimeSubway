package com.example.realtimesubway;

import android.util.Log;
import android.widget.Toast;

import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePositionList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;

public class CallRetrofitGetApi {
    public Map<String, String> sortedRealtimePositionMap;
    public CallRetrofitGetApi(RetrofitApi retrofitApi, String LineNum){
        retrofitApi.getRealtimePositionList(LineNum).enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, retrofit2.Response<RealtimePositionList> response) {
                if(response.isSuccessful()) {
                    RealtimePositionList result = response.body();
                    List<RealtimePosition> positionList =  result.getRealtimePositionList();

                    Mapping mapping = new Mapping(positionList);


                    Log.d("test",mapping.sortedRealtimePositionMap.toString());

                } else {
                }
            }
            @Override
            public void onFailure(Call<RealtimePositionList> call, Throwable t) {
            }
        });
    }
}
