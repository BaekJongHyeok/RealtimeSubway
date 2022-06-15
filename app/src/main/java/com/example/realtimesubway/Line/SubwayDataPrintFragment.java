package com.example.realtimesubway.Line;

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

import com.example.realtimesubway.Data.Subway.PositionData;
import com.example.realtimesubway.Data.Subway.RealtimePosition;
import com.example.realtimesubway.Data.Subway.RealtimePositionList;
import com.example.realtimesubway.R;
import com.example.realtimesubway.Retrofit.SubwayRetrofit.RetrofitAdapter;
import com.example.realtimesubway.Retrofit.SubwayRetrofit.RetrofitApi;

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
    RecyclerView recyclerView1, recyclerView2;
    RetrofitAdapter retrofitAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    List<RealtimePosition> positionList;
    List<PositionData> upPositionList, downPositionList;
    RetrofitApi retrofitApi;
    Retrofit retrofit;

    public static SubwayDataPrintFragment newInstance(int number) {
        SubwayDataPrintFragment fp = new SubwayDataPrintFragment();
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

        // 지하철 정보 받아오기
        buildRetrofit();
        createRetrofitService();
        callRetrofitGetApi();

        return mview;
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


    private void createRetrofitService(){
        retrofitApi = retrofit.create(RetrofitApi.class);
    }

    public void callRetrofitGetApi(){
        String str = "1호선";
        retrofitApi.getRealtimePositionList(str).enqueue(new Callback<RealtimePositionList>() {
            @Override
            public void onResponse(Call<RealtimePositionList> call, retrofit2.Response<RealtimePositionList> response) {
                if(response.isSuccessful()) {
                    RealtimePositionList result = response.body();

                    positionList =  result.getRealtimePositionList();

                    upPositionList = new ArrayList<>();
                    downPositionList = new ArrayList<>();

                    if(positionList == null){
                        Toast.makeText(getContext(),"열차가 없습니다.",Toast.LENGTH_SHORT).show();
                    } else {
                        for(RealtimePosition position:  positionList) {
                            if(position.getUpdnLine().equals("0")){
                                PositionData temp = new PositionData();
                                temp.setUpdnLine(position.getUpdnLine());
                                temp.setSubwayId(position.getSubwayId());
                                temp.setSubwayNm(position.getSubwayNm());
                                temp.setStatnNm(position.getStatnNm());
                                temp.setRecptnDt(position.getRecptnDt());
                                temp.setTrainSttus(position.getTrainSttus());
                                temp.setDirectAt(position.getDirectAt());
                                upPositionList.add(temp);

                            } else {
                                PositionData temp = new PositionData();
                                temp.setUpdnLine(position.getUpdnLine());
                                temp.setSubwayId(position.getSubwayId());
                                temp.setSubwayNm(position.getSubwayNm());
                                temp.setStatnNm(position.getStatnNm());
                                temp.setRecptnDt(position.getRecptnDt());
                                temp.setTrainSttus(position.getTrainSttus());
                                temp.setDirectAt(position.getDirectAt());
                                downPositionList.add(temp);
                            }
                        }
                    }

                    // recyclerview로 출력
                    retrofitAdapter = new RetrofitAdapter(getActivity().getApplicationContext(), upPositionList);
                    recyclerView1.setAdapter(retrofitAdapter);

                    retrofitAdapter = new RetrofitAdapter(getActivity().getApplicationContext(), downPositionList);
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

    }
}
