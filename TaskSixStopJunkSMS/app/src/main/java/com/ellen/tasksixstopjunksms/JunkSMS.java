package com.ellen.tasksixstopjunksms;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by ellen on 15/11/19.
 */
public class JunkSMS implements BaseColumns, Parcelable {
    public final static String AUTHORITY = "com.ellen.tasksixstopjunksms.JunkSMSContentProvider";
    public static final Uri JUNKSMS_URI = Uri.parse(
            "content://" + AUTHORITY + "/junksms");

    //Column Name of table junksms
    public final static String JUNKSMSID = "junksms_id";
    public final static String SENTNUMBER = "junksms_numbers";
    public final static String SMSCONTENT = "junksms_content";
    public final static String RECEIVEDTIME = "junksms_receivetime";

    public final static Parcelable.Creator<JunkSMS> CREATOR = new Parcelable.Creator<JunkSMS>() {
        public JunkSMS createFromParcel(Parcel source) {
            return new JunkSMS(source);
        }

        public JunkSMS[] newArray(int size) {
            return new JunkSMS[size];
        }
    };


    private int junkSMSId;
    private String sentNumber;
    private String smsContent;
    private String receiveTime;

    public JunkSMS(Parcel source) {
        junkSMSId = source.readInt();
        sentNumber = source.readString();
        smsContent = source.readString();
        receiveTime = source.readString();

    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(junkSMSId);
        dest.writeString(sentNumber);
        dest.writeString(smsContent);
        dest.writeString(receiveTime);

    }
}
