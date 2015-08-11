package com.example.giangdam.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class Product {
    @SerializedName("Desires")
    public List<ProductDesire> Desires;

    public List<ProductDesire> getDesires() {
        return Desires;
    }

    public void setDesires(List<ProductDesire> desires) {
        Desires = desires;
    }
}
