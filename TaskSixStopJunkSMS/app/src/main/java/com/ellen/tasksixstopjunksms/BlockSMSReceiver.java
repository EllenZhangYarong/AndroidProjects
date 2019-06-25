package com.ellen.tasksixstopjunksms;

import android.content.BroadcastReceiver;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Parcel;
import android.telephony.SmsMessage;

import java.text.SimpleDateFormat;
import java.util.HashMap;

import static com.ellen.tasksixstopjunksms.BadNumbers.BADNUMBER;
import static com.ellen.tasksixstopjunksms.BadNumbers.BADNUMBER_URI;

public class BlockSMSReceiver extends BroadcastReceiver {
    final static String ACTION = "com.ellen.tasksixstopjunksms.intent.action.BlockSMSReceiver";
    HashMap<String, String> map = null;

    public BlockSMSReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {

        System.out.println("已经可以接收广播了！");

        String action = intent.getAction().toString();
        if (action.equals(Intent.ACTION_BOOT_COMPLETED)) {
            System.out.println("OnReceiver is already start !!!!!!!!");

        } else {
            Bundle extras = intent.getExtras();
            if (extras == null) {
                return;
            }

            final Object[] pdusSMS = (Object[]) intent.getExtras().get("pdus");

            for (Object pdu : pdusSMS) {
                SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdu);
                final String number = smsMessage.getOriginatingAddress();
                final String message = smsMessage.getMessageBody();

                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                final String senttime = formatter.format(smsMessage.getTimestampMillis());

                //Query the number or Content is the bad things

                Cursor cursor2 = context.getContentResolver().query(KeyWords.KEYWORDS_URI, null, null, null, null);
                Cursor cursor1 = context.getContentResolver().query(BADNUMBER_URI, null, null, null, null);

                while (cursor1.moveToNext()) {

//                    System.out.println("The Keyword is --------------\n" + keyword + "\n------------");
                    //First,if the number is bad number, abort broadcast,else
                    //If the message contains the keywords ,then abort broadcast
                    String badNumber = cursor1.getString(cursor1.getColumnIndex(BADNUMBER));
                    if (badNumber.equals(number)) {
                        abortBroadcast();
                        writeToDB(context, number, message, senttime);
                        break;
                    } else {
                        while (cursor2.moveToNext()) {
                            String keyword = cursor2.getString(cursor2.getColumnIndex(KeyWords.KEYWORDS));
                            if (message.toLowerCase().indexOf(keyword.toLowerCase()) != -1) {
                                abortBroadcast();
                                writeToDB(context, number, message, senttime);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    private void writeToDB(Context context, String number, String message, String senttime) {
        ContentValues values = new ContentValues();
        values.clear();
        values.put(JunkSMS.RECEIVEDTIME, senttime);
        values.put(JunkSMS.SENTNUMBER, number);
        values.put(JunkSMS.SMSCONTENT, message);

        context.getContentResolver().insert(JunkSMS.JUNKSMS_URI, values);
    }
}
