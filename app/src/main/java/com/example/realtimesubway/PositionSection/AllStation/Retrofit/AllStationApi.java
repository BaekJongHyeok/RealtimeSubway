package com.example.realtimesubway.PositionSection.AllStation.Retrofit;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Station.SearchInfoBySubwayNameService;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.AllStation;
import com.example.realtimesubway.PositionSection.AllStation.OpenApi.SearchSTNBySubwayLineInfo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AllStationApi {

    @GET("%20/{name1}")
    Call<AllStation> getAllStationData(@Path("name1") String line);
}
