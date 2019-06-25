package com.ellen.taskeightbaidufirstpage.models;

/**
 * Created by ellen on 15/12/9.
 */
public class ShopsInfoDatum {
    private String shopName, shopBrunch, amountOfSold;
    private int shopRank;
    private int shopPicUri;
    private String[] conditions;
    private Boolean isRepay;
    private Boolean isRefund;
    private Boolean isTicket;
    private Boolean isCoupons;

    public ShopsInfoDatum(String shopName, String shopBrunch,
                          String amountOfSold, int shopRank,
                          String[] conditions, int shopPicUri,
                          Boolean isRepay, Boolean isRefund, Boolean isTicket, Boolean isCoupons) {
        this.shopName = shopName;
        this.shopBrunch = shopBrunch;
        this.amountOfSold = amountOfSold;
        this.shopRank = shopRank;
        this.conditions = conditions;
        this.shopPicUri = shopPicUri;
        this.isRepay = isRepay;
        this.isRefund = isRefund;
        this.isTicket = isTicket;
        this.isCoupons = isCoupons;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public String getShopBrunch() {
        return shopBrunch;
    }

    public void setShopBrunch(String shopBrunch) {
        this.shopBrunch = shopBrunch;
    }

    public String getAmountOfSold() {
        return amountOfSold;
    }

    public void setAmountOfSold(String amountOfSold) {
        this.amountOfSold = amountOfSold;
    }

    public int getShopRank() {
        return shopRank;
    }

    public void setShopRank(int shopRank) {
        this.shopRank = shopRank;
    }

    public int getShopPicUri() {
        return shopPicUri;
    }

    public void setShopPicUri(int shopPicUri) {
        this.shopPicUri = shopPicUri;
    }

    public String[] getConditions() {
        return conditions;
    }

    public void setConditions(String[] conditions) {
        this.conditions = conditions;
    }

    public Boolean getIsRepay() {
        return isRepay;
    }

    public void setIsRepay(Boolean isRepay) {
        this.isRepay = isRepay;
    }

    public Boolean getIsRefund() {
        return isRefund;
    }

    public void setIsRefund(Boolean isRefund) {
        this.isRefund = isRefund;
    }

    public Boolean getIsTicket() {
        return isTicket;
    }

    public void setIsTicket(Boolean isTicket) {
        this.isTicket = isTicket;
    }

    public Boolean getIsCoupons() {
        return isCoupons;
    }

    public void setIsCoupons(Boolean isCoupons) {
        this.isCoupons = isCoupons;
    }
}
