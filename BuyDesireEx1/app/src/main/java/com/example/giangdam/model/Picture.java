package com.example.giangdam.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Giang.Dam on 8/12/2015.
 */
public class Picture {
    @SerializedName("data")
    Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }
}
