package com.ellen.taskelevenlyric;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by ellen on 16/1/5.
 */
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static String TAG = "MainActivity";
    MyAsynTask myAsynTaskPlay;
    Pattern numberPattern;
    private TextView tvDisplayLine;
    private Button btnPlay, btnStop;
    private LyricLineBeans lyricLineBeans;
    private ArrayList<LyricLineBeans> lineBeansArrayList;
    private MediaPlayer mediaPlayer = null;

    /*
    * Transform numbers before : into milliseconds
    * */
    public static long parseTime(String[] ts) {
        double seconds;
        seconds = Double.parseDouble(ts[1]);
        double s = Integer.parseInt(ts[0]) * 60 + seconds;
        return Math.round(s * 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tvDisplayLine = (TextView) findViewById(R.id.tvDisplayOneLine);
        btnPlay = (Button) findViewById(R.id.btnPlay);
        btnStop = (Button) findViewById(R.id.btnStop);
        btnPlay.setOnClickListener(this);
        btnStop.setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = null;

            if (myAsynTaskPlay != null) {
                myAsynTaskPlay.cancel(true);
            }
        }
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnStop:
                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;
                }
                if (myAsynTaskPlay != null) {
                    myAsynTaskPlay.cancel(true);
                }
                break;

            case R.id.btnPlay:
                if (mediaPlayer == null) {
                    mediaPlayer = MediaPlayer.create(this, R.raw.single_linzhixuan);
                    mediaPlayer.start();
                    mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                        @Override
                        public void onCompletion(MediaPlayer mp) {
                            mediaPlayer = null;
                        }
                    });

                    myAsynTaskPlay = new MyAsynTask();
                    myAsynTaskPlay.execute();
                }


                break;
        }
    }

    /*
    * Extract time from one whole line of lrc file
    *
    * */
    private String[] extractTimeAndContentFromLine(String wholeLine) {


        int posBracketRight = wholeLine.indexOf("]");
        //get time between [ and ]
        String time = wholeLine.substring(1, posBracketRight);

//        Log.d(TAG,"time is " + time);

        String[] ts = time.split("\\:", 2);

//        Log.d(TAG,"ts[0] = " + ts[0]);
//        Log.d(TAG,"ts[1] = " + ts[1]);

        String[] result = new String[2];
        if (isNumber(ts[0])) {
            result[0] = parseTime(ts) + ""; //numbers before : fromTime
            result[1] = wholeLine.substring(posBracketRight + 1); //lyric line content
        }
//        Log.d(TAG, "time =  " + result[0] + "     content = " + result[1]);

        return result;
    }

    private boolean isNumber(String str) {
        numberPattern = Pattern.compile("[0-9]+");
        Matcher isNum = numberPattern.matcher(str);
        return isNum.matches();
    }

    public class MyAsynTask extends AsyncTask<Void, Integer, Long> {
        private String wholeLine = "";

        @Override
        protected Long doInBackground(Void... params) {

            String[] timeAndContent = null;

            lineBeansArrayList = new ArrayList<LyricLineBeans>();

            //Parse lrc file
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getResources()
                            .openRawResource(R.raw.single_lyric)));

            try {
                while ((wholeLine = reader.readLine()) != null) {
                    if (isCancelled()) {
                        break;
                    }
                    timeAndContent = extractTimeAndContentFromLine(wholeLine);
                    lyricLineBeans = new LyricLineBeans();
                    lyricLineBeans.setFromTime(Long.parseLong(timeAndContent[0]));
                    lyricLineBeans.setLineContent(timeAndContent[1]);
                    lineBeansArrayList.add(lyricLineBeans);
                }

                for (int j = 0; j < lineBeansArrayList.size() - 1; j++) {
                    if (isCancelled()) break;
                    lineBeansArrayList.get(j).setToTime(lineBeansArrayList.get(j + 1).getFromTime());
//                    Log.d(TAG, lineBeansArrayList.get(j).toString());

                    publishProgress(j);
                    if (j < lineBeansArrayList.size() - 1) {
                        try {
                            Thread.sleep(lineBeansArrayList.get(j).getDuring());
                        } catch (InterruptedException e) {
                            isCancelled();
//                            e.printStackTrace();
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                if(reader!=null){
                    try {
                        reader.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            tvDisplayLine.setText(lineBeansArrayList.get(values[0]).getLineContent());
        }
    }
}
