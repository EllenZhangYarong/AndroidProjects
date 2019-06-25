package com.me.ellen.taskfourcalculator;

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button[] btnNumberSymbolClear;
    private Double dResult;
    private String firstNumber;
    private String secondNumber;
    private String operator;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

//        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
//        fab.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
//            }
//        });

        tvResult = (TextView) findViewById(R.id.tvResult);
        tvResult.setText("0");
        dResult = 0.0;
        firstNumber = "";
        secondNumber = "";
        operator="";

        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {
                    case R.id.btnAdd:
                        prepareCal();
                        operator = "+";
                        break;

                    case R.id.btnSubduction:
                        prepareCal();
                        operator = "-";
                        break;


                    case R.id.btnMultiply:
                        prepareCal();
                        operator = "*";
                        break;

                    case R.id.btnDivide:
                        prepareCal();
                        operator = "/";
                        break;

                    case R.id.btnZero:
                        secondNumber += "0";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnOne:
                        secondNumber += "1";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnTwo:
                        secondNumber += "2";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnThree:
                        secondNumber += "3";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnFour:
                        secondNumber += "4";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnFive:
                        secondNumber += "5";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnSix:
                        secondNumber += "6";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnSeven:
                        secondNumber += "7";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnEight:
                        secondNumber += "8";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnNine:
                        secondNumber += "9";
                        tvResult.setText(secondNumber);
                        break;
                    case R.id.btnClear:
                        firstNumber = "";
                        secondNumber = "";
                        operator = "";
                        dResult = 0.0;
                        tvResult.setText("0");
                        break;
                    case R.id.btnEquals:
                        if (firstNumber.equals("") || operator.equals("")) {

                        } else {
                            firstNumber = dResult.toString();
                            secondNumber = tvResult.getText().toString();
                            dResult = calculate(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber), operator);

                            printResult();
                            operator="";
                            firstNumber="";
                            secondNumber="";
                        }
                        break;

                    default:
                }

            }
        };

        findUs();
        for (int i = 0; i < 16; i++) {
            btnNumberSymbolClear[i].setOnClickListener(clickListener);

        }

    }

    private void printResult() {
        if (dResult % 1 != 0) {
            tvResult.setText(dResult.toString());
        } else {
            Integer i = dResult.intValue();
            tvResult.setText(i.toString());
        }
    }

    private void prepareCal() {
        if(operator.equals("")){
            firstNumber = tvResult.getText().toString();
            dResult = Double.parseDouble(firstNumber);
        }else{
            if(firstNumber.equals("")){
                firstNumber="0";
            }else {
                firstNumber = dResult.toString();
            }
            secondNumber = tvResult.getText().toString();
            dResult = calculate(Double.parseDouble(firstNumber), Double.parseDouble(secondNumber), operator);
            tvResult.setText(dResult.toString());

            printResult();
        }
        secondNumber="";
    }


    public double calculate(double op1, double op2, String operator) {

        switch (operator) {
            case "+":
                dResult = op1 + op2;
                break;
            case "-":
                dResult = op1 - op2;
                break;
            case "*":
                dResult = op1 * op2;
                break;
            case "/":
                if(op2==0.0){
                    dResult=0.0;
                    tvResult.setText(R.string.cannotZero);
                }else {
                    dResult = op1 / op2;
                }break;
            default:
        }
        return dResult;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void findUs() {

        btnNumberSymbolClear = new Button[16];

        btnNumberSymbolClear[0] = (Button) findViewById(R.id.btnZero);
       btnNumberSymbolClear[1] = (Button) findViewById(R.id.btnOne);
        btnNumberSymbolClear[2] = (Button) findViewById(R.id.btnTwo);
        btnNumberSymbolClear[3] = (Button) findViewById(R.id.btnThree);
        btnNumberSymbolClear[4] = (Button) findViewById(R.id.btnFour);
        btnNumberSymbolClear[5] = (Button) findViewById(R.id.btnFive);
        btnNumberSymbolClear[6] = (Button) findViewById(R.id.btnSix);
        btnNumberSymbolClear[7] = (Button) findViewById(R.id.btnSeven);
        btnNumberSymbolClear[8] = (Button) findViewById(R.id.btnEight);
        btnNumberSymbolClear[9] = (Button) findViewById(R.id.btnNine);
        btnNumberSymbolClear[10] = (Button) findViewById(R.id.btnAdd);
        btnNumberSymbolClear[11] = (Button) findViewById(R.id.btnSubduction);
        btnNumberSymbolClear[12] = (Button) findViewById(R.id.btnMultiply);
        btnNumberSymbolClear[13] = (Button) findViewById(R.id.btnDivide);
        btnNumberSymbolClear[14] = (Button) findViewById(R.id.btnClear);
        btnNumberSymbolClear[15] = (Button) findViewById(R.id.btnEquals);

    }
}
