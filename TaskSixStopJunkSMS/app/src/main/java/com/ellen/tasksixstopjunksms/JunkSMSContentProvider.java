package com.ellen.tasksixstopjunksms;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.os.Environment;
import android.util.Log;

public class JunkSMSContentProvider extends ContentProvider {

    //    database and columns
    public static final String DATABASE_NAME = "junksmsdb";
    public static final String TABLE_NAME_JUNKSMS = "junksms";
    public static final String TABLE_NAME_BADNUMBERS = "badnumbers";
    public static final String TABLE_NAME_KEYWORDS = "keywords";
    public static final int DATABASE_VERSION = 1;
    public final static String AUTHORITY = "com.ellen.tasksixstopjunksms.JunkSMSContentProvider";
    private static final String TAG = "JunkSMSContentProvider";
    private static final UriMatcher URIMATCHER = new UriMatcher(UriMatcher.NO_MATCH);

    static {

        URIMATCHER.addURI(AUTHORITY,
                "keywords", 1);
        URIMATCHER.addURI(AUTHORITY,
                "keywords/#", 2);
        URIMATCHER.addURI(AUTHORITY,
                "badnumbers", 3);
        URIMATCHER.addURI(AUTHORITY,
                "badnumbers/#", 4);
        URIMATCHER.addURI(AUTHORITY,
                "junksms", 5);
        URIMATCHER.addURI(AUTHORITY,
                "junksms/#", 6);

    }

    JunkSMSDatabaseHelper junkSMSDatabaseHelper;
    private SQLiteDatabase DB;

    public JunkSMSContentProvider() {
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {

        DB = junkSMSDatabaseHelper.getWritableDatabase();
        int count = 0;
        switch (URIMATCHER.match(uri)) {
            case 1:
            case 2:
                count = DB.delete(TABLE_NAME_KEYWORDS, selection, selectionArgs);
                break;
            case 3:
            case 4:
                count = DB.delete(TABLE_NAME_BADNUMBERS, selection, selectionArgs);
                break;
            case 5:
            case 6:
                count = DB.delete(TABLE_NAME_JUNKSMS, selection, selectionArgs);
                break;
            default:
                throw new IllegalArgumentException("Unknown Uri : " + uri);
        }

        return count;
    }

    @Override
    public String getType(Uri uri) throws UnsupportedOperationException {

        switch (URIMATCHER.match(uri)) {
            case 1:
                return "vnd.android.cursor.dir/keywords";
            case 2:
                return "vnd.android.cursor.item/keywords";
            case 3:
                return "vnd.android.cursor.dir/badnumbers";
            case 4:
                return "vnd.android.cursor.item/badnumbers";
            case 5:
                return "vnd.android.cursor.dir/junksms";
            case 6:
                return "vnd.android.cursor.item/junksms";
            default:
                throw new IllegalArgumentException("Unkown Uri : " + uri.toString());

        }
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {

        DB = junkSMSDatabaseHelper.getWritableDatabase();
        Uri uriInsert = null;
        long rowId = 0;
        switch (URIMATCHER.match(uri)) {
            case 1:
                rowId = DB.insert(TABLE_NAME_KEYWORDS, KeyWords.KEYWORDS, values);
                break;

            case 3:
                rowId = DB.insert(TABLE_NAME_BADNUMBERS, BadNumbers.BADNUMBER, values);
                break;

            case 5:
                rowId = DB.insert(TABLE_NAME_JUNKSMS, JunkSMS.SMSCONTENT, values);
                break;

            default:
                throw new IllegalArgumentException("Unkown Uri : " + uri.toString());
        }
        uriInsert = ContentUris.withAppendedId(uri, rowId);
        return uriInsert;
    }

    @Override
    public boolean onCreate() {

        Context context = getContext();
        junkSMSDatabaseHelper = new JunkSMSDatabaseHelper(
                context, DATABASE_NAME, null, DATABASE_VERSION);

        DB = junkSMSDatabaseHelper.getWritableDatabase();
        Log.e(TAG, Environment.getExternalStorageDirectory().toString());
        return (DB == null) ? false : true;

    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection,
                        String[] selectionArgs, String sortOrder) {

        DB = junkSMSDatabaseHelper.getReadableDatabase();
        Cursor cursor = null;
        long id = 0;
        String where = "";

        switch (URIMATCHER.match(uri)) {

            case 1:
                cursor = DB.query(
                        TABLE_NAME_KEYWORDS, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case 2:
                id = ContentUris.parseId(uri);
                where = "TABLE_NAME_KEYWORDS._ID = " + id;
                if (selection != null && !"".equals(selection)) {
                    where += "and " + selection;
                }
                cursor = DB.query(TABLE_NAME_KEYWORDS, projection, where, selectionArgs, null, null, sortOrder);
                break;
            case 3:
                cursor = DB.query(
                        TABLE_NAME_BADNUMBERS, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case 4:
                id = ContentUris.parseId(uri);
                where = "TABLE_NAME_BADNUMBERS._ID = " + id;
                if (selection != null && !"".equals(selection)) {
                    where += "and " + selection;
                }
                cursor = DB.query(TABLE_NAME_BADNUMBERS, projection, where, selectionArgs, null, null, sortOrder);
                break;
            case 5:
                cursor = DB.query(
                        TABLE_NAME_JUNKSMS, projection, selection, selectionArgs, null, null, sortOrder);
                break;

            case 6:
                id = ContentUris.parseId(uri);
                where = "TABLE_NAME_JUNKSMS._ID = " + id;
                if (selection != null && !"".equals(selection)) {
                    where += "and " + selection;
                }
                cursor = DB.query(TABLE_NAME_JUNKSMS, projection, where, selectionArgs, null, null, sortOrder);
                break;
        }
        return cursor;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection,
                      String[] selectionArgs) {
        // TODO: Implement this to handle requests to update one or more rows.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    private class JunkSMSDatabaseHelper extends SQLiteOpenHelper {

        private static final String KEYWORDS_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME_KEYWORDS +
                "(" + KeyWords.KEYWORDSID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KeyWords.KEYWORDS + " TEXT)";
        private static final String BADNUMBER_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME_BADNUMBERS +
                "(" + BadNumbers.NUMBERID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                BadNumbers.BADNUMBER + " TEXT)";
        private static final String JUNKSMS_TABLE_CREATE = "CREATE TABLE " + TABLE_NAME_JUNKSMS +
                "(" + JunkSMS.JUNKSMSID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                JunkSMS.SENTNUMBER + " TEXT, " +
                JunkSMS.SMSCONTENT + " TEXT, " +
                JunkSMS.RECEIVEDTIME + " TEXT)";

        public JunkSMSDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(KEYWORDS_TABLE_CREATE);
            db.execSQL(BADNUMBER_TABLE_CREATE);
            db.execSQL(JUNKSMS_TABLE_CREATE);

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.w(TAG, "Upgrading database from version from " + oldVersion + " to "
                    + newVersion + "which will destory all old data");
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_KEYWORDS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_BADNUMBERS);
            db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_JUNKSMS);
            onCreate(db);

        }
    }

}
