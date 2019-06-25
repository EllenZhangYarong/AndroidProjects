package com.ellen.tasksixstopjunksms;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ellen.tasksixstopjunksms.BadNumbers.BADNUMBER_URI;

public class KeywordsActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<HashMap<String, String>> list = null;
    private Cursor cursor = null;
    private String keyword;
    private String id;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_keywords);

        showListView();
    }

    private void showListView() {

        ListView listView = (ListView) this.findViewById(R.id.listView);

        list = getKeywords();

        final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.simple_list_item1,
                new String[]{"keyword", "id"},
                new int[]{R.id.tvBadNumbersOrKeywords, R.id.tvid});

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                HashMap map = (HashMap) adapter.getItem(position);
                if (map.containsKey("id")) {
                    String keywordId = map.get("id").toString();
                    getContentResolver().delete(KeyWords.KEYWORDS_URI,
                            KeyWords.KEYWORDSID + "=?",
                            new String[]{keywordId});
                }

                showListView();
                return true;
            }
        });

        findViewById(R.id.btnAddNewKeywords).setOnClickListener(this);
    }

    private ArrayList<HashMap<String, String>> getKeywords() {
        list = new ArrayList<HashMap<String, String>>();
        cursor = getContentResolver().query(KeyWords.KEYWORDS_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            HashMap<String, String> mapKeywords = new HashMap<>();

            keyword = cursor.getString(cursor.getColumnIndex(KeyWords.KEYWORDS));
            id = cursor.getString(cursor.getColumnIndex(KeyWords.KEYWORDSID));


            mapKeywords.put("keyword", keyword);
            mapKeywords.put("id", id);

            list.add(mapKeywords);

        }
        cursor.close();

        return list;
    }

    @Override
    public void onClick(View v) {
        // TODO: 15/11/23
        //Open Dialog , add new numbers
        showAddNewKeywords();
    }

    private void showAddNewKeywords() {

        final HashMap<String, String> mapNewBadNumber = new HashMap<>();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View addNewVew = inflater.inflate(R.layout.addnew, null);

        final EditText etNewNumOrKey = (EditText) addNewVew.findViewById(R.id.etNewNumOrKey);

        builder.setTitle(R.string.addnewkey);
        builder.setView(addNewVew);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String keyword = etNewNumOrKey.getText().toString();

                mapNewBadNumber.put("keyword", keyword);
                map = mapNewBadNumber;
                addNewNumOrKey(map);//Write Table BadNumbers
            }
        });

        AlertDialog alertDialog;
        alertDialog = builder.create();
        alertDialog.show();

    }

    private void addNewNumOrKey(HashMap map) {

//        System.out.println("addNewNumOrKey");
//        System.out.println("-------------------The keyword is ---------------");
//        System.out.println(map.get("keyword"));
//        System.out.println("------------------------------------------------");

        String keyword = String.valueOf(map.get("keyword")).trim();
        if (!keyword.equals("")) {

            //Write to DB
            ContentValues values = new ContentValues();
            values.clear();
            values.put(KeyWords.KEYWORDS, keyword);
            getContentResolver().insert(KeyWords.KEYWORDS_URI, values);

        }
        showListView();
    }
}
