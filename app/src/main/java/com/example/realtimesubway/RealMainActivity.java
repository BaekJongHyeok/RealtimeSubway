package com.example.realtimesubway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.realtimesubway.ArrivalSection.Data.Line.SubwayDataPrintViewPager;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.Row;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchAdapter;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.SearchStationLine;
import com.example.realtimesubway.Common.Const;
import com.example.realtimesubway.network.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;

public class RealMainActivity extends AppCompatActivity {
    private String TAG = RealMainActivity.class.getSimpleName();

    // UI
    private EditText etSearch;
    private RecyclerView rvSearchResult;
    private DividerItemDecoration itemDivider;

    private List<SearchItem> searchItemList, filteredList;
    private SearchAdapter searchAdapter;
    private LinearLayoutManager lyManager;

    //Retrofit
    private StationApi apiService;

    private Map<String, ArrayList<String>> sortedMap;

    private String stationName;

    private ArrayList<Bitmap> imageList;

    SearchStationLine searchStationLine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        init();

        addEventListener();
    }

    private void init() {
        searchStationLine = new SearchStationLine(getApplicationContext());

        etSearch = (EditText) findViewById(R.id.et_search);
        rvSearchResult = (RecyclerView) findViewById(R.id.rv_searchResult);

        filteredList = new ArrayList<>();
        searchItemList = new ArrayList<>();
        searchAdapter = new SearchAdapter(searchItemList, this);

        // recyclerView 세팅
        lyManager = new LinearLayoutManager(this);
        rvSearchResult.setLayoutManager(lyManager);
        rvSearchResult.setAdapter(searchAdapter);
        itemDivider = new DividerItemDecoration(rvSearchResult.getContext(), new LinearLayoutManager(this).getOrientation());
        rvSearchResult.addItemDecoration(itemDivider);

        // 지하철 역 전체 정보 api 세팅
        apiService = RetrofitClient.getClient(Const.SUBWAY_STATION_URL).create(StationApi.class);
        getStationInfo();
    }

    private void addEventListener() {
        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "beforeTextChanged: dddd");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                Log.d(TAG, "onTextChanged: ddd");
            }

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = etSearch.getText().toString();
            }
        });

        // 리스트 아이템 클릭 시 해당 역 정보로 이동
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String stationName = "";
                if(filteredList.size() == 0) {
                    stationName = searchItemList.get(position).getStationName();
                } else {
                    stationName = filteredList.get(position).getStationName();
                }
                Intent intent = new Intent(getApplicationContext(), SubwayDataPrintViewPager.class);
                intent.putExtra(SubwayDataPrintViewPager.KEY_STATION_NAME, stationName);
                intent.putExtra(SubwayDataPrintViewPager.KEY_LINECOUNT, sortedMap.get(stationName).size());
                intent.putStringArrayListExtra(SubwayDataPrintViewPager.KEY_STATION_LINES, sortedMap.get(stationName));
                startActivity(intent);
                etSearch.setText(null);
            }
        });

    }

    private void getStationInfo() {
        retrofit2.Call<SearchInfoBySubwayNameService> call = apiService.getStationData(1000);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(retrofit2.Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if(response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList =  result2.getSearchInfoBySubwayNameService().getRow();

                    // hashmap 형태로 만들기
                    HashMap<String, ArrayList<String>> dic = new HashMap<String, ArrayList<String>>();
                    for(int i=0; i<rowList.size(); i++){
                        String key = rowList.get(i).getStationNm();
                        String lineNumValue = rowList.get(i).getLineNum();
                        ArrayList<String> list = new ArrayList<String>();
                        if(dic.containsKey(key)){
                            list = dic.get(key);
                            if(lineNumValue.equals("인천선") || lineNumValue.equals("인천2호선") || lineNumValue.equals("경의선") || lineNumValue.equals("경강선")
                                    || lineNumValue.equals("신림선") || lineNumValue.equals("서해선") || lineNumValue.equals("김포도시철도") || lineNumValue.equals("용인경전철")
                                    || lineNumValue.equals("의정부경전철")){
                                // api에 데이터가 없는 노선은 삭제
                            } else{
                                list.add(lineNumValue);
                            }
                        } else {
                            if(lineNumValue.equals("인천선") || lineNumValue.equals("인천2호선") || lineNumValue.equals("경의선") || lineNumValue.equals("경강선")
                                    || lineNumValue.equals("신림선") || lineNumValue.equals("서해선") || lineNumValue.equals("김포도시철도") || lineNumValue.equals("용인경전철")
                                    || lineNumValue.equals("의정부경전철")){
                                // api에 데이터가 없는 노선은 삭제
                            } else{
                                list.add(lineNumValue);
                            }
                        }
                        dic.put(key,list);
                    }

                    // 맵 key 기준 정렬
                    sortedMap = new TreeMap<>(dic);

                    // 전철역 뿌려주기
                    for(Map.Entry<String, ArrayList<String>> entry: sortedMap.entrySet()){
                        stationName = entry.getKey();
                        ArrayList<String> lines = entry.getValue();

                        imageList = new ArrayList<>();
                        if(lines.size() > 0){
                            for(String line : lines) {
                                Bitmap image = searchStationLine.search(line);
                                imageList.add(image);
                            }

                            searchItemList.add(new SearchItem(imageList, stationName));
                        }
                    }
                } else {
                    Toast.makeText(getApplicationContext() ,"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {
                Log.d("test", "error");
            }
        });
    }
}