package com.example.ngu.myinstagram.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Ngu on 7/9/2015.
 */
public class SaveThumbnailsTask extends AsyncTask<byte[], Void, Void> {
    @Override
    protected Void doInBackground(byte[]... params) {
        File pictureThumbnailsFile = getOutputMediaFile();
        byte[] data = params[0];

        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bitmap= BitmapFactory.decodeByteArray(data,0,data.length);
        //bitmap.compress(Bitmap.CompressFormat.JPEG,20,stream);


        Matrix matrix = new Matrix();
        matrix.setRotate(90, (float) bitmap.getWidth() / 2, (float) bitmap.getHeight() / 2);
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getHeight(), bitmap.getHeight(), matrix, true);
        bitmap.compress(Bitmap.CompressFormat.JPEG,20,stream);

        byte[] byteAfterResize = stream.toByteArray();



        Log.e("------", "vao picture");

        if (pictureThumbnailsFile == null) {
            Log.e("------", "creating pictureThumbnailsFile null");
        }
        try {
            FileOutputStream fos = new FileOutputStream(pictureThumbnailsFile);
            fos.write(byteAfterResize);
            fos.flush();
            fos.close();
            Log.e("------", "creating FOS Okay");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    private static File getOutputMediaFile() {
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp/.Thumbnails");
        if (!mediaStorageDir.exists()) {
            if (!mediaStorageDir.mkdirs()) {
                Log.d("MyCameraApp", "failed to create directory");
                return null;
            }
        }

        // Create a media file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File mediaFile;
        String last = timeStamp.substring(timeStamp.length() - 1, timeStamp.length());
        mediaFile = new File(mediaStorageDir.getPath() + File.separator +
                "IMG_" + timeStamp.substring(0, timeStamp.length() - 1).concat(last) + ".jpg");
        return mediaFile;
    }
}
