package com.example.realtimesubway.Retrofit.StationRetrofit;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.realtimesubway.Data.Station.Row;
import com.example.realtimesubway.Data.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.Data.Station.StationLine;
import com.example.realtimesubway.Data.Subway.Arrival;
import com.example.realtimesubway.Line.ViewpagerTest;
import com.example.realtimesubway.MainActivity;
import com.example.realtimesubway.R;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient2 extends AppCompatActivity {
    public static Context Context1;    public List<StationLine> stationline;
    String value = ((MainActivity)MainActivity.mContext).name;
    public int linecount = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrofitclient2);

        // 한글 url encoding
        String str = value;
        String str2 = java.net.URLEncoder.encode(new String(str.getBytes(StandardCharsets.UTF_8)));


        // Retrofit 사용 api 값 가져오기
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/635a446f6d6a6f6e3131304e52586866/json/SearchInfoBySubwayNameService/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OkHttpClient client = new OkHttpClient().newBuilder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                }
        ).build();

        StationApi stationApi = retrofit.create(StationApi.class);
        // 연결
        Call<SearchInfoBySubwayNameService> call = stationApi.getStationData(str);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if(response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList =  result2.getSearchInfoBySubwayNameService().getRow();

                    stationline = new ArrayList<>();

                    StationLine datalist = new StationLine();
                    for (int i=0; i<rowList.size(); i++){
                        //arrivals.add(new StationLine(rowList.get(i).getLineNum()));
                        datalist.setLineNum(rowList.get(i).getLineNum());
                        stationline.add(datalist);
                        linecount++;
                    }
                    Log.d("test",stationline.toString());
                } else {
                    Toast.makeText(RetrofitClient2.this,"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {
                Log.d("test", "error");
            }
        });

        Context1 = this;
    }

}
