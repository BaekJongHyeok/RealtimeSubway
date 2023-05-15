package com.example.realtimesubway.data;

public class Line {
    private final int imageResource;
    private final String name;

    public Line(int imageResource, String name) {
        this.imageResource = imageResource;
        this.name = name;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getName() {
        return name;
    }
}
