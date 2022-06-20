package com.example.realtimesubway.ArrivalSection.Data.Retrofit.SubwayRetrofit;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.SubwayArrival.RealtimeArrivalList;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ArrivalApi {
    @GET("100/{name1}")
    Call<RealtimeArrivalList> getRealtimeArrivalList(@Path("name1") String subwayNm);
}
