package com.example.realtimesubway.ArrivalSection.Data.Station;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SearchInfoBySubwayNameService {

    @SerializedName("SearchInfoBySubwayNameService")
    @Expose
    private Data data;

    public Data getSearchInfoBySubwayNameService() {
        return data;
    }

    public void setSearchInfoBySubwayNameService(Data data) {
        this.data = data;
    }

}
