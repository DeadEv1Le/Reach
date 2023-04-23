package org.tensorflow.lite.examples.detection.ui.home;

public class Mountain {
    private String name;
    private int height;
    private int image;

    public Mountain(String name, int height, int image) {
        this.name = name;
        this.height = height;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public int getHeight() {
        return height;
    }

    public int getImage() {
        return image;
    }
}
