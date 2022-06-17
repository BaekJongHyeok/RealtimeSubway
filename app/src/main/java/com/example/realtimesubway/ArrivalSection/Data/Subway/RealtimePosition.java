package com.example.realtimesubway.ArrivalSection.Data.Subway;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RealtimePosition  {

    @SerializedName("beginRow")
    @Expose
    private Object beginRow;
    @SerializedName("endRow")
    @Expose
    private Object endRow;
    @SerializedName("curPage")
    @Expose
    private Object curPage;
    @SerializedName("pageRow")
    @Expose
    private Object pageRow;
    @SerializedName("totalCount")
    @Expose
    private Integer totalCount;
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
    private String subwayNm;
    @SerializedName("statnId")
    @Expose
    private String statnId;
    @SerializedName("statnNm")
    @Expose
    private String statnNm;
    @SerializedName("trainNo")
    @Expose
    private String trainNo;
    @SerializedName("lastRecptnDt")
    @Expose
    private String lastRecptnDt;
    @SerializedName("recptnDt")
    @Expose
    private String recptnDt;
    @SerializedName("updnLine")
    @Expose
    private String updnLine;
    @SerializedName("statnTid")
    @Expose
    private String statnTid;
    @SerializedName("statnTnm")
    @Expose
    private String statnTnm;
    @SerializedName("trainSttus")
    @Expose
    private String trainSttus;
    @SerializedName("directAt")
    @Expose
    private String directAt;
    @SerializedName("lstcarAt")
    @Expose
    private String lstcarAt;

    public Object getBeginRow() {
        return beginRow;
    }

    public void setBeginRow(Object beginRow) {
        this.beginRow = beginRow;
    }

    public Object getEndRow() {
        return endRow;
    }

    public void setEndRow(Object endRow) {
        this.endRow = endRow;
    }

    public Object getCurPage() {
        return curPage;
    }

    public void setCurPage(Object curPage) {
        this.curPage = curPage;
    }

    public Object getPageRow() {
        return pageRow;
    }

    public void setPageRow(Object pageRow) {
        this.pageRow = pageRow;
    }

    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

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

    public String getSubwayNm() {
        return subwayNm;
    }

    public void setSubwayNm(String subwayNm) {
        this.subwayNm = subwayNm;
    }

    public String getStatnId() {
        return statnId;
    }

    public void setStatnId(String statnId) {
        this.statnId = statnId;
    }

    public String getStatnNm() {
        return statnNm;
    }

    public void setStatnNm(String statnNm) {
        this.statnNm = statnNm;
    }

    public String getTrainNo() {
        return trainNo;
    }

    public void setTrainNo(String trainNo) {
        this.trainNo = trainNo;
    }

    public String getLastRecptnDt() {
        return lastRecptnDt;
    }

    public void setLastRecptnDt(String lastRecptnDt) {
        this.lastRecptnDt = lastRecptnDt;
    }

    public String getRecptnDt() {
        return recptnDt;
    }

    public void setRecptnDt(String recptnDt) {
        this.recptnDt = recptnDt;
    }

    public String getUpdnLine() {
        return updnLine;
    }

    public void setUpdnLine(String updnLine) {
        this.updnLine = updnLine;
    }

    public String getStatnTid() {
        return statnTid;
    }

    public void setStatnTid(String statnTid) {
        this.statnTid = statnTid;
    }

    public String getStatnTnm() {
        return statnTnm;
    }

    public void setStatnTnm(String statnTnm) {
        this.statnTnm = statnTnm;
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

    public String getLstcarAt() {
        return lstcarAt;
    }

    public void setLstcarAt(String lstcarAt) {
        this.lstcarAt = lstcarAt;
    }

}