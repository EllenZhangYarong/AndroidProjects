package com.ellen.tasksevenimageviewer;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by ellen on 15/11/26.
 */
public class FileFromAssets {

    public static Bitmap getBitmapFromAssets(Context context, String filename) {
        Bitmap image = null;

        AssetManager assetManager = context.getResources().getAssets();
        try {
            InputStream is = assetManager.open(filename);
            image = BitmapFactory.decodeStream(is);
            is.close();
        } catch (IOException e) {
            System.out.println("Fail to open file ---- " + filename);
        }
        return image;
    }

    private static void copyAssetFile(InputStream in, OutputStream out) {
        try {
            byte[] buffer = new byte[1024];
            int read;

            while ((read = in.read(buffer)) != -1) {
                out.write(buffer, 0, read);

            }
            in.close();
            in = null;
            out.flush();
            out.close();
            out = null;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void copyToSDCard(Context context) {
        try {
            AssetManager assetManager = context.getAssets();
            String[] files = assetManager.list("");
            InputStream inputStream = null;
            OutputStream outputStream = null;
            for (int i = 0; i < files.length; i++) {
                inputStream = assetManager.open(files[i]);
                outputStream = new FileOutputStream((Environment.getExternalStorageDirectory() + "/") + files[i]);
                copyAssetFile(inputStream, outputStream);
            }
        } catch (FileNotFoundException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
