package com.example.realtimesubway.ArrivalSection.Data.Line;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePositionList;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.SubwayArrival.Arrival;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.SubwayArrival.RealtimeArrival;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.SubwayArrival.RealtimeArrivalList;
import com.example.realtimesubway.R;
import com.example.realtimesubway.network.arrival.ArrivalAdapter;
import com.example.realtimesubway.network.arrival.ArrivalApi;
import com.example.realtimesubway.network.arrival.RetrofitApi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class SubwayDataPrintFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    private RecyclerView recyclerView1, recyclerView2;
    private ArrivalAdapter arrivalAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RetrofitApi retrofitApi;
    private ArrivalApi arrivalApi;
    private Retrofit retrofit;
    private String stationLineName, stationNm;

    public static SubwayDataPrintFragment newInstance(int number, String stationLineNm, String stationNm) {
        SubwayDataPrintFragment fp = new SubwayDataPrintFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("number", number);
        Bundle stationLineBundle = new Bundle();
        stationLineBundle.putString("stationLineName", stationLineNm);
        stationLineBundle.putString("stationName", stationNm);
        fp.setArguments(bundle);
        fp.setArguments(stationLineBundle);
        return fp;
    }

    @Override
    public void onCreate(@NonNull Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            int num = getArguments().getInt("number");
            stationLineName = getArguments().getString("stationLineName");
            stationNm = getArguments().getString("stationName");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @NonNull ViewGroup container, @NonNull Bundle savedInstanceState) {
        View mView = LayoutInflater.from(inflater.getContext()).inflate(R.layout.fragment_subwaydata_print,container,false);

        // 새로고침 관련
        swipeRefreshLayout = mView.findViewById(R.id.swipeLayout);
        swipeRefreshLayout.setOnRefreshListener(this);

        // RecyclerView 관련
        recyclerView1 = mView.findViewById(R.id.recycler1);
        recyclerView2 = mView.findViewById(R.id.recycler2);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        RecyclerView.LayoutManager layoutManager2 = new LinearLayoutManager(getActivity().getApplicationContext());
        recyclerView1.setLayoutManager(layoutManager);
        recyclerView2.setLayoutManager(layoutManager2);
        DividerItemDecoration dividerDecoration = new DividerItemDecoration(recyclerView1.getContext(), new LinearLayoutManager(getActivity().getApplicationContext()).getOrientation());
        recyclerView1.addItemDecoration(dividerDecoration);
        recyclerView2.addItemDecoration(dividerDecoration);

        // 지하철 정보 받아오기
        buildRetrofit();
        createRetrofitService();
        callRetrofitGetApi();
        return mView;
    }

    @Override
    public void onRefresh() {
        buildRetrofit();
        createRetrofitService();
        callRetrofitGetApi();

        swipeRefreshLayout.setRefreshing(false);
    }

    private void buildRetrofit(){
        // Retrofit 사용 api 값 가져오기
        retrofit = new Retrofit.Builder()
                .baseUrl("http://swopenapi.seoul.go.kr/api/subway/65425773516a6f6e36396452775575/json/realtimePosition/0/")
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

    private void buildRetrofit2() {
        // Retrofit 사용 api 값 가져오기
        retrofit = new Retrofit.Builder()
                .baseUrl("http://swopenapi.seoul.go.kr/api/subway/526c646e766a6f6e383478756c6f54/json/realtimeStationArrival/0/")
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
        retrofitApi = retrofit.create(RetrofitApi.class);
    }

    private void createRetrofitService2() { arrivalApi = retrofit.create(ArrivalApi.class); }

    public void callRetrofitGetApi(){
        String LineNumber = stationLineName.replaceFirst("0",""); // stationLineName 앞에 붙는 0제거
        retrofitApi.getRealtimePositionList(LineNumber).enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, retrofit2.Response<RealtimePositionList> response) {
                if(response.isSuccessful()) {
                    RealtimePositionList result = response.body();
                    List<RealtimePosition> positionList =  result.getRealtimePositionList();
                    String subwayLineCode = positionList.get(0).getSubwayId(); // 위치정보 API의 노선번호

                    buildRetrofit2();
                    createRetrofitService2();
                    callRetrofitGetApi2(subwayLineCode);
                } else {
                    Toast.makeText(getActivity().getApplicationContext(),"에러", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onFailure(Call<RealtimePositionList> call, Throwable t) {
                Toast.makeText(getActivity().getApplicationContext(), "실패", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void callRetrofitGetApi2(String subwayLineCode){
        String str = stationNm;
        arrivalApi.getRealtimeArrivalList(str).enqueue(new Callback<RealtimeArrivalList>() {
            @Override
            public void onResponse(Call<RealtimeArrivalList> call, retrofit2.Response<RealtimeArrivalList> response) {
                if(response.isSuccessful()) {
                    RealtimeArrivalList result = response.body();
                    List<RealtimeArrival> arrivalList = result.getRealtimeArrivalList();
                    List<Arrival> upArrivalList = new ArrayList<>(); // 상행열차 담을 리스트
                    List<Arrival> downArrivalList = new ArrayList<>(); // 하행열차 담을 리스트

                    if(arrivalList == null){
                        Toast.makeText(getContext(),"현재 운영중인 열차가 없습니다.",Toast.LENGTH_SHORT).show();
                    } else {
                        for(RealtimeArrival arrival : arrivalList){
                            // 도착정보 API의 노선번호가 위치정보 API의 노선번호와 같을 경우
                            if(arrival.getSubwayId().equals(subwayLineCode)){
                                // 상행이거나 외선일 경우 upArrivalList
                                if(arrival.getUpdnLine().equals("상행") || arrival.getUpdnLine().equals("외선")){
                                    Arrival arrTemp = new Arrival();
                                    arrTemp.setTrainLineNm(arrival.getTrainLineNm());
                                    arrTemp.setArvlMsg2(arrival.getArvlMsg2());
                                    upArrivalList.add(arrTemp);
                                } else {
                                    Arrival arrTemp = new Arrival();
                                    arrTemp.setTrainLineNm(arrival.getTrainLineNm());
                                    arrTemp.setArvlMsg2(arrival.getArvlMsg2());
                                    downArrivalList.add(arrTemp);
                                }
                            }
                        }
                    }
                    // 상행선 출력
                    arrivalAdapter = new ArrivalAdapter(getActivity().getApplicationContext(), upArrivalList);
                    recyclerView1.setAdapter(arrivalAdapter);
                    // 하행선 출력
                    arrivalAdapter = new ArrivalAdapter(getActivity().getApplicationContext(), downArrivalList);
                    recyclerView2.setAdapter(arrivalAdapter);
                }
            }
            @Override
            public void onFailure(Call<RealtimeArrivalList> call, Throwable t) {}
        });

    }

}
