package com.example.realtimesubway.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.realtimesubway.ArrivalSection.Data.Line.SubwayDataPrintViewPager;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.Row;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchAdapter;
import com.example.realtimesubway.ArrivalSection.Data.SearchFilter.SearchItem;
import com.example.realtimesubway.ArrivalSection.Data.SearchStationLineImage.SearchStationLine;
import com.example.realtimesubway.FavorListViewAdapter;
import com.example.realtimesubway.R;
import com.example.realtimesubway.databinding.ActivityRealMainBinding;
import com.example.realtimesubway.network.RetrofitClient;
import com.example.realtimesubway.network.arrival.StationApi;
import com.example.realtimesubway.util.Const;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;

public class RealMainActivity extends AppCompatActivity implements View.OnTouchListener {
    // UI
    private ActivityRealMainBinding binding;
    private FavorListViewAdapter favorListViewAdapter;

    private Map<String, ArrayList<String>> sortedMap;
    private Map<String, ArrayList<Bitmap>> imageMap = new HashMap<>();
    private ArrayList<SearchItem> searchItemCopyList = new ArrayList();
    private ArrayList<SearchItem> favorList = new ArrayList<>();
    private List<SearchItem> searchItemList, filteredList;
    private SearchAdapter searchAdapter;

    private StationApi apiService;
    private SearchStationLine searchStationLine;
    private long backKeyPressedTime = 0;
    private int progressCount = 0;
    private Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRealMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        init();
        addEventListener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        setFavorList();
    }

    private void init() {
        searchStationLine = new SearchStationLine(getApplicationContext());

        binding.btnMain.setOnTouchListener(this);
        binding.etSearch.setOnTouchListener(this);

        favorListViewAdapter = new FavorListViewAdapter(this, favorList);
        binding.gridFavor.setAdapter(favorListViewAdapter);

        filteredList = new ArrayList<>();
        searchItemList = new ArrayList<>();
        searchAdapter = new SearchAdapter(searchItemList, this);

        // recyclerView 세팅
        LinearLayoutManager lyManager = new LinearLayoutManager(this);
        DividerItemDecoration itemDivider = new DividerItemDecoration(RealMainActivity.this, new LinearLayoutManager(this).getOrientation());
        binding.rvSearchResult.setLayoutManager(lyManager);
        binding.rvSearchResult.setAdapter(searchAdapter);
        binding.rvSearchResult.addItemDecoration(itemDivider);

        // 지하철 역 전체 정보 api 세팅
        apiService = RetrofitClient.getClient(Const.SUBWAY_STATION_URL).create(StationApi.class);
        getStationInfo();
    }

    private void addEventListener() {
        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                String searchText = binding.etSearch.getText().toString();
                search(searchText);
            }
        });

        // 리스트 아이템 클릭 시 해당 역 정보로 이동
        searchAdapter.setOnItemClickListener(new SearchAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                String stationName = "";

                if(filteredList.isEmpty()) {
                    stationName = searchItemList.get(position).getStationName();
                } else {
                    stationName = filteredList.get(position).getStationName();
                }

                putIntent(stationName);
            }
        });

        binding.gridFavor.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                putIntent(favorList.get(position).getStationName());
            }
        });

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if(actionId == EditorInfo.IME_ACTION_DONE) {
                    InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    inputMethodManager.hideSoftInputFromWindow(binding.etSearch.getWindowToken(), 0);
                    return true;
                }
                return false;
            }
        });

        binding.gridFavor.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long l) {
                new AlertDialog.Builder(RealMainActivity.this)
                        .setTitle("정말로 즐겨찾기에서 삭제하시겠습니까?")
                        .setPositiveButton("예", (dialog, which) -> {
                            // 여기다가 이제 즐겨찾기 삭제 로직 추가해야함
                        })
                        .setNegativeButton("아니오", (dialog, which) -> {})
                        .show();
                return true;
            }
        });
    }

    private void putIntent(String stationName) {
        Intent intent = new Intent(getApplicationContext(), SubwayDataPrintViewPager.class);
        intent.putExtra(SubwayDataPrintViewPager.KEY_STATION_NAME, stationName);
        intent.putExtra(SubwayDataPrintViewPager.KEY_LINECOUNT, sortedMap.get(stationName).size());
        intent.putStringArrayListExtra(SubwayDataPrintViewPager.KEY_STATION_LINES, sortedMap.get(stationName));
        startActivity(intent);
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case MotionEvent.ACTION_DOWN: {
                if(view == binding.etSearch) {
                    binding.layoutMain.setVisibility(View.GONE);
                    binding.layoutResult.setVisibility(View.VISIBLE);
                    String searchText = binding.etSearch.getText().toString();
                    search(searchText);
                } else if(view == binding.btnMain) {
                    binding.etSearch.setText(null);
                    searchItemList.clear();
                    searchAdapter.notifyDataSetChanged();
                    binding.layoutMain.setVisibility(View.VISIBLE);
                    binding.layoutResult.setVisibility(View.GONE);
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
            View view = getCurrentFocus();
            if ( view instanceof EditText) {
                Rect outRect = new Rect();
                view.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)ev.getRawX(), (int)ev.getRawY())) {
                    view.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
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
                    SearchInfoBySubwayNameService result = response.body();
                    List<Row> rowList = result.getSearchInfoBySubwayNameService().getRow();

                    // hashmap 형태로 만들기
                    HashMap<String, ArrayList<String>> dic = new HashMap<>();

                    for(Row row : rowList) {
                        String stationName = row.getStationNm();
                        String lineNum = row.getLineNum();

                        ArrayList<String> list = new ArrayList<>();
                        if(dic.containsKey(stationName)) list = dic.get(stationName);

                        List<String> ignoreLineList = Arrays.asList(Const.IGNORE_LINES);
                        if(!ignoreLineList.contains(lineNum)) {
                            // api에 데이터가 있는 노선만 추가
                            list.add(lineNum);
                            Collections.sort(list);
                            dic.put(stationName,list);
                        }
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

        for(Map.Entry<String, ArrayList<String>> entry : sortedMap.entrySet()){
            String stationName = entry.getKey();
            ArrayList<String> lines = entry.getValue();

            ArrayList<Bitmap> imageList = new ArrayList<>();
            for(String line : lines) {
                Bitmap image = searchStationLine.search(line);
                imageList.add(image);
            }
            imageMap.put(stationName, imageList);
            searchItemList.add(new SearchItem(imageList, stationName));
        }

        setFavorList();

        searchItemCopyList.addAll(searchItemList);
        binding.gridFavor.setVisibility(View.VISIBLE);
        setProgressbar(false);
    }


    /**
     * 즐겨찾기
     */
    private void setFavorList() {
        if (sortedMap == null) return;

        favorList.clear();
        SharedPreferences pref = getSharedPreferences("favor", MODE_PRIVATE);
        Set<String> favorStationNames = pref.getAll().keySet();
        ArrayList<SearchItem> newFavorList = new ArrayList<>(favorStationNames.size());
        for (String favorStationName : favorStationNames) {
            ArrayList<Bitmap> imageList = getImageListForStation(favorStationName);
            SearchItem searchItem = new SearchItem(imageList, favorStationName);
            newFavorList.add(searchItem);
        }

        favorList.clear();
        favorList.addAll(newFavorList);
        binding.tvFavorite.setText("(" + favorStationNames.size() + ")");
        favorListViewAdapter.notifyDataSetChanged();
    }

    private ArrayList<Bitmap> getImageListForStation(String stationName) {
        ArrayList<Bitmap> imageList = new ArrayList<>();
        ArrayList<String> lineNumbers = sortedMap.get(stationName);
        if (lineNumbers != null) {
            for (String lineNumber : lineNumbers) {
                Bitmap lineBitmap = searchStationLine.search(lineNumber);
                if (lineBitmap != null) {
                    imageList.add(lineBitmap);
                }
            }
        }
        return imageList;
    }

    private void clearSearchResults() {
        binding.etSearch.setText(null);
        searchItemList.clear();
        searchAdapter.notifyDataSetChanged();
        binding.layoutMain.setVisibility(View.VISIBLE);
        binding.layoutResult.setVisibility(View.GONE);
    }

    public void onBackPressed() {
        if (binding.layoutResult.getVisibility() == View.VISIBLE) {
            clearSearchResults();
            setFavorList();
        } else if (binding.layoutMain.getVisibility() == View.VISIBLE) {
            handleExit();
        }
    }

    private void handleExit() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            makeToast(R.string.msg_back_pressed);
        } else {
            toast.cancel();
            finish();
        }
    }

    public void setProgressbar(boolean visible) {
        if (visible) {
            if (progressCount == 0) {
                binding.pbLoading.setVisibility(View.VISIBLE);
                getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE, WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
            progressCount++;
        } else {
            progressCount--;
            if (progressCount == 0) {
                binding.pbLoading.setVisibility(View.GONE);
                getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
            }
        }
    }

    private void makeToast(int msg) {
        if(toast != null) {
            toast.cancel();
        }
        toast = Toast.makeText(this, getString(msg), Toast.LENGTH_SHORT);
        toast.show();
    }
}