package com.me.ellen.taskfiveaidlserver;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.RemoteException;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private Button btnBindService,btnUnbindService;
    private TextView tvServerMessage;
    private IAppServiceAIDLInterface binder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        findViewById(R.id.btnBindService).setOnClickListener(this);
        findViewById(R.id.btnUnbindService).setOnClickListener(this);
        tvServerMessage = (TextView) findViewById(R.id.tvServerMessage);


    }



    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnBindService:
                bindService(new Intent(MainActivity.this,AppServer.class),this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btnUnbindService:
                unbindService(this);
                break;

        }

    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {

        System.out.println("You are here onServiceConnected\n Bind Service");
        binder = IAppServiceAIDLInterface.Stub.asInterface(service);
        try {
            binder.registCallBack(onServiceCallback);
//            System.out.println("I am coming ---- after registcallback");
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }
    private TimerServiceCallBack.Stub onServiceCallback = new TimerServiceCallBack.Stub() {

        @Override
        public void onTimer(int count) throws RemoteException {

            Message msg = new Message();
            msg.arg1 = count;
////            System.out.println("I am here ---- onTimer");
//            msg.obj = MainActivity.this;
            handler.sendMessage(msg);

        }

    };

    private final MyHandler handler = new MyHandler();

    private class MyHandler extends android.os.Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            int count = msg.arg1;
////            System.out.println("count = " + count);
//
//            MainActivity _this = (MainActivity) msg.obj;
            tvServerMessage.setText(String.format("Current Value is %d" , count));
        }
    }
    @Override
    public void onServiceDisconnected(ComponentName name) {

        callUnregisterBinder();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        callUnregisterBinder();
    }

    private void callUnregisterBinder(){
        try {
            binder.unregistCallBack(onServiceCallback);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
