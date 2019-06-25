package com.me.ellen.startotherservice;

import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.me.ellen.taskfiveservice.AppServiceInterface;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {
    private Button btnStartOtherService, btnStopOtherService;
    private Button btnBindOtherService, btnUnbindOtherService;
    private TextView tvFromServiceMessage;

    private Intent serviceIntent;
    private AppServiceInterface myBinder = null;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvFromServiceMessage = (TextView) findViewById(R.id.tvFromServiceMessage);
        btnStartOtherService = (Button) findViewById(R.id.btnStartOtherService);
        btnStopOtherService = (Button) findViewById(R.id.btnStopOtherService);
        btnBindOtherService = (Button) findViewById(R.id.btnBindOtherService);
        btnUnbindOtherService = (Button) findViewById(R.id.btnUnbindOtherService);

        tvFromServiceMessage = (TextView) findViewById(R.id.tvFromServiceMessage);
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName("com.me.ellen.taskfiveservice", "com.me.ellen.taskfiveservice.AppService"));

        btnStartOtherService.setOnClickListener(this);
        btnStopOtherService.setOnClickListener(this);
        btnBindOtherService.setOnClickListener(this);
        btnUnbindOtherService.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent i = new Intent();
        i.setComponent(new ComponentName("com.me.ellen.taskfiveservice","com.me.ellen.taskfiveservice.AppService"));

        switch (v.getId()) {
            case R.id.btnStartOtherService:
                startService(serviceIntent);
                break;
            case R.id.btnStopOtherService:
                stopService(serviceIntent);
                break;
            case R.id.btnBindOtherService:
                bindService(serviceIntent,this,Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindOtherService:
                unbindService(this);
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        System.out.println("Bind Service");
        System.out.println(service);

        myBinder = (AppServiceInterface.Stub.asInterface(service)) ;
        System.out.println(myBinder.getClass());

//        myBinder.getService().setCallBack(new AppService.CallBack() {
//            @Override
//            public void onDataChange(String data) {
//                Message msg = new Message();
//                Bundle b = new Bundle();
//                b.putString("data", data);
//                msg.setData(b);
//                handler.sendMessage(msg);
//            }
//        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {


    }

    private android.os.Handler handler = new android.os.Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            String str = msg.getData().getString("data");
            tvFromServiceMessage.setText(str);
        }
    };




/*    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartOtherService:
                startService(i);
                break;
            case R.id.btnStopOtherService:
                stopService(i);
                break;
        }

    }*/


}
