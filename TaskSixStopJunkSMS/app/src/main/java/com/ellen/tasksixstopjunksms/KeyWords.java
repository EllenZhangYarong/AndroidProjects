package com.ellen.tasksixstopjunksms;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;
import android.provider.BaseColumns;

/**
 * Created by ellen on 15/11/19.
 */
public class KeyWords implements BaseColumns, Parcelable {

    public final static String AUTHORITY = "com.ellen.tasksixstopjunksms.JunkSMSContentProvider";
    public static final Uri KEYWORDS_URI = Uri.parse(
            "content://" + AUTHORITY + "/keywords");

    //Column Name of table keywords
    public final static String KEYWORDS = "keywords_keyword";
    public final static String KEYWORDSID = "badnumbers_id";
    public final static Parcelable.Creator<KeyWords> CREATOR = new Parcelable.Creator<KeyWords>() {
        public KeyWords createFromParcel(Parcel source) {
            return new KeyWords(source);
        }

        public KeyWords[] newArray(int size) {
            return new KeyWords[size];
        }
    };
    private String keywords;
    private String keywordsId;

    public KeyWords(Parcel source) {
        keywords = source.readString();
        keywordsId = source.readString();
    }

    public String getKeywords() {
        return keywords;
    }

    public void setKeywords(String keywords) {
        this.keywords = keywords;
    }

    public String getKeywordsId() {
        return keywordsId;
    }

    public void setKeywordsId(String keywordsId) {
        this.keywordsId = keywordsId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(keywords);
        dest.writeString(keywordsId);

    }
}
