package com.example.realtimesubway.Data.Subway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealtimeArrival {

    @SerializedName("rowNum")
    @Expose
    private Integer rowNum;
    @SerializedName("selectedCount")
    @Expose
    private Integer selectedCount;
    @SerializedName("subwayId")
    @Expose
    private String subwayId;
    @SerializedName("subwayNm")
    @Expose
    private Object subwayNm;
    @SerializedName("updnLine")
    @Expose
    private String updnLine;
    @SerializedName("trainLineNm")
    @Expose
    private String trainLineNm;
    @SerializedName("subwayHeading")
    @Expose
    private String subwayHeading;
    @SerializedName("statnFid")
    @Expose
    private String statnFid;
    @SerializedName("statnNm")
    @Expose
    private String statnNm;
    @SerializedName("trainCo")
    @Expose
    private Object trainCo;
    @SerializedName("subwayList")
    @Expose
    private String subwayList;
    @SerializedName("statnList")
    @Expose
    private String statnList;
    @SerializedName("btrainSttus")
    @Expose
    private Object btrainSttus;
    @SerializedName("barvlDt")
    @Expose
    private String barvlDt;
    @SerializedName("btrainNo")
    @Expose
    private String btrainNo;
    @SerializedName("bstatnId")
    @Expose
    private String bstatnId;
    @SerializedName("bstatnNm")
    @Expose
    private String bstatnNm;
    @SerializedName("recptnDt")
    @Expose
    private String recptnDt;
    @SerializedName("arvlMsg2")
    @Expose
    private String arvlMsg2;
    @SerializedName("arvlMsg3")
    @Expose
    private String arvlMsg3;
    @SerializedName("arvlCd")
    @Expose
    private String arvlCd;

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getSelectedCount() {
        return selectedCount;
    }

    public void setSelectedCount(Integer selectedCount) {
        this.selectedCount = selectedCount;
    }

    public String getSubwayId() {
        return subwayId;
    }

    public void setSubwayId(String subwayId) {
        this.subwayId = subwayId;
    }

    public Object getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(Object subwayNm) {
        this.subwayNm = subwayNm;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getTrainLineNm() {
        return trainLineNm;
    }

    public void setTrainLineNm(String trainLineNm) {
        this.trainLineNm = trainLineNm;
    }

    public String getSubwayHeading() {
        return subwayHeading;
    }

    public void setSubwayHeading(String subwayHeading) {
        this.subwayHeading = subwayHeading;
    }

    public String getStatnFid() {
        return statnFid;
    }

    public void setStatnFid(String statnFid) {
        this.statnFid = statnFid;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public Object getTrainCo() {
        return trainCo;
    }

    public void setTrainCo(Object trainCo) {
        this.trainCo = trainCo;
    }


    public String getSubwayList() {
        return subwayList;
    }

    public void setSubwayList(String subwayList) {
        this.subwayList = subwayList;
    }

    public String getStatnList() {
        return statnList;
    }

    public void setStatnList(String statnList) {
        this.statnList = statnList;
    }

    public Object getBtrainSttus() {
        return btrainSttus;
    }

    public void setBtrainSttus(Object btrainSttus) {
        this.btrainSttus = btrainSttus;
    }

    public String getBarvlDt() {
        return barvlDt;
    }

    public void setBarvlDt(String barvlDt) {
        this.barvlDt = barvlDt;
    }

    public String getBtrainNo() {
        return btrainNo;
    }

    public void setBtrainNo(String btrainNo) {
        this.btrainNo = btrainNo;
    }

    public String getBstatnId() {
        return bstatnId;
    }

    public void setBstatnId(String bstatnId) {
        this.bstatnId = bstatnId;
    }

    public String getBstatnNm() {
        return bstatnNm;
    }

    public void setBstatnNm(String bstatnNm) {
        this.bstatnNm = bstatnNm;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public String getArvlMsg2() {
        return arvlMsg2;
    }

    public void setArvlMsg2(String arvlMsg2) {
        this.arvlMsg2 = arvlMsg2;
    }

    public String getArvlMsg3() {
        return arvlMsg3;
    }

    public void setArvlMsg3(String arvlMsg3) {
        this.arvlMsg3 = arvlMsg3;
    }

    public String getArvlCd() {
        return arvlCd;
    }

    public void setArvlCd(String arvlCd) {
        this.arvlCd = arvlCd;
    }

    @Override
    public String toString() {
        return "상하행 : " + updnLine + '\n' +
                "도착지방향 : " + trainLineNm + '\n' +
                "내리는 방향 : " + subwayHeading + '\n' +
                "종착역 명 : '" + bstatnNm + '\n' +
                "첫 도착메세지 : " + arvlMsg2 + '\n' +
                "두번째 도착메세지 : " + arvlMsg3 + '\n' + '\n';
    }
}
