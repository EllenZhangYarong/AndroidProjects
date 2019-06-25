package com.ellen.tasktwelvejokes;

import java.io.Serializable;

/**
 * Created by ellen on 16/1/6.
 */
public class JokeBean implements Serializable {
    private static String TAG = "JokeBean";

    private String jokeId;
    private String jokeTitle;
    private String jokeDate;
    private String jokeDetail;

    public String getJokeId() {
        return jokeId;
    }

    public void setJokeId(String jokeId) {
        this.jokeId = jokeId;
    }

    public String getJokeDetail() {
        return jokeDetail;
    }

    public void setJokeDetail(String jokeDetail) {
        this.jokeDetail = jokeDetail;
    }

    public String getJokeDate() {
        return jokeDate;
    }

    public void setJokeDate(String jokeDate) {
        this.jokeDate = jokeDate;
    }

    public String getJokeTitle() {
        return jokeTitle;
    }

    public void setJokeTitle(String jokeTitle) {
        this.jokeTitle = jokeTitle;
    }

    @Override
    public String toString() {
        return TAG + "\nID : " + getJokeId()
                +"\nTitle : " + getJokeTitle()
                + "\nDetail: " + getJokeDetail()
                + "\nDate: " + getJokeDate();
    }
}
