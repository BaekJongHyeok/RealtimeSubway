package com.example.realtimesubway.Data.Station;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Row  {

    @SerializedName("STATION_CD")
    @Expose
    private String stationCd;
    @SerializedName("STATION_NM")
    @Expose
    private String stationNm;
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

    public String getLineNum() {
        return lineNum.substring(0,lineNum.length());
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
