package com.example.realtimesubway.ArrivalSection.Data.SubwayArrival;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RealtimeArrivalList  {

    @SerializedName("errorMessage")
    @Expose
    private ErrorMessageArrival errorMessage;
    @SerializedName("realtimeArrivalList")
    @Expose
    private List<RealtimeArrival> realtimeArrivalList = null;

    public ErrorMessageArrival getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessageArrival errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<RealtimeArrival> getRealtimeArrivalList() {
        return realtimeArrivalList;
    }

    public void setRealtimeArrivalList(List<RealtimeArrival> realtimeArrivalList) {
        this.realtimeArrivalList = realtimeArrivalList;
    }
}