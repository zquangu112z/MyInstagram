package com.example.ngu.myinstagram.activity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.model.DataPicture;

public class EditPictureActivity extends ActionBarActivity {
    ImageView iv_test;
    byte[] data;
    static Bitmap bitmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_picture);
        //Log.e("------", "okkkkkkkkkkkkkkkkkkkk" + DataPicture.x.getData()[2] + "");//du lieu o trong x
        iv_test = (ImageView) findViewById(R.id.iv_test);

        //get bitmap
        data = DataPicture.x.getData();
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmap1 = rotationPictureBitmap(bitmap);
                iv_test.setImageBitmap(bitmap1);
            }
        });
        thread.start();
    }

    private static Bitmap rotationPictureBitmap(Bitmap bitmapx) {
//        BitmapFactory.Options bounds = new BitmapFactory.Options();
//        bounds.inJustDecodeBounds = true;
        int rotationAngle = 90;
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmapx.getWidth() / 2, (float) bitmapx.getHeight() / 2);
        Bitmap x = Bitmap.createBitmap(bitmapx, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return x;
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_edit_picture, menu);
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
}
