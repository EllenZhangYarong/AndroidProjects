package com.ellen.tasksixstopjunksms;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class SettingActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnEditKeywords, btnEditNumbers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        findViewById(R.id.btnEditKeywords).setOnClickListener(this);
        findViewById(R.id.btnEditNumbers).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnEditKeywords:
                startActivity(new Intent(this, KeywordsActivity.class));
                break;
            case R.id.btnEditNumbers:
                startActivity(new Intent(this, BadNumbersActivity.class));
                break;
        }
    }
}
