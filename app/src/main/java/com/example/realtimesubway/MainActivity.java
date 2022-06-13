package com.example.realtimesubway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realtimesubway.Data.Station.Row;
import com.example.realtimesubway.Data.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.Line.ViewpagerTest;
import com.example.realtimesubway.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.SearchFilter.SearchAdapter;
import com.example.realtimesubway.SearchFilter.SearchItem;

import java.io.IOException;
import java.security.KeyStore;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {
    ArrayList<SearchItem> searchItemArrayList,filteredList;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static Context mContext;
    public String name;
    public List<Map<String, Object>> listmap;
    public Map<String, Object> map;
    public int linecount;
    private Retrofit retrofit;
    private StationApi stationApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText editText = (EditText) findViewById(R.id.edit);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        // 검색 필터링
        filteredList=new ArrayList<>();
        searchItemArrayList=new ArrayList<>();
        searchAdapter = new SearchAdapter(searchItemArrayList,this);

        // recyclerview
        linearLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(searchAdapter);
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(this).getOrientation());
        recyclerView.addItemDecoration(dividerDecoration);


        // 지하철 역 전체 정보
        buildRetrofit();
        createRetrofitService();
        callRetrofitGetApi();


        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = editText.getText().toString();
                searchFilter(searchText);
            }
        });


        // 클릭시 해당 역 정보로 이동
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int postion) {
                name = searchItemArrayList.get(postion).getStationName();
                Intent intent = new Intent(MainActivity.this, ViewpagerTest.class);
                startActivity(intent);
                editText.setText(null);
            }
        });
        mContext = this;
    }

    // 필터링 함수
    public void searchFilter(String searchText) {
        filteredList.clear();

        for (int i = 0; i < searchItemArrayList.size(); i++) {
            if (searchItemArrayList.get(i).getStationName().toLowerCase().contains(searchText.toLowerCase())) {
                filteredList.add(searchItemArrayList.get(i));
            }
        }
        searchAdapter.filterList(filteredList);
    }

    private void buildRetrofit(){
        // Retrofit 사용 api 값 가져오기
        retrofit = new Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/635a446f6d6a6f6e3131304e52586866/json/SearchInfoBySubwayNameService/")
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
    }


    private void createRetrofitService(){
        stationApi = retrofit.create(StationApi.class);
    }

    public void callRetrofitGetApi(){
        Call<SearchInfoBySubwayNameService> call = stationApi.getStationData(1000);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if(response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList =  result2.getSearchInfoBySubwayNameService().getRow();
                    listmap = new ArrayList<Map<String, Object>>();
                    for(int i = 0; i< rowList.size(); i++){
                        map = new HashMap<String, Object>();
                        map.put(rowList.get(i).getStationNm(), rowList.get(i).getLineNum());
                        listmap.add(map);
                    }


                    // 전철역 뿌려주기
                    for(Map<String, Object> map : listmap){
                        for(Map.Entry<String, Object> entry: map.entrySet()){
                            String key = entry.getKey();
                            Object value = entry.getValue();
                            searchItemArrayList.add(new SearchItem(key));
                            searchAdapter.notifyDataSetChanged();

                        }
                    }
                    Log.d("test", listmap.toString());

                } else {
                    Toast.makeText(MainActivity.this ,"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {
                Log.d("test", "error");
            }
        });

    }

}