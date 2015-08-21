package com.example.giangdam.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Giang.Dam on 8/5/2015.
 */
public class ProductDesire {


    @SerializedName("ProductName")
    public String ProductName;

    @SerializedName("RetailerName")
    public String RetailerName;

    @SerializedName("DesiresCount")
    public int DesiresCount;


    @SerializedName("ProductImage")
    public String ProductImage;

    @SerializedName("Latitude")
    public double Latitude;

    @SerializedName("Longitude")
    public double Longitude;

    public double getLatitude() {
        return Latitude;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public String getProductImage() {
        return ProductImage;
    }

    public void setProductImage(String productImage) {
        ProductImage = productImage;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getRetailerName() {
        return RetailerName;
    }

    public void setRetailerName(String retailerName) {
        RetailerName = retailerName;
    }

    public int getDesiresCount() {
        return DesiresCount;
    }

    public void setDesiresCount(int desiresCount) {
        DesiresCount = desiresCount;
    }





}
