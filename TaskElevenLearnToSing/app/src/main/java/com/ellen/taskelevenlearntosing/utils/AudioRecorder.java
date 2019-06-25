package com.ellen.taskelevenlearntosing.utils;

import android.media.MediaRecorder;
import android.os.Environment;

import java.io.File;
import java.io.IOException;

/**
 * Created by ellen on 15/12/30.
 */
public class AudioRecorder {

    private String dir;

    public String getFileName() {
        return fileName;
    }

    private String fileName;
    private MediaRecorder mediaRecorder;


    public AudioRecorder() {

        dir = Environment.getExternalStorageDirectory().getAbsolutePath() + "/myrecorder/";
    }

    public void startRecord() throws IOException {

        //whether the sd card exists or has enough storage space, if not, throw IOexception
        String state = Environment.getExternalStorageState();
        if (!state.equals(Environment.MEDIA_MOUNTED)) {
            throw new IOException("Not found SD card");
        }


        //whether the diretory exists, if not, mkdir
        File myDir = new File(dir);
        if (!myDir.exists()) {

            myDir.mkdir();

        }

        //auto name the record file using time stamp
        fileName = dir + System.currentTimeMillis() + ".amr";

        mediaRecorder = new MediaRecorder();

        //use system mic to record
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);

        //Set the output format
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);

        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        mediaRecorder.setAudioSamplingRate(8000);

        mediaRecorder.setOutputFile(fileName);

        mediaRecorder.prepare();
        mediaRecorder.start();
    }

    public void stopRecord() {

        if (mediaRecorder != null) {

            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;

        }
    }
}
