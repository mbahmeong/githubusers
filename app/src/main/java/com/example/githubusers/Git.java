package com.example.githubusers;

import android.os.Parcel;
import android.os.Parcelable;

public class Git implements Parcelable {
    private int photo;
    private String name;
    private String username;
    private String location;
    private String repository;
    private String company;
    private String followers;
    private String following;

    protected Git(Parcel in) {
        photo = in.readInt();
        name = in.readString();
        username = in.readString();
        location = in.readString();
        repository = in.readString();
        company = in.readString();
        followers = in.readString();
        following = in.readString();
    }
    //dibawah ini kuncinya
    public Git() {

    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(photo);
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(location);
        dest.writeString(repository);
        dest.writeString(company);
        dest.writeString(followers);
        dest.writeString(following);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Git> CREATOR = new Creator<Git>() {
        @Override
        public Git createFromParcel(Parcel in) {
            return new Git(in);
        }

        @Override
        public Git[] newArray(int size) {
            return new Git[size];
        }
    };

    //getter and setter hasil generate
    public int getPhoto() {
        return photo;
    }

    public void setPhoto(int photo) {
        this.photo = photo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getRepository() {
        return repository;
    }

    public void setRepository(String repository) {
        this.repository = repository;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowing() {
        return following;
    }

    public void setFollowing(String following) {
        this.following = following;
    }
}
