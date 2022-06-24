package com.example.realtimesubway.PositionSection;

import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.PositionData;
import com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway.RealtimePosition;

import java.util.ArrayList;
import java.util.List;

public class AllStationData {
    private String stationName;
    private String frCode;
    private List<PositionData> upLine;
    private List<PositionData> downLine;

    public AllStationData(String frCode, String stationName, List<PositionData> upLine, List<PositionData> downLine) {
        this.stationName = stationName;
        this.upLine = upLine;
        this.downLine = downLine;
    }

    public String getStationName() {
        return this.stationName;
    }

    public String getFrCode() {
        return this.frCode;
    }

    public List<PositionData> getUpLine() {
        return upLine;
    }

    public void setUpLine(List<PositionData> upLine) {
        this.upLine = upLine;
    }

    public List<PositionData> getDownLine() {
        return downLine;
    }

    public void setDownLine(List<PositionData> downLine) {
        this.downLine = downLine;
    }
}
