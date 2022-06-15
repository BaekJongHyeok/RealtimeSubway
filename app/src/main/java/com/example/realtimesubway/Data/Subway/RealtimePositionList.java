package com.example.realtimesubway.Data.Subway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RealtimePositionList {

    @SerializedName("errorMessage")
    @Expose
    private ErrorMessage errorMessage;
    @SerializedName("realtimePositionList")
    @Expose
    private List<RealtimePosition> realtimePositionList = null;

    public ErrorMessage getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public List<RealtimePosition> getRealtimePositionList() {
        return realtimePositionList;
    }

    public void setRealtimePositionList(List<RealtimePosition> realtimePositionList) {
        this.realtimePositionList = realtimePositionList;
    }

}