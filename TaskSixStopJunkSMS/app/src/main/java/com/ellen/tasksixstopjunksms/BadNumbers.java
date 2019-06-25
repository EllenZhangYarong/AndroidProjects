package com.ellen.tasksixstopjunksms;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by ellen on 15/11/19.
 */
public class BadNumbers implements BaseColumns, Parcelable {
    public final static String AUTHORITY = "com.ellen.tasksixstopjunksms.JunkSMSContentProvider";
    public static final Uri BADNUMBER_URI = Uri.parse(
            "content://" + AUTHORITY + "/badnumbers");

    //Column Name of table badnumbers
    public final static String BADNUMBER = "badnumbers_numbers";
    public final static String NUMBERID = "badnumbers_id";
    public final static Parcelable.Creator<BadNumbers> CREATOR = new Parcelable.Creator<BadNumbers>() {
        public BadNumbers createFromParcel(Parcel source) {
            return new BadNumbers(source);
        }

        public BadNumbers[] newArray(int size) {
            return new BadNumbers[size];
        }
    };
    private String badnumber;
    private String numberid;

    public BadNumbers(Parcel source) {
        numberid = source.readString();
        badnumber = source.readString();
    }

    public String getBadnumber() {
        return badnumber;
    }

    public void setBadnumber(String badnumber) {
        this.badnumber = badnumber;
    }

    public String getNumberid() {
        return numberid;
    }

    public void setNumberid(String numberid) {
        this.numberid = numberid;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(numberid);
        dest.writeString(badnumber);

    }
}
