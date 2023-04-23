package org.tensorflow.lite.examples.detection.ui.mountInfo;


public class  InfoModel{
    private String name;
    private String location;
    private int height;
    private String description;

    public InfoModel(String name, String location, int height, String description) {
        this.name = name;
        this.location = location;
        this.height = height;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }

    public int getHeight() {
        return height;
    }

    public String getDescription() {
        return description;
    }
}