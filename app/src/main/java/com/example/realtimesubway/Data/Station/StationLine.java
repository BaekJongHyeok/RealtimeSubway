package com.example.realtimesubway.Data.Station;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class StationLine {
    private String lineNum;

    public StationLine(){}
    public StationLine(String lineNum){
        this.lineNum = lineNum;
    }

    public String getLineNum() {
        return lineNum;
    }

    public void setLineNum(String lineNum) {
        this.lineNum = lineNum;
    }

    @Override
    public String toString() {
        return lineNum;
    }
}
