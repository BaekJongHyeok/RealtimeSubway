package com.example.realtimesubway;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void btnClick(View view){
        if(view.getId() == R.id.btn_arrival){
            Intent intent = new Intent(MainActivity.this, ArrivalMainActivity.class);
            startActivity(intent);
        } else if(view.getId() == R.id.btn_subwayline){
            Intent intent = new Intent(MainActivity.this, PositionMainActivity.class);
            startActivity(intent);
        }
    }
}
