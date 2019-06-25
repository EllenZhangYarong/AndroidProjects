package com.ellen.tasksixstopjunksms;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    GridView gvSetting;
    private Cursor cursor = null;
    private String id;
    private String sentNumber;
    private String smsContent;
    private String receiveTime;
    private ArrayList<HashMap<String, String>> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showListView();

/*

          //  delete wrong database
        this.deleteDatabase("junksmsdb");
        this.deleteDatabase("junksms.db");
        File dbFile = new File("/data/data/tasksixstopjunksms/databases/junksms.db");
        dbFile.delete();

        //Insert the first data when the new database is empty,just for test
        for(int i =0; i <3; i++) {
            Context context = MainActivity.this;
            ContentValues values = new ContentValues();
            Uri uriJunkSMS = JunkSMS.JUNKSMS_URI;
            values.put(JunkSMS.SENTNUMBER, "10010"+i);
            values.put(JunkSMS.SMSCONTENT, "This is a Junk SMS. You should delete it right now");
            values.put(JunkSMS.RECEIVEDTIME, "2015-05-17 11:23:35");
            getContentResolver().insert(uriJunkSMS, values);
        }*/
    }

    private void showListView() {

        listView = (ListView) this.findViewById(R.id.lvJunkSMS);
        ArrayList<HashMap<String, String>> list = getJunkSMS();

        final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.listview_main,
                new String[]{"sentNumber", "smsContent", "receiveTime", "id"},
                new int[]{R.id.tvSentNumber, R.id.tvSMSContent, R.id.tvSentTime, R.id.tvid});

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap map = (HashMap) adapter.getItem(position);
                if (map.containsKey("id")) {
                    String junkSMSId = map.get("id").toString();
                    getContentResolver().delete(JunkSMS.JUNKSMS_URI,
                            JunkSMS.JUNKSMSID + "=?",
                            new String[]{junkSMSId});
                }
                showListView();
                return true;
            }
        });
    }

    private ArrayList<HashMap<String, String>> getJunkSMS() {
        list = new ArrayList<HashMap<String, String>>();
        cursor = getContentResolver().query(JunkSMS.JUNKSMS_URI, null, null, null, null);
        while (cursor.moveToNext()) {
            HashMap<String, String> mapJunkSMS = new HashMap<>();
            sentNumber = cursor.getString(cursor.getColumnIndex(JunkSMS.SENTNUMBER));
            smsContent = cursor.getString(cursor.getColumnIndex(JunkSMS.SMSCONTENT));
            receiveTime = cursor.getString(cursor.getColumnIndex(JunkSMS.RECEIVEDTIME));
            id = cursor.getString(cursor.getColumnIndex(JunkSMS.JUNKSMSID));
            mapJunkSMS.put("sentNumber", sentNumber);
            mapJunkSMS.put("smsContent", smsContent);
            mapJunkSMS.put("receiveTime", receiveTime);
            mapJunkSMS.put("id", id);
            list.add(mapJunkSMS);

        }
        cursor.close();

        return list;
    }

    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_MENU) {
            if (gvSetting == null) {
                loadBottomMenu();
            }
            if (gvSetting.getVisibility() == View.GONE) {
                gvSetting.setVisibility(View.VISIBLE);
            } else {
                gvSetting.setVisibility(View.GONE);
            }
        }
        return super.onKeyDown(keyCode, event);
    }

    private void loadBottomMenu() {

        gvSetting = (GridView) findViewById(R.id.gvSetting);
        gvSetting.setNumColumns(1);
        gvSetting.setGravity(Gravity.CENTER);
        ArrayList data = new ArrayList();
        HashMap<String, String> map = new HashMap<>();
        map.put("button", getString(R.string.setting));
        data.add(map);
        SimpleAdapter adapter = new SimpleAdapter(this, data,
                R.layout.bottommenuitem,
                new String[]{"button"},
                new int[]{R.id.tvSettingMenu});
        gvSetting.setAdapter(adapter);
        gvSetting.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, SettingActivity.class);

                startActivity(intent);
            }
        });
    }
}
