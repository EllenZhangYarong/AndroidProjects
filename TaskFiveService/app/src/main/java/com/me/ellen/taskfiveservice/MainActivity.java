package com.me.ellen.taskfiveservice;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.AsyncTask;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.logging.Handler;
import java.util.logging.LogRecord;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Button btnBindService, btnUnbindService;
    private Button btnStartService,btnStopService;
    private Button btnSyncData;
    private EditText etInput;
    private TextView tvFromService;

    private TextView tvCurrentValue;
    private AppService.MyBinder myBinder = null;
    //    private boolean isRunning =  false;
    private String data = "";
    private Intent intent=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        tvCurrentValue = (TextView) findViewById(R.id.tvCurrentValue);
        btnBindService = (Button) findViewById(R.id.btnBindService);
        btnUnbindService = (Button) findViewById(R.id.btnUnbindService);
        btnStartService = (Button) findViewById(R.id.btnStartService);
        btnStopService = (Button) findViewById(R.id.btnStopService);
        etInput = (EditText) findViewById(R.id.etInput);
        tvFromService = (TextView) findViewById(R.id.tvFromService);

        btnSyncData = (Button) findViewById(R.id.btnSyncData);

        btnBindService.setOnClickListener(this);
        btnUnbindService.setOnClickListener(this);
        btnStartService.setOnClickListener(this);
        btnStopService.setOnClickListener(this);
        btnSyncData.setOnClickListener(this);
        etInput.setOnClickListener(this);
    }

    public void onClick(View v) {
        switch ((v.getId())) {
            case R.id.btnStartService:
                startService(new Intent(this,AppService.class));
                break;

            case R.id.btnStopService:
                stopService(new Intent(this,AppService.class));
                break;

            case R.id.btnBindService:
                bindService(new Intent(this, AppService.class), this, Context.BIND_AUTO_CREATE);
                break;

            case R.id.btnUnbindService:
                unbindService(this);
                break;

            case R.id.btnSyncData:
                if(myBinder!=null){
                    myBinder.setData(etInput.getText().toString());
                }
                break;
            case R.id.etInput:
                etInput.setText("");

        }

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        System.out.println("Service destroied");
    }



    @Override
    public void onServiceDisconnected(ComponentName name) {

    }


   private android.os.Handler handler = new android.os.Handler(){
       @Override
       public void handleMessage(Message msg) {
           super.handleMessage(msg);
           String str = msg.getData().getString("data");
           tvFromService.setText(str);
       }
   };

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        System.out.println(service.getClass());
        myBinder = (AppService.MyBinder) service;
        myBinder.getService().setCallBack(new AppService.CallBack() {
            @Override
            public void onDataChange(String data) {
                Message msg = new Message();
                Bundle b = new Bundle();
                b.putString("data", data);
                msg.setData(b);
                handler.sendMessage(msg);
            }
        });

    }





/*    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnStartAppService:
                startService(new Intent(this,AppService.class));
                isRunning = true;

                new Thread(){
                    public void run(){
                        int count = 0;
                        while (isRunning) {
                            try {
                                System.out.println("Curren Value is " + count);
                                Thread.sleep(1000);
                                count++;
                                new AnotherTask().execute(String.format("Current Value is %d",count));
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }.start();

                break;
            case R.id.btnStopAppService:
                stopService(new Intent(this,AppService.class));
                isRunning = false;
        }

    }

    private class AnotherTask extends AsyncTask<String,Void,String> {


        @Override
        protected String doInBackground(String... params) {

            return params[0];
        }

        protected void onPostExecute(String result){
            tvCurrentValue = (TextView) findViewById(R.id.tvCurrentValue);
            tvCurrentValue.setText(result);
        }
    }*/

/*    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        startService(new Intent(this,AppService.class));
    }*/


}
