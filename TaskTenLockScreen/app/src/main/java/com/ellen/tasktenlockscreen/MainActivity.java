package com.ellen.tasktenlockscreen;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //Three button used to Register, unregister as a system component, and exute the lock of the screen
    private Button btnRegister, btnUnregister, btnLockScreen;

    //used to show some hints
    private TextView textView;

    private DevicePolicyManager devicePolicyManager;
    private ComponentName componentName;
    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

    }

    /*
    * Initiate the view , as general using find by id
    * */
    private void initView() {
        devicePolicyManager = (DevicePolicyManager) getSystemService(DEVICE_POLICY_SERVICE);
        componentName = new ComponentName(MainActivity.this, MyDeviceManager.class);

        btnRegister = (Button) findViewById(R.id.btnRegister);
        btnUnregister = (Button) findViewById(R.id.btnUnregister);
        btnLockScreen = (Button) findViewById(R.id.btnLockScreen);
        textView = (TextView) findViewById(R.id.textView);


        btnRegister.setOnClickListener(this);
        btnUnregister.setOnClickListener(this);
        btnLockScreen.setOnClickListener(this);

        showButtons();

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.btnRegister:
                Log.e("TAG:", "Register Button was clicked");

                Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
                intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN,
                        componentName);
                intent.putExtra(DevicePolicyManager.EXTRA_ADD_EXPLANATION, "Start One key to lock the screen");
                startActivity(intent);

                showUnregisterButton();

                break;
            case R.id.btnUnregister:
                Log.e("TAG:", "Unregister Button was clicked");

                devicePolicyManager.removeActiveAdmin(
                        componentName);

                showRegisterButton();

                break;

            //excute lock the screen
            case R.id.btnLockScreen:
                Log.e("TAG:", "Lock Screen Button was clicked");

                //Must acquire the system right to do
                devicePolicyManager.lockNow();
                finish();
                break;
        }

    }

    //Dispay different button (Register Button)
    public void showRegisterButton() {
        textView.setText(R.string.hintwords);
        btnRegister.setVisibility(View.VISIBLE);
        btnUnregister.setVisibility(View.GONE);
        btnLockScreen.setVisibility(View.GONE);

    }

    //Display different buttons(Unregister Button and LockScreen Button)
    public void showUnregisterButton() {
        textView.setText(R.string.hintwords2);
        btnRegister.setVisibility(View.GONE);
        btnUnregister.setVisibility(View.VISIBLE);
        btnLockScreen.setVisibility(View.VISIBLE);
    }

    void showButtons(){
        if (devicePolicyManager != null && devicePolicyManager.isAdminActive(componentName)) {

            showUnregisterButton();

        } else {

            showRegisterButton();

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        showButtons();
    }
}
