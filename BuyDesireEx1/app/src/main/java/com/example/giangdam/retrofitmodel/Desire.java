
package com.example.giangdam.retrofitmodel;

import com.google.gson.annotations.Expose;


public class Desire {

    @Expose
    private String WishListID;
    @Expose
    private Integer ProductVariantID;
    @Expose
    private String ProductName;
    @Expose
    private String ProductImage;
    @Expose
    private Integer RetailerID;
    @Expose
    private String RetailerName;
    @Expose
    private Integer RetailerStoreID;
    @Expose
    private Double Latitude;
    @Expose
    private Double Longitude;
    @Expose
    private Integer UserID;
    @Expose
    private String UserProfileName;
    @Expose
    private Boolean IsInMyDesire;
    @Expose
    private Integer UserAddedProductID;
    @Expose
    private Integer DesiresCount;

    /**
     * 
     * @return
     *     The WishListID
     */
    public String getWishListID() {
        return WishListID;
    }

    /**
     * 
     * @param WishListID
     *     The WishListID
     */
    public void setWishListID(String WishListID) {
        this.WishListID = WishListID;
    }

    /**
     * 
     * @return
     *     The ProductVariantID
     */
    public Integer getProductVariantID() {
        return ProductVariantID;
    }

    /**
     * 
     * @param ProductVariantID
     *     The ProductVariantID
     */
    public void setProductVariantID(Integer ProductVariantID) {
        this.ProductVariantID = ProductVariantID;
    }

    /**
     * 
     * @return
     *     The ProductName
     */
    public String getProductName() {
        return ProductName;
    }

    /**
     * 
     * @param ProductName
     *     The ProductName
     */
    public void setProductName(String ProductName) {
        this.ProductName = ProductName;
    }

    /**
     * 
     * @return
     *     The ProductImage
     */
    public String getProductImage() {
        return ProductImage;
    }

    /**
     * 
     * @param ProductImage
     *     The ProductImage
     */
    public void setProductImage(String ProductImage) {
        this.ProductImage = ProductImage;
    }

    /**
     * 
     * @return
     *     The RetailerID
     */
    public Integer getRetailerID() {
        return RetailerID;
    }

    /**
     * 
     * @param RetailerID
     *     The RetailerID
     */
    public void setRetailerID(Integer RetailerID) {
        this.RetailerID = RetailerID;
    }

    /**
     * 
     * @return
     *     The RetailerName
     */
    public String getRetailerName() {
        return RetailerName;
    }

    /**
     * 
     * @param RetailerName
     *     The RetailerName
     */
    public void setRetailerName(String RetailerName) {
        this.RetailerName = RetailerName;
    }

    /**
     * 
     * @return
     *     The RetailerStoreID
     */
    public Integer getRetailerStoreID() {
        return RetailerStoreID;
    }

    /**
     * 
     * @param RetailerStoreID
     *     The RetailerStoreID
     */
    public void setRetailerStoreID(Integer RetailerStoreID) {
        this.RetailerStoreID = RetailerStoreID;
    }

    /**
     * 
     * @return
     *     The Latitude
     */
    public Double getLatitude() {
        return Latitude;
    }

    /**
     * 
     * @param Latitude
     *     The Latitude
     */
    public void setLatitude(Double Latitude) {
        this.Latitude = Latitude;
    }

    /**
     * 
     * @return
     *     The Longitude
     */
    public Double getLongitude() {
        return Longitude;
    }

    /**
     * 
     * @param Longitude
     *     The Longitude
     */
    public void setLongitude(Double Longitude) {
        this.Longitude = Longitude;
    }

    /**
     * 
     * @return
     *     The UserID
     */
    public Integer getUserID() {
        return UserID;
    }

    /**
     * 
     * @param UserID
     *     The UserID
     */
    public void setUserID(Integer UserID) {
        this.UserID = UserID;
    }

    /**
     * 
     * @return
     *     The UserProfileName
     */
    public String getUserProfileName() {
        return UserProfileName;
    }

    /**
     * 
     * @param UserProfileName
     *     The UserProfileName
     */
    public void setUserProfileName(String UserProfileName) {
        this.UserProfileName = UserProfileName;
    }

    /**
     * 
     * @return
     *     The IsInMyDesire
     */
    public Boolean getIsInMyDesire() {
        return IsInMyDesire;
    }

    /**
     * 
     * @param IsInMyDesire
     *     The IsInMyDesire
     */
    public void setIsInMyDesire(Boolean IsInMyDesire) {
        this.IsInMyDesire = IsInMyDesire;
    }

    /**
     * 
     * @return
     *     The UserAddedProductID
     */
    public Integer getUserAddedProductID() {
        return UserAddedProductID;
    }

    /**
     * 
     * @param UserAddedProductID
     *     The UserAddedProductID
     */
    public void setUserAddedProductID(Integer UserAddedProductID) {
        this.UserAddedProductID = UserAddedProductID;
    }

    /**
     * 
     * @return
     *     The DesiresCount
     */
    public Integer getDesiresCount() {
        return DesiresCount;
    }

    /**
     * 
     * @param DesiresCount
     *     The DesiresCount
     */
    public void setDesiresCount(Integer DesiresCount) {
        this.DesiresCount = DesiresCount;
    }

}
