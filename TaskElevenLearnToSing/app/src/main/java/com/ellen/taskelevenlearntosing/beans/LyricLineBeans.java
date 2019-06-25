package com.ellen.taskelevenlearntosing.beans;

import java.io.Serializable;

/**
 * Created by ellen on 16/1/5.
 */
public class LyricLineBeans implements Serializable {

    private long fromTime;
    private long toTime;
    private String lineContent;

    public long getFromTime() {
        return fromTime;
    }

    public void setFromTime(long fromTime) {
        this.fromTime = fromTime;
    }

    public String getLineContent() {
        return lineContent;
    }

    public void setLineContent(String lineContent) {
        this.lineContent = lineContent;
    }

    public long getToTime() {
        return toTime;
    }

    public void setToTime(long toTime) {
        this.toTime = toTime;
    }

    public long getDuring() {
        return toTime - fromTime + 1;
    }

    @Override
    public String toString() {
        return "StartTime:" + fromTime + " EndTime:" + toTime + " LyricSentence:" + lineContent;
    }
}
