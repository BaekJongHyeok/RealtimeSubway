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
        if(stationNm.equals("서울역")){
            stationNm = "서울";
        }
        return stationNm;
    }

    public void setStationNm(String stationNm) {
        this.stationNm = stationNm;
    }

    public String getLineNum() {
        String stripLineNum = lineNum.substring(0,lineNum.length());
        if(stripLineNum.equals("우이신설경전철")){
             stripLineNum = "우이신설선";
        } else {
        }
        return stripLineNum;
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
