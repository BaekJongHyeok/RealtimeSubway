package com.example.realtimesubway.PositionSection.AllStation.OpenApi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row {

    @SerializedName("STATION_CD")
    @Expose
    private String stationCd;
    @SerializedName("STATION_NM")
    @Expose
    private String stationNm;
    @SerializedName("STATION_NM_ENG")
    @Expose
    private String stationNmEng;
    @SerializedName("LINE_NUM")
    @Expose
    private String lineNum;
    @SerializedName("FR_CODE")
    @Expose
    private String frCode;

    public String getStationCd() {
        return stationCd;
    }

    public void setStationCd(String stationCd) {
        this.stationCd = stationCd;
    }

    public String getStationNm() {
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public String getStationNmEng() {
        return stationNmEng;
    }

    public void setStationNmEng(String stationNmEng) {
        this.stationNmEng = stationNmEng;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    public String getFrCode() {
        return frCode;
    }

    public void setFrCode(String frCode) {
        this.frCode = frCode;
    }

}