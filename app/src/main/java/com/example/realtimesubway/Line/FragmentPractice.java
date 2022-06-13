package com.example.realtimesubway.Line;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.realtimesubway.Data.Station.Row;
import com.example.realtimesubway.Data.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.Data.Station.StationLine;
import com.example.realtimesubway.Data.Subway.Arrival;
import com.example.realtimesubway.Data.Subway.RealtimeArrival;
import com.example.realtimesubway.Data.Subway.RealtimePositionList;
import com.example.realtimesubway.MainActivity;
import com.example.realtimesubway.R;
import com.example.realtimesubway.Retrofit.StationRetrofit.RetrofitClient2;
import com.example.realtimesubway.Retrofit.StationRetrofit.StationApi;
import com.example.realtimesubway.Retrofit.SubwayRetrofit.RetrofitAdapter;
import com.example.realtimesubway.Retrofit.SubwayRetrofit.RetrofitApi;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FragmentPractice extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    TextView textViewRetro;
    String value = ((MainActivity)MainActivity.mContext).name;
    RecyclerView recyclerView1, recyclerView2;
    RetrofitAdapter retrofitAdapter, retrofitAdapter2;
    SwipeRefreshLayout swipeRefreshLayout;

    //파씽2
    public static Context Context1;
    public List<StationLine> stationline;
    public int linecount = 0;


    public static FragmentPractice newInstance(int number) {
        FragmentPractice fp = new FragmentPractice();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        fp.setArguments(bundle);
        return fp;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        if(getArguments() != null){
            int num = getArguments().getInt("number");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View mview = LayoutInflater.from(inflater.getContext()).inflate(R.layout.activity_retrofitclient,container,false);

        swipeRefreshLayout = mview.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);
        recyclerView1 = mview.findViewById(R.id.recycler1);
        recyclerView2 = mview.findViewById(R.id.recycler2);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView1.getContext(), new LinearLayoutManager(getActivity().getApplicationContext()).getOrientation());
        recyclerView1.addItemDecoration(dividerDecoration);
        recyclerView2.addItemDecoration(dividerDecoration);


        // 한글 url encoding
        String str = value;
        String str2 = java.net.URLEncoder.encode(new String(str.getBytes(StandardCharsets.UTF_8)));


        // Retrofit 사용 api 값 가져오기
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://swopenapi.seoul.go.kr/api/subway/486e4e506d6a6f6e38354d62565654/json/realtimeStationArrival/0/")
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

        RetrofitApi retrofitApi = retrofit.create(RetrofitApi.class);
        retrofitApi.getData(str).enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, retrofit2.Response<RealtimePositionList> response) {
                if(response.isSuccessful()) {
                    RealtimePositionList result = response.body();

                    List<RealtimeArrival> arrivalList =  result.getRealtimeArrivalList();

                    List<Arrival> arrivals = new ArrayList<>();
                    List<Arrival> arrivals2 = new ArrayList<>();

                    for(RealtimeArrival arrival:  arrivalList) {
                        if(arrival.getUpdnLine().equals("상행")){
                            Arrival temp = new Arrival();
                            temp.setUpdnLine(arrival.getUpdnLine());
                            temp.setTrainLineNm(arrival.getTrainLineNm());
                            temp.setSubwayHeading(arrival.getSubwayHeading());
                            temp.setBstatnNm(arrival.getBstatnNm());
                            temp.setArvlMsg2(arrival.getArvlMsg2());
                            temp.setArvlMsg3(arrival.getArvlMsg3());
                            arrivals.add(temp);

                        } else if(arrival.getUpdnLine().equals("하행")){

                            Arrival temp = new Arrival();
                            temp.setUpdnLine(arrival.getUpdnLine());
                            temp.setTrainLineNm(arrival.getTrainLineNm());
                            temp.setSubwayHeading(arrival.getSubwayHeading());
                            temp.setBstatnNm(arrival.getBstatnNm());
                            temp.setArvlMsg2(arrival.getArvlMsg2());
                            temp.setArvlMsg3(arrival.getArvlMsg3());
                            arrivals2.add(temp);
                        }

                    }

                    // recyclerview로 출력
                    retrofitAdapter = new RetrofitAdapter(getActivity().getApplicationContext(), arrivals);
                    recyclerView1.setAdapter(retrofitAdapter);

                    retrofitAdapter = new RetrofitAdapter(getActivity().getApplicationContext(), arrivals2);
                    recyclerView2.setAdapter(retrofitAdapter);

                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"에러", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<RealtimePositionList> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

        //파씽2
        // 한글 url encoding
        String strpasing = value;
        String str2pasing = java.net.URLEncoder.encode(new String(strpasing.getBytes(StandardCharsets.UTF_8)));


        // Retrofit 사용 api 값 가져오기
        Retrofit retrofit2 = new Retrofit.Builder()
                .baseUrl("http://openapi.seoul.go.kr:8088/635a446f6d6a6f6e3131304e52586866/json/SearchInfoBySubwayNameService/1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        OkHttpClient client2 = new OkHttpClient().newBuilder().addInterceptor(
                new Interceptor() {
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        return null;
                    }
                }
        ).build();

        StationApi stationApi = retrofit2.create(StationApi.class);
        // 연결
        Call<SearchInfoBySubwayNameService> call = stationApi.getStationData(str);
        call.enqueue(new Callback<SearchInfoBySubwayNameService>() {
            @Override
            public void onResponse(Call<SearchInfoBySubwayNameService> call, retrofit2.Response<SearchInfoBySubwayNameService> response) {
                if(response.isSuccessful()) {
                    SearchInfoBySubwayNameService result2 = response.body();
                    List<Row> rowList =  result2.getSearchInfoBySubwayNameService().getRow();

                    stationline = new ArrayList<>();


                    for (int i=0; i<rowList.size(); i++){
                        StationLine datalist = new StationLine();
                        //arrivals.add(new StationLine(rowList.get(i).getLineNum()));
                        datalist.setLineNum(rowList.get(i).getLineNum());
                        stationline.add(datalist);
                        linecount++;
                    }
                    Log.d("test",stationline.toString());
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<SearchInfoBySubwayNameService> call, Throwable t) {
                Log.d("test", "error");
            }
        });


        return mview;
    }

    @Override
    public void onRefresh() {

        swipeRefreshLayout.setRefreshing(false);
    }
}
