package com.ellen.taskeightbaidufirstpage.models;

/**
 * Created by ellen on 15/12/7.
 * <p/>
 * Datum Model for About me page
 */
public class AboutMeDatum {

    private String content;
    private int imageUrl;

    public AboutMeDatum(String content, int imageUrl) {
        this.content = content;
        this.imageUrl = imageUrl;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


}
