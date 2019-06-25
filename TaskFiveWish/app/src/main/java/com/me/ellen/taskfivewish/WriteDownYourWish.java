package com.me.ellen.taskfivewish;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WriteDownYourWish extends AppCompatActivity {
    private Button btnOk,btnCancel;
    private EditText etMyWishIs;
    private TextView txHintNothing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_down_your_wish);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        etMyWishIs = (EditText) findViewById(R.id.etMyWishIs);
        txHintNothing = (TextView) findViewById(R.id.txHintNothing);

        btnOk = (Button) findViewById(R.id.btnOk);
        btnCancel = (Button) findViewById(R.id.btnCancel);

        final Intent i = new Intent();

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    case R.id.btnOk:
                        if(etMyWishIs.getText().toString().equals("")){
                            txHintNothing.setText("亲，你还没有许愿呢〜许一个吧！");

                        }
                        else {
                            i.putExtra("wish", etMyWishIs.getText().toString());
                            setResult(1, i);
                            finish();
                        }
                        break;

                    case R.id.btnCancel:
                        i.putExtra("wish", "您取消了许愿！");
                        setResult(0, i);
                        finish();
                        break;


                }

            }
        };

        Button btTry = (Button) findViewById(R.id.btnOk);
        Button btReplay = (Button) findViewById(R.id.btnCancel);

        btTry.setOnClickListener(clickListener);
        btReplay.setOnClickListener(clickListener);

    }

}
