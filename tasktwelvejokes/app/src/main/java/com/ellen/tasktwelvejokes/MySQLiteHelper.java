package com.ellen.tasktwelvejokes;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by ellen on 16/4/13.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {


    public static final String TABLE_JOKES = "jokedetail";

    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "comment";
    public static final String COLUMN_DETAIL = "lat";
    public static final String COLUMN_DATE = "long";
    public static final String COLUMN_JOKEID = "radi";
    private static final String DATABASE_NAME = "joke.db";
    private static final int DATABASE_VERSION = 1;


    public static final String NEWTABLE = "CREATE TABLE  "
            + TABLE_JOKES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text not null," + COLUMN_DETAIL + "," + COLUMN_DATE + "," + COLUMN_JOKEID + ");";

    public static final String CREATTABLE = "CREATE TABLE IF NOT EXISTS "
            + TABLE_JOKES + "(" + COLUMN_ID
            + " integer primary key autoincrement, " + COLUMN_TITLE
            + " text not null," + COLUMN_DETAIL + "," + COLUMN_DATE + "," + COLUMN_JOKEID + ");";

    public static final String QUERYDATUM = "SELECT * FROM " + TABLE_JOKES;


    MySQLiteHelper(Context context) {
        super(context, context.getExternalFilesDir(null).getAbsolutePath() + "/"
                + DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase database) {
        database.execSQL(CREATTABLE);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.w(MySQLiteHelper.class.getName(),
                "Upgrading database from version " + oldVersion + " to "
                        + newVersion + ", which will destroy all old data");
        db.execSQL("DROP TABLE IF EXISTS " + NEWTABLE);

        onCreate(db);
    }

}
