package com.example.realtimesubway.PositionSection;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePositionList;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.AllStation;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.Row;
import com.example.realtimesubway.PositionSection.AllStation.Retrofit.AllStationApi;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmService extends Service {
    public static Intent serviceIntent = null;
    public AlarmService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test", "onCreate 호출됨");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}