package com.example.ngu.myinstagram.helper;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Ngu on 7/2/2015.
 */
public class RotatePictureTask extends AsyncTask<File, Void, Void> {

    public static final int MEDIA_TYPE_IMAGE = 1;

    @Override
    protected Void doInBackground(File... params) {
        File file = params[0];

        //fileName
        String name = file.getPath();
        //fileName

        /*lay fileName cua anh*/
        String parts[]=name.split("/");
        int end=parts.length;
        String fileName=parts[end-1];
        Log.e("------",fileName);
        /*lay fileName cua anh*/

        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "MyCameraApp");
        //Bitmap pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
        String filename = "pippo.png";
        //File sd = Environment.getExternalStorageDirectory();
        File dest = new File(mediaStorageDir, fileName);

        Bitmap bitmap = rotationPictureFile(file);
        try {
            FileOutputStream out = new FileOutputStream(dest);
            //bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 90, out);
            out.flush();
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("------", "can't convert!");

        }
        return null;

    }

    /**
     * Create a File for saving an image or video
     */
    private static Bitmap rotationPictureFile(File file) {

        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;
        //IMG_20150701_213944.jpg
        String x = file.getAbsolutePath();
        BitmapFactory.decodeFile(x, bounds);

        BitmapFactory.Options opts = new BitmapFactory.Options();
        Bitmap bm = BitmapFactory.decodeFile(x, opts);
        ExifInterface exif = null;
        try {
            exif = new ExifInterface(x);
        } catch (IOException e) {
            e.printStackTrace();
        }
        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
        int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

        int rotationAngle = 0;
        if (orientation == ExifInterface.ORIENTATION_NORMAL) {
            rotationAngle = 90;
            Log.e("------", "orientation==1");
        }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_90) {
            rotationAngle = 180;//last State: 90
            Log.e("------", "orientation==ORIENTATION_ROTATE_90");
        }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_180) {
            rotationAngle = 270;
            Log.e("------", "orientation==ORIENTATION_ROTATE_180");
        }
        if (orientation == ExifInterface.ORIENTATION_ROTATE_270) {
            rotationAngle = 0;
            Log.e("------", "orientation==ORIENTATION_ROTATE_270");
        }

        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
        //return mediaFile;
        return rotatedBitmap;
    }
}


//private static PictureCallback mPicture = new PictureCallback() {
//        @Override
//        public void onPictureTaken(byte[] data, Camera camera) {
//
//            Bitmap pictureFile = getOutputMediaFile(MEDIA_TYPE_IMAGE);
//
//            String filename = "pippo.png";
//            File sd = Environment.getExternalStorageDirectory();
//            File dest = new File(sd, filename);
//
//            Bitmap bitmap = pictureFile;
//            try {
//                FileOutputStream out = new FileOutputStream(dest);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 90, out);
//                out.flush();
//                out.close();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//    };

/**
 * Create a file Uri for saving an image or video
 * <p/>
 * Create a File for saving an image or video
 * <p/>
 * Create a File for saving an image or video
 * <p/>
 * Create a File for saving an image or video
 */
    /*private static Uri getOutputMediaFileUri(int type) {
        return Uri.fromFile(getOutputMediaFile(type));
    }*/

/**
 * Create a File for saving an image or video
 */
//    private static Bitmap getOutputMediaFile(int type) {
//        // To be safe, you should check that the SDCard is mounted
//        // using Environment.getExternalStorageState() before doing this.
//        Log.e("------", Environment.getExternalStorageState().toString());
//        Log.e("------", Environment.getRootDirectory().toString());
//        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES), "MyCameraApp");//MyCameraApp
//        Log.e("------", Environment.getExternalStoragePublicDirectory(
//                Environment.DIRECTORY_PICTURES).toString());
//        // This location works best if you want the created images to be shared
//        // between applications and persist after your app has been uninstalled.
//
//        // Create the storage directory if it does not exist
//        if (!mediaStorageDir.exists()) {
//            if (!mediaStorageDir.mkdirs()) {
//                Log.d("MyCameraApp", "failed to create directory");
//                return null;
//            }
//
//        }
//
//        // Create a media file name
//        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
//        File mediaFile;
//        if (type == MEDIA_TYPE_IMAGE) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "IMG_" + timeStamp + ".jpg");
//        } else if (type == MEDIA_TYPE_VIDEO) {
//            mediaFile = new File(mediaStorageDir.getPath() + File.separator +
//                    "VID_" + timeStamp + ".mp4");
//        } else {
//            return null;
//        }
//
////them vao test xoay anh
//        BitmapFactory.Options bounds = new BitmapFactory.Options();
//        bounds.inJustDecodeBounds = true;
//        //IMG_20150701_213944.jpg
//        String x=mediaStorageDir.getAbsolutePath()+"/IMG_20150701_213944.jpg";
//        /*
//        String x=mediaFile.getAbsolutePath();
//        Log.e("------",x.toString())
//        */
//        BitmapFactory.decodeFile(x, bounds);
//
//        BitmapFactory.Options opts = new BitmapFactory.Options();
//        Bitmap bm = BitmapFactory.decodeFile(x, opts);
//        ExifInterface exif = null;
//        try {
//            exif = new ExifInterface(x);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
//        int orientation = orientString != null ? Integer.parseInt(orientString) :  ExifInterface.ORIENTATION_NORMAL;
//
//        int rotationAngle = 0;
//        if (orientation == ExifInterface.ORIENTATION_NORMAL){
//            rotationAngle=90;
//            Log.e("------","orientation==1");
//        }
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_90){
//            rotationAngle = 90;
//            Log.e("------","orientation==ORIENTATION_ROTATE_90");
//        }
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_180){
//            rotationAngle = 180;
//            Log.e("------","orientation==ORIENTATION_ROTATE_180");
//        }
//        if (orientation == ExifInterface.ORIENTATION_ROTATE_270){
//            rotationAngle = 270;
//            Log.e("------","orientation==ORIENTATION_ROTATE_270");
//        }
//
//        Matrix matrix = new Matrix();
//        matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
//        Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);
////them vao test xoay anh
//        //return mediaFile;
//        return  rotatedBitmap;
//    }
