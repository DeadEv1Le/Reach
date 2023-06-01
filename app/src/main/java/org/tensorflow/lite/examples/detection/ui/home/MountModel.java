package org.tensorflow.lite.examples.detection.ui.home;

import java.io.Serializable;

import android.os.Parcel;
import android.os.Parcelable;

public class MountModel implements Parcelable {
    private String mountainName;
    private String mountainType;
    private String description;
    private String difficulty;
    private String distance;
    private int elevation;
    private String firstHike;
    private String location;
    private String itemImage;
    private String infoImage;

    public MountModel() {
        // Default constructor required for Firebase
    }

    public MountModel(String mountainName, String mountainType, String description, String difficulty,
                      String distance, int elevation, String firstHike, String location, String itemImage, String infoImage) {
        this.mountainName = mountainName;
        this.mountainType = mountainType;
        this.description = description;
        this.difficulty = difficulty;
        this.distance = distance;
        this.elevation = elevation;
        this.firstHike = firstHike;
        this.location = location;
        this.itemImage = itemImage;
        this.infoImage = infoImage;
    }

    protected MountModel(Parcel in) {
        mountainName = in.readString();
        mountainType = in.readString();
        description = in.readString();
        difficulty = in.readString();
        distance = in.readString();
        elevation = in.readInt();
        firstHike = in.readString();
        location = in.readString();
        itemImage = in.readString();
        infoImage = in.readString();
    }

    public static final Creator<MountModel> CREATOR = new Creator<MountModel>() {
        @Override
        public MountModel createFromParcel(Parcel in) {
            return new MountModel(in);
        }

        @Override
        public MountModel[] newArray(int size) {
            return new MountModel[size];
        }
    };

    public String getMountainName() {
        return mountainName;
    }

    public void setMountainName(String mountainName) {
        this.mountainName = mountainName;
    }

    public String getMountainType() {
        return mountainType;
    }

    public void setMountainType(String mountainType) {
        this.mountainType = mountainType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDistance() {
        return distance;
    }

    public void setDistance(String distance) {
        this.distance = distance;
    }

    public int getElevation() {
        return elevation;
    }

    public void setElevation(int elevation) {
        this.elevation = elevation;
    }

    public String getFirstHike() {
        return firstHike;
    }

    public void setFirstHike(String firstHike) {
        this.firstHike = firstHike;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getItemImage() {
        return itemImage;
    }

    public void setItemImage(String itemImage) {
        this.itemImage = itemImage;
    }

    public String getInfoImage() {
        return infoImage;
    }

    public void setInfoImage(String infoImage) {
        this.infoImage = infoImage;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mountainName);
        dest.writeString(mountainType);
        dest.writeString(description);
        dest.writeString(difficulty);
        dest.writeString(distance);
        dest.writeInt(elevation);
        dest.writeString(firstHike);
        dest.writeString(location);
        dest.writeString(itemImage);
        dest.writeString(infoImage);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}