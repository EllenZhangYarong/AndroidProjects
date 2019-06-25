/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ellen.taskelevenlearntosing.utils;

import java.io.Serializable;
import java.util.Comparator;

/**
 * content,fromTime,toTime and others
 *
 * @author authorfu
 */
public class Sentence implements Serializable {//
    private static String TAG = "Sentence";

    /*
    * fromTime：一句开始的时间：毫秒数
    * toTime：一句结束时间
    * content：一句歌词内容
    * index：当前句在一个歌词文件里的索引位置
    * */
    private long fromTime = -1;
    private long toTime = -1;
    private String content = "";
    private int index = -1;

    //构造函数
    public Sentence(long fromTime) {
        this("", fromTime, -1);
    }

    public Sentence(String content) {
        this(content, -1, -1);
    }

    public Sentence(String content, long fromTime) {
        this(content, fromTime, -1);
    }

    public Sentence(String content, long fromTime, long toTime) {
        this.content = content;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }


    /**
     * 一句开始到结束共计需要多少时间
     * 后面线程用到
     *
     * */
    public long getDuring() {
        return toTime - fromTime + 1;
    }



    /**
     * check if time is between fromTime and toTime if time is negative return true
     **/
    public boolean isInTime(long time) {
        if (time < 0)
            return true;
        boolean fromTimeCheck = (fromTime == -1 || time >= fromTime);
        boolean toTimeCheck = (toTime == -1 || time <= toTime);
        return fromTimeCheck && toTimeCheck;
    }

    /*
    *
    * 成员变量的getter setter 方法
    *
    * */
    public void setContent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setFromTime(long fromTime) {
        this.fromTime = fromTime;
    }

    public long getFromTime() {
        return fromTime;
    }

    public void setToTime(long toTime) {
        this.toTime = toTime;
    }

    public long getToTime() {
        return toTime;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    /*
    * 序列化
    * */
    public String toString() {

        return "{index:" + index + "|" + fromTime + "(" + content + ")" + toTime + "}";
    }

    /*
    * 通过比较两个句子，来获得开始时间和结束时间的差值
    * */
    public static class SentenceComparator implements Comparator<Sentence> {

        @Override
        public int compare(Sentence sent1, Sentence sent2) {
            return (int) (sent1.getFromTime() - sent2.getFromTime());
        }

    }
}
