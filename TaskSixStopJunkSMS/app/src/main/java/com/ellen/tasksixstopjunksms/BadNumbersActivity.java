package com.ellen.tasksixstopjunksms;

import android.app.AlertDialog;
import android.content.ContentValues;
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
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

import static com.ellen.tasksixstopjunksms.BadNumbers.BADNUMBER;
import static com.ellen.tasksixstopjunksms.BadNumbers.BADNUMBER_URI;

public class BadNumbersActivity extends AppCompatActivity implements View.OnClickListener {
    ArrayList<HashMap<String, String>> list = null;
    private Cursor cursor = null;
    private String badNumbers;
    private String id;
    private HashMap<String, String> map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bad_numbers);
        showListView();
    }

    private void showListView() {

        ListView listView = (ListView) this.findViewById(R.id.listView);

        list = getBadNumbers();

        final SimpleAdapter adapter = new SimpleAdapter(this, list, R.layout.simple_list_item1,
                new String[]{"badNumbers", "id"},
                new int[]{R.id.tvBadNumbersOrKeywords, R.id.tvid});

        listView.setAdapter(adapter);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

//                long itemId = adapter.getItemId(position);
//                System.out.println(itemId);

                HashMap map = (HashMap) adapter.getItem(position);
                if (map.containsKey("id")) {
//                    System.out.println("Yes, i guess right");
//
//                    System.out.println(map.get("id").toString());

                    String numberId = map.get("id").toString();
                    getContentResolver().delete(BADNUMBER_URI, BadNumbers.NUMBERID + "=?", new String[]{numberId});


                }
                showListView();
                return true;
            }
        });

        findViewById(R.id.btnAddBadNumbers).setOnClickListener(this);
    }

    private ArrayList<HashMap<String, String>> getBadNumbers() {
        list = new ArrayList<HashMap<String, String>>();
        cursor = getContentResolver().query(BadNumbers.BADNUMBER_URI, null, null, null, null);

        while (cursor.moveToNext()) {
            HashMap<String, String> mapBadNumbers = new HashMap<>();

            badNumbers = cursor.getString(cursor.getColumnIndex(BadNumbers.BADNUMBER));
            id = cursor.getString(cursor.getColumnIndex(BadNumbers.NUMBERID));


            mapBadNumbers.put("badNumbers", badNumbers);
            mapBadNumbers.put("id", id);

            list.add(mapBadNumbers);

        }
        cursor.close();

        return list;
    }

    @Override
    public void onClick(View v) {
        // TODO: 15/11/23
        //Open Dialog , add new numbers
        showAddNewBadNumber();
    }

    private void showAddNewBadNumber() {

        final HashMap<String, String> mapNewBadNumber = new HashMap<>();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View addNewVew = inflater.inflate(R.layout.addnew, null);

        final EditText etNewNumOrKey = (EditText) addNewVew.findViewById(R.id.etNewNumOrKey);

        builder.setTitle(R.string.addnewnumber);
        builder.setView(addNewVew);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String number = etNewNumOrKey.getText().toString();
                //System.out.println("The New Number is --------------------" + number + "\n -----------");
                mapNewBadNumber.put("number", number);
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
//        System.out.println("-------------------The Number is ---------------");
//        System.out.println(map.get("number"));
//        System.out.println("------------------------------------------------");

        String number = String.valueOf(map.get("number")).trim();
        if (!number.equals("")) {

            //Write to DB
            ContentValues values = new ContentValues();
            values.clear();
            values.put(BadNumbers.BADNUMBER, number);

            getContentResolver().insert(BADNUMBER_URI, values);

        }
        showListView();
    }
}
