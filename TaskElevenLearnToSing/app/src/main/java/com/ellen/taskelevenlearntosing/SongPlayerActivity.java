package com.ellen.taskelevenlearntosing;

import android.media.MediaPlayer;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ellen.taskelevenlearntosing.utils.AudioPlayer;
import com.ellen.taskelevenlearntosing.utils.AudioRecorder;
import com.ellen.taskelevenlearntosing.utils.Sentence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Hashtable;

public class SongPlayerActivity extends AppCompatActivity implements View.OnClickListener {
    public static String TAG = "SongPlayerActivity";

    private TextView tvDisplayLine, tvDispayWhole, tvDisplaySongName;
    private ImageButton ibMicUsing, ibPlay, ibMic, ibStop, ibPlayRecord; //ibPause,

    private MediaPlayer mediaPlayer, mediaPlayer2;

    //Record and Play class
    private AudioRecorder audioRecorder;
    private AudioPlayer audioPlayer;

    MyAsynTask myAsynTaskPlay, myAsynTaskRecord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_song_player);

        initView();


    }

    private void initView() {

        ibStop = (ImageButton) findViewById(R.id.ibStop);
        ibPlay = (ImageButton) findViewById(R.id.ibPlay);
        ibMic = (ImageButton) findViewById(R.id.ibMic);
        ibMicUsing = (ImageButton) findViewById(R.id.ibMicUsing);
        ibPlayRecord = (ImageButton) findViewById(R.id.ibPlayRecord);

        tvDisplayLine = (TextView) findViewById(R.id.tvDisplayLine);
        tvDispayWhole = (TextView) findViewById(R.id.tvDispayWhole);
        tvDisplaySongName = (TextView) findViewById(R.id.tvDisplaySongName);


        ibPlay.setOnClickListener(this);
        ibStop.setOnClickListener(this);
        ibMic.setOnClickListener(this);
        ibMicUsing.setOnClickListener(this);
        ibPlayRecord.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.ibPlay:

                playMusic();
                ibPlay.setImageResource(R.drawable.player_playing);

                //播放歌曲同时同步显示歌词
                myAsynTaskPlay = new MyAsynTask();
                myAsynTaskPlay.execute();

                break;

            case R.id.ibStop:

                if (mediaPlayer != null) {
                    mediaPlayer.stop();
                    mediaPlayer.release();
                    mediaPlayer = null;

                }

                if (audioRecorder != null) {
                    audioRecorder.stopRecord();

                }

                if (audioPlayer != null) {
                    audioPlayer.stop();
                }

                ibPlay.setImageResource(R.drawable.player_play);
                ibMic.setVisibility(View.VISIBLE);
                ibMicUsing.setVisibility(View.GONE);
                ibPlayRecord.setImageResource(R.drawable.player_playrecord);

                if (myAsynTaskPlay != null) {
                    myAsynTaskPlay.cancel(true);
                }
                if (myAsynTaskRecord != null) {
                    myAsynTaskRecord.cancel(true);
                }

                break;

            case R.id.ibMic:

                try {
                    recordMe();
                } catch (IOException e) {

                    Toast.makeText(this, "Failed to record", Toast.LENGTH_SHORT).show();
                    e.printStackTrace();

                }
                ibMic.setVisibility(View.GONE);
                ibMicUsing.setVisibility(View.VISIBLE);

                myAsynTaskRecord = new MyAsynTask();
                myAsynTaskRecord.execute();

                break;

            case R.id.ibMicUsing:

                if (audioRecorder != null) {
                    audioRecorder.stopRecord();
                    mediaPlayer.stop();
                }

                ibMicUsing.setVisibility(View.GONE);
                ibMic.setVisibility(View.VISIBLE);

                myAsynTaskRecord.cancel(true);

                break;

            case R.id.ibPlayRecord:

                if (audioRecorder != null) {
                    audioRecorder.stopRecord();
                    playRecord();

                }
                ibPlayRecord.setImageResource(R.drawable.play_recording);

            default:
        }
    }


    /*
    * 录音
    * */
    private void recordMe() throws IOException {

        if (audioRecorder == null) {
            audioRecorder = new AudioRecorder();

        }
        audioRecorder.startRecord();
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mediaPlayer = MediaPlayer.create(this, R.raw.single_music);
        mediaPlayer.start();
    }

    /*
    * 播放录音
    * */
    private void playRecord() {

        if (audioPlayer == null) {
            audioPlayer = new AudioPlayer();
        }

        audioPlayer.setPlayerPath(audioRecorder.getFileName());

        audioPlayer.play();
    }

    //播放app内置音乐
    private void playMusic() {

        if (mediaPlayer == null) {
            mediaPlayer = MediaPlayer.create(this, R.raw.single_linzhixuan);
            mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {

                    mediaPlayer = null;

                    //music finished play , change the button image
                    ibPlay.setImageResource(R.drawable.player_play);

                }
            });
        }

        mediaPlayer.start();

    }

    class MyAsynTask extends AsyncTask<URL, Integer, Long> {
        private Lyric lyric;
        private ArrayList<Sentence> sentences;
        private Hashtable<String, String> tags;

        @Override
        protected Long doInBackground(URL... params) {
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getResources()
                            .openRawResource(R.raw.single_lyric)));

            try {
                lyric = LrcParser.create(reader);
                sentences = lyric.findAllSentences(-1, -1);
                tags = lyric.getTags();

                for (int j = 0; j < sentences.size(); j++) {
                    if (isCancelled()) break;
                    publishProgress(j);
                    if (j < sentences.size() - 1) {
                        Thread.sleep(sentences.get(j).getDuring());
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            //display the song's title and singer
            String tagString = "";
            if (tags.containsKey("ti")) {
                tagString += "歌名: " + tags.get("ti");
            }
            if (tags.containsKey("ar")) {
                tagString += "\n演唱: " + tags.get("ar");
            }

            tvDisplaySongName.setText(tagString);

            String lyricContent = "";
            for (int i = 0; i < sentences.size(); i++) {

                lyricContent += "\n" + (sentences.get(i).getContent());
            }

            tvDispayWhole.setText(lyricContent);
            tvDispayWhole.setMovementMethod(ScrollingMovementMethod.getInstance());

            //display the current singing lyric line
            tvDisplayLine.setText(sentences.get(values[0]).getContent());
        }


        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);

          /*  //display the whole song lyrics
            String lyricContent = "";

            for (int i = 0; i < sentences.size(); i++) {

                lyricContent += "\n" + (sentences.get(i).getContent());

//                Log.d(TAG, "onPostExecute----->\n" + sentences.get(i).getContent());
//                System.out.println(sentences.get(i).getContent());
            }

            tvDispayWhole.setText(lyricContent);
            tvDispayWhole.setMovementMethod(ScrollingMovementMethod.getInstance());*/
        }
    }
}