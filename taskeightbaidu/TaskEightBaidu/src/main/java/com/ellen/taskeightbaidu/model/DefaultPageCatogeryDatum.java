package com.ellen.taskeightbaidu.model;

/**
 * Created by ellen on 15/12/7.
 * <p/>
 * Datum Model for About me page
 */
public class DefaultPageCatogeryDatum {

    private String hint;
    private String catogery;
    private int imageUrl;

    public DefaultPageCatogeryDatum(String hint, String content, int imageUrl) {
        this.hint = hint;
        this.catogery = content;
        this.imageUrl = imageUrl;
    }

    public String getHint() {
        return hint;
    }

    public void setHint(String hint) {
        this.hint = hint;
    }

    public String getCatogery() {
        return catogery;
    }

    public void setCatogery(String catogery) {
        this.catogery = catogery;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

}
