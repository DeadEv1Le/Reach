package org.tensorflow.lite.examples.detection.ui.post;


public class DataClass {
    private String dataImage;

    private String userImage;
    private String dataTitle;
    private String dataDesc;

    private String userName;
    private String placeName;
    private String key;

    private String category;

    private String contacts;


    public DataClass(String dataImage, String dataTitle, String dataDesc,  String userName, String userImage, String placeName, String category, String contacts) {
        this.dataImage = dataImage;
        this.dataTitle = dataTitle;
        this.dataDesc = dataDesc;
        this.userName = userName;
        this.userImage = userImage;
        this.placeName = placeName;
        this.category = category;
        this.contacts = contacts;
    }



    public DataClass() {
    }

    public String getContacts() {
        return contacts;
    }

    public void setContacts(String contacts) {
        this.contacts = contacts;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getDataImage() {
        return dataImage;
    }

    public void setDataImage(String dataImage) {
        this.dataImage = dataImage;
    }

    public String getDataTitle() {
        return dataTitle;
    }

    public void setDataTitle(String dataTitle) {
        this.dataTitle = dataTitle;
    }

    public String getDataDesc() {
        return dataDesc;
    }

    public void setDataDesc(String dataDesc) {
        this.dataDesc = dataDesc;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

}