package com.example.realtimesubway;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.Row;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.ArrivalSection.Data.Line.SubwayDataPrintViewPager;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchAdapter;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline1;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline2;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.Searchstationline3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ArrivalMainActivity extends AppCompatActivity {
    ArrayList<SearchItem> searchItemArrayList,filteredList;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static Context mContext;
    private Retrofit retrofit;
    private StationApi stationApi;
    public Map<String, ArrayList<String>> sortedMap;
    String key;
    public Bitmap image1,image2,image3,image4,image5,image6,image7,image8,image9,image10,image11,image12,
            image13,image14,image24;
    public Bitmap firstImage, secondImage, thirdImage, fourthImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_arrival_main);

        setImage();
    }

    @Override
    protected void onResume() {
        super.onResume();
        EditText editText = (EditText) findViewById(R.id.edit);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview);


        // 검색 필터링
        filteredList = new ArrayList<>();
        searchItemArrayList = new ArrayList<>();
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
                //여기서 리스트 포지션 초기화 시켜주면 됌

            }
        });


        // 클릭시 해당 역 정보로 이동
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String name;
                if(filteredList.size() == 0){
                    name = searchItemArrayList.get(position).getStationName();
                }else {
                    name = filteredList.get(position).getStationName();
                }
                Intent intent = new Intent(ArrivalMainActivity.this, SubwayDataPrintViewPager.class);
                intent.putExtra(SubwayDataPrintViewPager.KEY_STATION_NAME,name);
                intent.putStringArrayListExtra(SubwayDataPrintViewPager.KEY_STATION_LINES, sortedMap.get(name));
                intent.putExtra(SubwayDataPrintViewPager.KEY_LINECOUNT, sortedMap.get(name).size());
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
                        key = entry.getKey();
                        ArrayList<String> value = entry.getValue();

                        firstImage = null;
                        secondImage = null;
                        thirdImage = null;
                        fourthImage = null;

                        if(value.size() >0){
                            if(value.size() == 1 ){
                                Searchstationline1 searchstationline1 = new Searchstationline1(value.get(0));
                                firstImage = searchstationline1.firstImage;
                            } else if(value.size() == 2){
                                Searchstationline1 searchstationline1 = new Searchstationline1(value.get(0));
                                firstImage = searchstationline1.firstImage;
                                Searchstationline2 searchstationline2 = new Searchstationline2(value.get(1));
                                secondImage = searchstationline2.secondImage;
                            } else if(value.size() == 3){
                                Searchstationline1 searchstationline1 = new Searchstationline1(value.get(0));
                                firstImage = searchstationline1.firstImage;
                                Searchstationline2 searchstationline2 = new Searchstationline2(value.get(1));
                                secondImage = searchstationline2.secondImage;
                                Searchstationline3 searchstationline3 = new Searchstationline3(value.get(2));
                                thirdImage = searchstationline3.thirdImage;
                            }
                            searchItemArrayList.add(new SearchItem(firstImage, secondImage, thirdImage, image24, key));
                            searchAdapter.notifyDataSetChanged();
                        } else {
                        }
                    }
                } else {
                    Toast.makeText(ArrivalMainActivity.this ,"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {
                Log.d("test", "error");
            }
        });

    }

    private void setImage() {
        //이미지 추가
        image1 = BitmapFactory.decodeResource(getResources(), R.drawable.line1);
        image2 = BitmapFactory.decodeResource(getResources(), R.drawable.line2);
        image3 = BitmapFactory.decodeResource(getResources(), R.drawable.line3);
        image4 = BitmapFactory.decodeResource(getResources(), R.drawable.line4);
        image5 = BitmapFactory.decodeResource(getResources(), R.drawable.line5);
        image6 = BitmapFactory.decodeResource(getResources(), R.drawable.line6);
        image7 = BitmapFactory.decodeResource(getResources(), R.drawable.line7);
        image8 = BitmapFactory.decodeResource(getResources(), R.drawable.line8);
        image9 = BitmapFactory.decodeResource(getResources(), R.drawable.line9);
        image10 = BitmapFactory.decodeResource(getResources(), R.drawable.line10);
        image11 = BitmapFactory.decodeResource(getResources(), R.drawable.line11);
        image12 = BitmapFactory.decodeResource(getResources(), R.drawable.line12);
        image13 = BitmapFactory.decodeResource(getResources(), R.drawable.line13);
        image14 = BitmapFactory.decodeResource(getResources(), R.drawable.line14);
        image24 = BitmapFactory.decodeResource(getResources(), R.drawable.line24);
    }

}