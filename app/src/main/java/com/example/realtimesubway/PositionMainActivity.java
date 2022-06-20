package com.example.realtimesubway;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.realtimesubway.PositionSection.LineList.LineListAdapter;
import com.example.realtimesubway.PositionSection.LineList.LineListData;
import com.example.realtimesubway.PositionSection.RealtimePositionLine2;

import java.util.ArrayList;

public class PositionMainActivity extends AppCompatActivity {
    ArrayList<LineListData> lineListData;
    String imageKey; // 01호선 -> line1로 변환된 이름 받아올 string
    String resName, packName;
    int resId; // 변수명 참조해서 drawable에서 가져온 리소스의 아이디

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_main);

        this.InitializeLineData();

        ListView listView = (ListView) findViewById(R.id.lineListView);
        final LineListAdapter lineListAdapter = new LineListAdapter(this, lineListData);

        listView.setAdapter(lineListAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View view, int position, long id) {

                    Intent intent = new Intent(PositionMainActivity.this, RealtimePositionLine2.class);
                    intent.putExtra(RealtimePositionLine2.KEY_LINENUM,position+1);
                    startActivity(intent);
            }
        });
    }

    private void InitializeLineData() {
        lineListData = new ArrayList<>();

        lineListData.add(new LineListData(R.drawable.line1, "1호선"));
        lineListData.add(new LineListData(R.drawable.line2, "2호선"));
        lineListData.add(new LineListData(R.drawable.line3, "3호선"));
        lineListData.add(new LineListData(R.drawable.line4, "4호선"));
        lineListData.add(new LineListData(R.drawable.line5, "5호선"));
        lineListData.add(new LineListData(R.drawable.line6, "6호선"));
        lineListData.add(new LineListData(R.drawable.line7, "7호선"));
        lineListData.add(new LineListData(R.drawable.line8, "8호선"));
        lineListData.add(new LineListData(R.drawable.line9, "9호선"));
        lineListData.add(new LineListData(R.drawable.line10, "수인분당선"));
        lineListData.add(new LineListData(R.drawable.line11, "경춘선"));
        lineListData.add(new LineListData(R.drawable.line12, "공항철도"));
        lineListData.add(new LineListData(R.drawable.line13, "신분당선"));
        lineListData.add(new LineListData(R.drawable.line14, "우이신설선"));

    }
}