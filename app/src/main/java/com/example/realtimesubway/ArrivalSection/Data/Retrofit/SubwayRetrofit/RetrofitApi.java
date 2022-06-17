package com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit;

import com.example.realtimesubway.ArrivalSection.Data.Subway.RealtimePositionList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RetrofitApi {

    @GET("100/{name1}")
    Call<RealtimePositionList> getRealtimePositionList(@Path("name1") String subwayNm);
}
