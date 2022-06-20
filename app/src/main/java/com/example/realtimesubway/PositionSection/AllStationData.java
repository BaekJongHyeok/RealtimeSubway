package com.example.realtimesubway.PositionSection;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;

import java.util.ArrayList;
import java.util.List;

public class AllStationData {
    private String stationName;
    private String frCode;
    private List<PositionData> updnLine;

    public AllStationData(String frCode, String stationName, List<PositionData> updnLine) {
        this.stationName = stationName;
        this.updnLine = updnLine;
    }

    public String getStationName() {
        return this.stationName;
    }

    public String getFrCode() {
        return this.frCode;
    }

    public List<PositionData> getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(List<PositionData> updnLine) {
        this.updnLine = updnLine;
    }
}
