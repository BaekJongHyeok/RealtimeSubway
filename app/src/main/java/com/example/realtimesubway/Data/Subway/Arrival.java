package com.example.realtimesubway.Data.Subway;

public class Arrival {
    private String updnLine;
    private String trainLineNm;
    private String subwayHeading;
    private String bstatnNm;
    private String arvlMsg2;
    private String arvlMsg3;

    public Arrival(){}
    public Arrival(String updnLine, String trainLineNm, String subwayHeading, String bstatnNm, String arvlMsg2, String arvlMsg3){
        this.updnLine = updnLine;
        this.trainLineNm = trainLineNm;
        this.subwayHeading = subwayHeading;
        this.bstatnNm = bstatnNm;
        this.arvlMsg2 = arvlMsg2;
        this.arvlMsg3 = arvlMsg3;
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

    public String getBstatnNm() {
        return bstatnNm;
    }

    public void setBstatnNm(String bstatnNm) {
        this.bstatnNm = bstatnNm;
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

    @Override
    public String toString() {
        return trainLineNm + '\n' +
                "내리실문 : " + subwayHeading +'\n' +
                "종착역 : " + bstatnNm + '\n' +
                "남은 시간 : " + arvlMsg2 +'\n' +
                "현재 위치 : " + arvlMsg3 + '\n' + '\n';
    }
}
