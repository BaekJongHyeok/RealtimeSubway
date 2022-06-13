package com.example.realtimesubway.Data.Subway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RealtimePositionList {

    @SerializedName("errorMessage")
    @Expose
    private ErrorMessage errorMessage;
    @SerializedName("realtimeArrivalList")
    @Expose
    private List<RealtimeArrival> realtimeArrivalList;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<RealtimeArrival> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(List<RealtimeArrival> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }

}