package com.example.realtimesubway.SearchFilter;

public class SearchItem {
    String stationName;
    public SearchItem(String stationName){
        this.stationName = stationName;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }
}
