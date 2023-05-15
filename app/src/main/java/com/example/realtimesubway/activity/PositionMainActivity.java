package com.example.realtimesubway.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.example.realtimesubway.PositionSection.LineList.LineListAdapter;
import com.example.realtimesubway.PositionSection.RealtimePositionLine2;
import com.example.realtimesubway.R;
import com.example.realtimesubway.data.Line;

import java.util.ArrayList;

public class PositionMainActivity extends AppCompatActivity {
    private static final String KEY_LINENUM = "line_number";
    private ArrayList<Line> lineList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_position_main);

        initializeLineData();

        ListView listView = findViewById(R.id.lineListView);
        final LineListAdapter lineListAdapter = new LineListAdapter(this, lineList);

        listView.setAdapter(lineListAdapter);

        listView.setOnItemClickListener((parent, view, position, id) -> {
            Intent intent = new Intent(PositionMainActivity.this, RealtimePositionLine2.class);
            intent.putExtra(KEY_LINENUM, position + 1);
            startActivity(intent);
        });
    }

    private void initializeLineData() {
        lineList = new ArrayList<>();
        lineList.add(new Line(R.drawable.line1, "1호선"));
        lineList.add(new Line(R.drawable.line2, "2호선"));
        lineList.add(new Line(R.drawable.line3, "3호선"));
        lineList.add(new Line(R.drawable.line4, "4호선"));
        lineList.add(new Line(R.drawable.line5, "5호선"));
        lineList.add(new Line(R.drawable.line6, "6호선"));
        lineList.add(new Line(R.drawable.line7, "7호선"));
        lineList.add(new Line(R.drawable.line8, "8호선"));
        lineList.add(new Line(R.drawable.line9, "9호선"));
        lineList.add(new Line(R.drawable.line10, "수인분당선"));
        lineList.add(new Line(R.drawable.line11, "경춘선"));
        lineList.add(new Line(R.drawable.line12, "공항철도"));
        lineList.add(new Line(R.drawable.line13, "신분당선"));
        lineList.add(new Line(R.drawable.line14, "우이신설선"));
    }
}