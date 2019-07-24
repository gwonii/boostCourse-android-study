package com.gmail.hc.gwnoii.mylistview;

public class SingerItem {

    private String name;
    private String phoneNum;
    private int imgResource;

    public SingerItem(String name, String phoneNum, int imgResource) {
        this.name = name;
        this.phoneNum = phoneNum;
        this.imgResource = imgResource;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public int getImgResource() {
        return imgResource;
    }


    @Override
    public String toString() {
        return "SingerItem{" +
                "name='" + name + '\'' +
                ", phoneNum='" + phoneNum + '\'' +
                ", imgResource=" + imgResource +
                '}';
    }
}
