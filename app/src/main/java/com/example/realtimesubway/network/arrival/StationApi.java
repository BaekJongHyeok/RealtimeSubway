package com.example.realtimesubway.network.arrival;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.SearchInfoBySubwayNameService;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface StationApi {

    @GET("1/{name1}")
    Call<SearchInfoBySubwayNameService> getStationData(@Path("name1") int count);
}
