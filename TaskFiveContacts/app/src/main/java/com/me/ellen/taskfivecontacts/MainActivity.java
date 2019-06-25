package com.me.ellen.taskfivecontacts;

import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;


public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    private Cursor cursor;
    private String name, number, contactid;

    private ArrayList<HashMap<String, String>> list;
    private HashMap<String, String> mapNewContact;


    private AlertDialog adDialOrSMS, addContactDialog;
    private Button btnAddContact;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        showListView();

        findViewById(R.id.btnAddContact).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showAddContact();
            }
        });

    }

    private void showAddContact() {
        final HashMap<String, String> map = new HashMap<>();

        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        final View addContactView = inflater.inflate(R.layout.addcontact, null);

        final EditText etName = (EditText) addContactView.findViewById(R.id.etName);
        final EditText etPhone = (EditText) addContactView.findViewById(R.id.etPhone);

        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.addContact);
        builder.setView(addContactView);
        builder.setCancelable(false);
        builder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                map.put("name", etName.getText().toString());
                map.put("phone", etPhone.getText().toString());
                mapNewContact = map;
                addContactToSystem(mapNewContact);
            }
        });

        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                addContactDialog.dismiss();
            }
        });

        addContactDialog = builder.create();
        addContactDialog.show();
    }

    private void addContactToSystem(HashMap map) {

        System.out.println(map.get("name"));
        System.out.println(map.get("phone"));
        String name = String.valueOf(map.get("name")).trim();
        String phone = String.valueOf(map.get("phone")).trim();
        if (name.equals("") && phone.equals("")) {


        } else {

            ContentValues values = new ContentValues();
            Uri uriRawContact = getContentResolver().
                    insert(ContactsContract.RawContacts.CONTENT_URI, values);
            long rawContactId = ContentUris.parseId(uriRawContact);

            values.clear();

            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.StructuredName.GIVEN_NAME, name);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);

            values.clear();
            values.put(ContactsContract.Data.RAW_CONTACT_ID, rawContactId);
            values.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE);
            values.put(ContactsContract.CommonDataKinds.Phone.NUMBER, phone);
            getContentResolver().insert(ContactsContract.Data.CONTENT_URI, values);
        }

        showListView();

    }


    public void showListView() {
        ListView listView = (ListView) this.findViewById(R.id.listView);
        ArrayList<HashMap<String, String>> list = getPhoneNameAndNumber();
        SimpleAdapter adapter = new SimpleAdapter(this, list, android.R.layout.simple_list_item_2,
                new String[]{"name", "number"},
                new int[]{android.R.id.text1, android.R.id.text2});
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(this);

    }

    private ArrayList<HashMap<String, String>> getPhoneNameAndNumber() {
        list = new ArrayList<HashMap<String, String>>();
        cursor = getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                null, null, null, null);
        while (cursor.moveToNext()) {

            HashMap<String, String> map = new HashMap<>();

            contactid = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));

            if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {

                Cursor cPhone = getContentResolver().query(
                        ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + "=?",
                        new String[]{contactid}, null);
                while (cPhone.moveToNext()) {
                    name = cPhone.getString(cPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    number = cPhone.getString(cPhone.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

                    map.put("name", name);
                    map.put("number", number);
                    list.add(map);

                    System.out.println("Phone is " + name);
                    System.out.println("Number is " + number);
                }
                cPhone.close();
            }
            if (cursor.isClosed()) {
                cursor.close();
                cursor = null;
            }

        }
        cursor.close();

        return list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


        HashMap<String, String> phoneMap = list.get(position);
        String myNumber = phoneMap.get("number");

        initChooseDailOrSMS(myNumber);
        adDialOrSMS.show();
    }

    private void initChooseDailOrSMS(String phonenumber) {
        final String myNumber = phonenumber;

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle(R.string.choose);
        builder.setCancelable(false);

        final String[] operation = new String[]{getString(R.string.makeacall), getString(R.string.sms)};
        AlertDialog.Builder builder1 = builder.setItems(operation, new DialogInterface.OnClickListener() {
            @TargetApi(Build.VERSION_CODES.M)
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent;

                if (0 == which) {

                    intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + myNumber));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

//                    System.out.println("Make A Call");
                } else {

                    intent = new Intent(Intent.ACTION_VIEW, Uri.parse("smsto:" + myNumber));
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    System.out.println("Send Message");
                }
                startActivity(intent);
            }
        });
        builder.setNeutralButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                adDialOrSMS.dismiss();
            }
        });
        adDialOrSMS = builder.create();
    }

}
