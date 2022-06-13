package com.example.realtimesubway;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;

import com.example.realtimesubway.Line.ViewpagerTest;
import com.example.realtimesubway.Retrofit.StationRetrofit.RetrofitClient2;
import com.example.realtimesubway.SearchFilter.SearchAdapter;
import com.example.realtimesubway.SearchFilter.SearchItem;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ArrayList<SearchItem> searchItemArrayList,filteredList;
    SearchAdapter searchAdapter;
    RecyclerView recyclerView;
    LinearLayoutManager linearLayoutManager;
    public static Context mContext;
    public String name;
    int pos;

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

        // 검색 후보들 (임시)
        searchItemArrayList.add(new SearchItem("고색"));
        searchItemArrayList.add(new SearchItem("금정"));
        searchItemArrayList.add(new SearchItem("당정"));
        searchItemArrayList.add(new SearchItem("부평"));
        searchItemArrayList.add(new SearchItem("망포"));
        searchItemArrayList.add(new SearchItem("서동탄"));
        searchItemArrayList.add(new SearchItem("서울"));
        searchItemArrayList.add(new SearchItem("성균관대"));
        searchItemArrayList.add(new SearchItem("수원"));
        searchItemArrayList.add(new SearchItem("신갈"));
        searchItemArrayList.add(new SearchItem("영등포"));
        searchItemArrayList.add(new SearchItem("영통"));
        searchItemArrayList.add(new SearchItem("의왕"));
        searchItemArrayList.add(new SearchItem("인천"));
        searchItemArrayList.add(new SearchItem("평촌"));
        searchItemArrayList.add(new SearchItem("화서"));


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

}