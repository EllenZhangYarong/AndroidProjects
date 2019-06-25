package com.me.ellen.taskfivewish;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private Button btnWhatsYourWish;
    private TextView tvYourWishIs,tvWishTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        btnWhatsYourWish = (Button) findViewById(R.id.btnWhatsYourWish);
        tvYourWishIs = (TextView) findViewById(R.id.tvYourWishIs);
        tvWishTitle = (TextView) findViewById(R.id.tvWishTitle);

        btnWhatsYourWish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this,WriteDownYourWish.class);
                startActivityForResult(i,0);

            }
        });


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(resultCode==0){
            tvWishTitle.setText("");
            tvYourWishIs.setText("");
            Toast.makeText(MainActivity.this,data.getStringExtra("wish").toString(),Toast.LENGTH_SHORT).show();
        }else {
            tvWishTitle.setText("您今天的愿望是：");
            tvYourWishIs.setText(data.getStringExtra("wish").toString());
        }

    }
}


