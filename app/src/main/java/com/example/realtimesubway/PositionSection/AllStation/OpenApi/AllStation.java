package com.example.realtimesubway.PositionSection.AllStation.OpenApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AllStation {

    @SerializedName("SearchSTNBySubwayLineInfo")
    @Expose
    private SearchSTNBySubwayLineInfo searchSTNBySubwayLineInfo;

    public SearchSTNBySubwayLineInfo getSearchSTNBySubwayLineInfo() {
        return searchSTNBySubwayLineInfo;
    }

    public void setSearchSTNBySubwayLineInfo(SearchSTNBySubwayLineInfo searchSTNBySubwayLineInfo) {
        this.searchSTNBySubwayLineInfo = searchSTNBySubwayLineInfo;
    }

}
