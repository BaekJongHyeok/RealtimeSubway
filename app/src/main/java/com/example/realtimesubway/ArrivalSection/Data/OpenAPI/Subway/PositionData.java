package com.example.realtimesubway.ArrivalSection.Data.OpenAPI.Subway;

public class PositionData {
    private String updnLine;
    private String subwayId;
    private String subwayNm;
    private String statnNm;
    private String recptnDt;
    private String trainSttus;
    private String trainNo;
    private String directAt;

    public PositionData(){};

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public String getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(String subwayNm) {
        this.subwayNm = subwayNm;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public String getTrainSttus() {
        return trainSttus;
    }

    public void setTrainSttus(String trainSttus) {
        this.trainSttus = trainSttus;
    }

    public String getDirectAt() {
        return directAt;
    }

    public void setDirectAt(String directAt) {
        this.directAt = directAt;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }
}
