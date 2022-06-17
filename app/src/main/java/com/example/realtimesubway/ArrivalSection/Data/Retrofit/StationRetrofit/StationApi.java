package com.example.realtimesubway.ArrivalSection.Data.Retrofit.StationRetrofit;

import com.example.realtimesubway.ArrivalSection.Data.Station.SearchInfoBySubwayNameService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StationApi {

    @GET("1/{name1}")
    Call<SearchInfoBySubwayNameService> getStationData(@Path("name1") int count);
}
