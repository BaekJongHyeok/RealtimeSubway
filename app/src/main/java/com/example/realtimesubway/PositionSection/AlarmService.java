package com.example.realtimesubway.PositionSection;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.loader.content.AsyncTaskLoader;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePositionList;
import com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit.RetrofitApi;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.AllStation;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.Row;
import com.example.realtimesubway.PositionSection.AllStation.Retrofit.AllStationApi;
import com.example.realtimesubway.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AlarmService extends Service {
    public static Intent serviceIntent = null;
    String lineNum, trainNo, destination;
    int beforePosition;
    List<PositionData> upPositionList;
    List<PositionData> downPositionList;
    TreeMap<String, String> sortedStationMap, reverseSortedStationMap;
    int value = 0;
    int beforePositionResult;

    public AlarmService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("test", "onCreate ?????????");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d("test", "onStartCommand ?????????");
        lineNum = intent.getStringExtra("lineNum");
        trainNo = intent.getStringExtra("trainNo");
        destination = intent.getStringExtra("destination");
        beforePosition = intent.getIntExtra("beforePosition",0);

        Log.d("test", lineNum + trainNo + destination + beforePosition);

        BackRunnable runnable = new BackRunnable();
        Thread thread = new Thread(runnable);
        thread.setDaemon(true);
        thread.start();

        return super.onStartCommand(intent, flags, startId);
    }

    class BackRunnable implements Runnable{
        @Override
        public void run(){
            while(true){
    //  trainNo??? ?????? ????????? ?????? ???????????? ?????? ????????? destination - beforeposition ????????? ?????????????????? ?????? ???????????? api??? ???????????? ??????
                initializeNotification();
                value++;
                reRealtimePosition();
    //  ??????????????? ???????????? ??????
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        private void initializeNotification() {
            NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext(), "1");
            builder.setSmallIcon(R.mipmap.ic_launcher);
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle();
            style.bigText("????????? ????????? ????????????.");
            style.setBigContentTitle(null);
            style.setSummaryText("????????? ?????????");
            builder.setContentText(null);
            builder.setContentTitle(null);
            builder.setOngoing(true);
            builder.setStyle(style);
            builder.setWhen(0);
            builder.setShowWhen(false);

            Intent notificationIntent = new Intent(getApplicationContext(), RealtimePositionLine2.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, notificationIntent, 0);
            builder.setContentIntent(pendingIntent);
            NotificationManager manager = (NotificationManager)getSystemService(Context.NOTIFICATION_SERVICE);
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
                manager.createNotificationChannel(new NotificationChannel("1", "??????????????? ?????????", NotificationManager.IMPORTANCE_NONE));
            }
            Notification notification = builder.build();
            startForeground(1, notification);
        }
    }


    private void reRealtimePosition() {
        Retrofit realtimeRetrofit = new Retrofit.Builder()
                .baseUrl("http://swopenapi.seoul.go.kr/api/subway/65425773516a6f6e36396452775575/json/realtimePosition/0/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        RetrofitApi retrofitApi = realtimeRetrofit.create(RetrofitApi.class);
        Call<RealtimePositionList> realtimeCall = retrofitApi.getRealtimePositionList(lineNum);
        realtimeCall.enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, Response<RealtimePositionList> response) {
                if(response.isSuccessful()){
                    RealtimePositionList result2 = response.body();
                    List<RealtimePosition> realtimePositionList = result2.getRealtimePositionList();

                    upPositionList = new ArrayList<>(); // ???????????? ?????? ?????????
                    downPositionList = new ArrayList<>(); // ???????????? ?????? ?????????


                    for(RealtimePosition position: realtimePositionList) {
                        // ??????????????? ????????? ??????
                        if (position.getUpdnLine().equals("0")) {
                            PositionData arrTemp = new PositionData();
                            arrTemp.setTrainNo(position.getTrainNo());
                            arrTemp.setStatnNm(position.getStatnNm());
                            arrTemp.setUpdnLine(position.getUpdnLine());
                            arrTemp.setTrainSttus(position.getTrainSttus());
                            arrTemp.setDirectAt(position.getDirectAt());
                            arrTemp.setStatnTnm(position.getStatnTnm());
                            upPositionList.add(arrTemp);
                        } else {
                            PositionData arrTemp = new PositionData();
                            arrTemp.setTrainNo(position.getTrainNo());
                            arrTemp.setStatnNm(position.getStatnNm());
                            arrTemp.setUpdnLine(position.getUpdnLine());
                            arrTemp.setTrainSttus(position.getTrainSttus());
                            arrTemp.setDirectAt(position.getDirectAt());
                            arrTemp.setStatnTnm(position.getStatnTnm());
                            downPositionList.add(arrTemp);
                        }
                    }

                    for(int i=0; i<upPositionList.size(); i++){
                        if(upPositionList.get(i).getTrainNo().equals(trainNo) && upPositionList.get(i).getStatnNm().equals(destination)){
                            Toast.makeText(getApplicationContext(),"??????",Toast.LENGTH_SHORT).show();
                            Log.d("alarm : ", "??????");
                        }
                    }
                    Intent showIntent = new Intent(getApplicationContext(), RealtimePositionLine2.class);
                    showIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);


                    Log.d("Alarm : ", value + "?????? ??????");
                }
            }
            @Override
            public void onFailure(Call<RealtimePositionList> call, Throwable t) {

            }
        });
        // ????????? ????????? ?????? api ???????????? ??????
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("test", "onDestroy ?????????");
    }



    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
}