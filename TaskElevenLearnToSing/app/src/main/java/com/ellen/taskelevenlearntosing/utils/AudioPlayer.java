package com.ellen.taskelevenlearntosing.utils;

import android.media.MediaPlayer;

import java.io.IOException;

/**
 * Created by ellen on 15/12/30.
 */
public class AudioPlayer implements MediaPlayer.OnCompletionListener {

    private MediaPlayer mediaPlayer;

    private String playerPath;

    public void setPlayerPath(String playerPath) {
        this.playerPath = playerPath;
    }

    public AudioPlayer() {
    }

    public void play() {

        if (mediaPlayer == null) {

            mediaPlayer = new MediaPlayer();

        }

        try {

            mediaPlayer.setDataSource(playerPath);
            mediaPlayer.prepare();
            mediaPlayer.start();

            mediaPlayer.setOnCompletionListener(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onCompletion(MediaPlayer mp) {
        mediaPlayer = null;
    }

    public void stop() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            mediaPlayer = null;
        }
    }

}
