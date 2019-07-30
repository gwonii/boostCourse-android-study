package com.gmail.hc.gwnoii.parcelable;

import android.os.Parcel;
import android.os.Parcelable;

public class SimpleData implements Parcelable {

    private int number;
    private String name;

    public SimpleData(int number, String name) {
        this.number = number;
        this.name = name;
    }

    private SimpleData (Parcel data){
        number = data.readInt();
        name = data.readString();
    }

    public static final Parcelable.Creator CREATOR = new Parcelable.Creator(){
        public SimpleData createFromParcel (Parcel data) {
            return new SimpleData(data);           // Parcel로 부터 데이터를 받고 새로운 SimpleData 객체를 만든다.
        }

        public SimpleData[] newArray(int size) {
            return new SimpleData[size];            // SimpleData를 기본적으로 array의 형태로 만들어 들어오는 개수에 맞게 array에 저장시킨다.
        }

    };


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(number);
        dest.writeString(name);
    }

    public int getNumber() {
        return number;
    }

    public String getName() {
        return name;
    }
}
