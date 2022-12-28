package com.example.realtimesubway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
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
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;

public class RealMainActivity extends AppCompatActivity implements View.OnTouchListener {
    private String TAG = RealMainActivity.class.getSimpleName();

    // UI
    private GridView lvFavor;
    private FavorListViewAdapter favorListViewAdapter;
    private EditText etSearch;
    private TextView tv_main;
    private RecyclerView rvSearchResult;
    private DividerItemDecoration itemDivider;
    private LinearLayout layoutMain, layoutResult;
    private LinearLayoutManager lyManager;
    private ProgressBar pbLoading;
    private int progressCount = 0;

    private boolean isFavor = false;
    private String stationName;
    private Map<String, ArrayList<String>> sortedMap;
    private Map<String, ArrayList<Bitmap>> imageMap = new HashMap<>();
    private ArrayList<SearchItem> searchItemCopyList = new ArrayList();
    private List<SearchItem> searchItemList, filteredList;
    private SearchAdapter searchAdapter;
    private ArrayList<SearchItem> favorList = new ArrayList<>();

    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private StationApi apiService;
    private SearchStationLine searchStationLine;
    private long backKeyPressedTime = 0;

    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_main);

        init();
        addEventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFavorList();
    }

    private void init() {
        searchStationLine = new SearchStationLine();

        layoutMain = findViewById(R.id.layout_main);
        layoutResult = findViewById(R.id.layoutResult);
        tv_main = findViewById(R.id.btn_main);
        tv_main.setOnTouchListener(this);
        rvSearchResult = findViewById(R.id.rv_searchResult);
        pbLoading = findViewById(R.id.pb_loading);
        etSearch = findViewById(R.id.et_search);
        etSearch.setOnTouchListener(this);
        lvFavor = findViewById(R.id.grid_favor);
        favorListViewAdapter = new FavorListViewAdapter(this, favorList);
        lvFavor.setAdapter(favorListViewAdapter);

        pref = getSharedPreferences("favor", MODE_PRIVATE);

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
                search(searchText);
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
            }
        });

        lvFavor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                String stationName = favorList.get(position).getStationName();
                Intent intent = new Intent(getApplicationContext(), SubwayDataPrintViewPager.class);
                intent.putExtra(SubwayDataPrintViewPager.KEY_STATION_NAME, stationName);
                intent.putExtra(SubwayDataPrintViewPager.KEY_LINECOUNT, sortedMap.get(stationName).size());
                intent.putStringArrayListExtra(SubwayDataPrintViewPager.KEY_STATION_LINES, sortedMap.get(stationName));
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if(view == etSearch) {
                    layoutMain.setVisibility(View.GONE);
                    layoutResult.setVisibility(View.VISIBLE);
                    String searchText = etSearch.getText().toString();
                    search(searchText);
                } else if(view == tv_main) {
                    etSearch.setText(null);
                    searchItemList.clear();
                    searchAdapter.notifyDataSetChanged();
                    layoutMain.setVisibility(View.VISIBLE);
                    layoutResult.setVisibility(View.GONE);
                    setFavorList();
                }
                break;
            }
        }
        return false;
    }

    // EditText focus 아웃될 경우
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    private void search(String searchText) {
        searchItemList.clear();

        if(searchText.length() == 0) {
            searchItemList.addAll(searchItemCopyList);
        } else {
            for(SearchItem item : searchItemCopyList) {
                if(item.getStationName().toLowerCase().contains(searchText)) {
                    searchItemList.add(item);
                }
            }
        }
        searchAdapter.notifyDataSetChanged();
    }

    // api로 전체역 정보 받아서 MAP 형태로 저장
    private void getStationInfo() {
        retrofit2.Call<SearchInfoBySubwayNameService> call = apiService.getStationData(1000);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(retrofit2.Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if(response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList = result2.getSearchInfoBySubwayNameService().getRow();

                    // hashmap 형태로 만들기
                    HashMap<String, ArrayList<String>> dic = new HashMap<String, ArrayList<String>>();

                    for(Row row : rowList) {
                        String stName = row.getStationNm();
                        String lineNum = row.getLineNum();
                        ArrayList<String> list = new ArrayList<>();
                        if(dic.containsKey(stName)) list = dic.get(stName);

                        if(lineNum.equals("인천선") || lineNum.equals("인천2호선") || lineNum.equals("경의선") || lineNum.equals("경강선")
                                || lineNum.equals("신림선") || lineNum.equals("서해선") || lineNum.equals("김포도시철도") || lineNum.equals("용인경전철")
                                || lineNum.equals("의정부경전철")) {// api에 데이터가 없는 노선은 삭제

                        } else {
                            list.add(lineNum);
                        }
                        Collections.sort(list);
                        dic.put(stName,list);
                    }

                    // 맵 key 기준 정렬
                    sortedMap = new TreeMap<>(dic);

                    setStationInfo();
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

    private void setStationInfo() {
        // 전철역 정보 저장
        setProgressbar(true);
        for(Map.Entry<String, ArrayList<String>> entry: sortedMap.entrySet()){
            stationName = entry.getKey();
            ArrayList<String> lines = entry.getValue();

            ArrayList<Bitmap> imageList = new ArrayList<>();
            if(lines.size() > 0){
                for(String line : lines) {
                    Bitmap image = searchStationLine.search(getApplicationContext(), line);
                    imageList.add(image);
                }
                imageMap.put(stationName, imageList);
                searchItemList.add(new SearchItem(imageList, stationName));

                // stationName이 즐겨찾기 되어있을 경우
                setFavorList();
            }
        }

        searchItemCopyList.addAll(searchItemList);
        setProgressbar(false);
    }

    private void setFavorList() {
        favorList.clear();
        Collection<?> collection = pref.getAll().keySet();
        Iterator<?> iterator = collection.iterator();

        while(iterator.hasNext()) {
            String stName = (String) iterator.next();
            favorList.add(new SearchItem(imageMap.get(stName),stName));
        }

        favorListViewAdapter.notifyDataSetChanged();
    }

    public void setProgressbar(boolean visible) {
        if (visible) {
            if (progressCount == 0) {
                pbLoading.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            progressCount++;
        } else {
            progressCount--;
            if (progressCount == 0) {
                pbLoading.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    @Override
    public void onBackPressed() {
        if(layoutResult.getVisibility() == View.VISIBLE) {
            searchItemList.clear();
            searchAdapter.notifyDataSetChanged();
            layoutMain.setVisibility(View.VISIBLE);
            layoutResult.setVisibility(View.GONE);
            setFavorList();
        } else if(layoutMain.getVisibility() == View.VISIBLE) {
            if(System.currentTimeMillis() > backKeyPressedTime + 2000) {
                backKeyPressedTime = System.currentTimeMillis();
                makeToast("뒤로 버튼을 한번 더 누르시면 종료됩니다.");
            } else {
                toast.cancel();
                finish();
            }
        }
    }

    private void makeToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}