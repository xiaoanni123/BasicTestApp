package com.example.lanxumit.testapplication.entity;

import android.os.Parcel;
import android.os.Parcelable;

import io.realm.RealmList;
import io.realm.RealmObject;

public class Dog extends RealmObject implements Parcelable {
    private String dogName;
    private String dogAge;

    public String getDogName() {
        return dogName;
    }

    public void setDogName(String dogName) {
        this.dogName = dogName;
    }

    public String getDogAge() {
        return dogAge;
    }

    public void setDogAge(String dogAge) {
        this.dogAge = dogAge;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(dogName);
        dest.writeString(dogAge);
    }

    public static final Creator<Dog> CREATOR = new Creator<Dog>() {
        @Override
        public Dog createFromParcel(Parcel in) {
            Dog dog = new Dog();
            dog.dogName=in.readString();
            dog.dogAge=in.readString();
            return dog;
        }

        @Override
        public Dog[] newArray(int size) {
            return new Dog[size];
        }
    };

}
