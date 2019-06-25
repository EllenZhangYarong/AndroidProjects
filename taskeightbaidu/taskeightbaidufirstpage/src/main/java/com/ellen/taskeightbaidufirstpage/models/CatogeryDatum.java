package com.ellen.taskeightbaidufirstpage.models;

/**
 * Created by ellen on 15/12/9.
 */
public class CatogeryDatum extends AboutMeDatum {

    private String adCorner;

    public CatogeryDatum(String content, int imageUrl, String adCorner) {
        super(content, imageUrl);
        this.adCorner = adCorner;
    }

    public String getAdCorner() {
        return adCorner;
    }

    public void setAdCorner(String adCorner) {
        this.adCorner = adCorner;
    }
}
