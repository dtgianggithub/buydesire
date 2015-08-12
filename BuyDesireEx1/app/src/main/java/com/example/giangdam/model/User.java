package com.example.giangdam.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Giang.Dam on 8/12/2015.
 */
public class User {

    @SerializedName("id")
    String id;

    @SerializedName("name")
    String name;

    @SerializedName("link")
    String link;

    @SerializedName("picture")
    Picture picture;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Picture getPicture() {
        return picture;
    }

    public void setPicture(Picture picture) {
        this.picture = picture;
    }
}
