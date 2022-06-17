package com.example.realtimesubway.PositionSection.LineList;

public class LineListData {
    private int lineImage;
    private String lineNm;

    public LineListData(int lineImage, String lineNm) {
        this.lineImage = lineImage;
        this.lineNm = lineNm;
    }

    public int getLineImage() {
        return lineImage;
    }

    public void setLineImage(int lineImage) {
        this.lineImage = lineImage;
    }

    public String getLineNm() {
        return lineNm;
    }

    public void setLineNm(String lineNm) {
        this.lineNm = lineNm;
    }
}
