package com.example.ngu.myinstagram.activity;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.adapter.ChooseFilterRVAdapter;
import com.example.ngu.myinstagram.model.DataPicture;
import com.example.ngu.myinstagram.model.Filter;

import java.io.ByteArrayOutputStream;
import java.nio.BufferUnderflowException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class EditPictureActivity extends ActionBarActivity {
    ImageView iv_result;
    byte[] data;
    static Bitmap bitmap;
    RecyclerView rv_filter;
    private List<Filter> filters;
    RelativeLayout rl_filter;
    ImageButton bt_close_edit;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        filters = new ArrayList<>();
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
        filters.add(new Filter(R.drawable.filter_test, "name"));
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_picture);
        iv_result = (ImageView) findViewById(R.id.iv_result);
        rv_filter = (RecyclerView) findViewById(R.id.rv_filter);
        rl_filter = (RelativeLayout) findViewById(R.id.rl_filter);
        bt_close_edit = (ImageButton) findViewById(R.id.bt_close_edit);
        //Tinh chieu cao cua rv_filter
        DisplayMetrics displaymetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displaymetrics);
        int width = displaymetrics.widthPixels;
        Log.e("------", "width:" + width);
        //chuyen qua set do cao cua cai imageView cho nhanh hon
        ViewGroup.LayoutParams params2 = iv_result.getLayoutParams();
        params2.height = width;


        //get bitmap
        data = DataPicture.x.getData();//root
        Log.e("------", "size: " + data.length);
        Log.e("------", "size: " + data[0] + data[1] + data[2] + data[3] + data[4] + data[5] + data[6] + data[7] + data[8] + data[9] + data[10] + data[11]);
        bitmap = BitmapFactory.decodeByteArray(data, 0, data.length);

        bitmap = minus127(bitmap);


        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                Bitmap bitmapFirst = editFirstPictureBitmap(bitmap);
                iv_result.setImageBitmap(bitmapFirst);
            }
        });
        thread.start();


        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_filter.setLayoutManager(layoutManager);
        initializeData();
        ChooseFilterRVAdapter chooseFilterRVAdapter = new ChooseFilterRVAdapter(filters);
        rv_filter.setAdapter(chooseFilterRVAdapter);

        bt_close_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    //xoay bitmap
    private static Bitmap rotationPictureBitmap(Bitmap bitmapx) {
        int rotationAngle = 90;
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmapx.getWidth() / 2, (float) bitmapx.getHeight() / 2);
        Bitmap x = Bitmap.createBitmap(bitmapx, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        return x;
    }

    //xu li khoi tao Bitmap
    private static Bitmap editFirstPictureBitmap(Bitmap bitmapx) {
        int rotationAngle = 90;
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmapx.getWidth() / 2, (float) bitmapx.getHeight() / 2);
        Bitmap x = Bitmap.createBitmap(bitmapx, 0, 0, bitmap.getHeight(), bitmap.getHeight(), matrix, true);
        //bitmap.getWidth()=>Height do cieu cao va chieu rong cua no bi nham trai nguoc nhau
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

    public Bitmap minus127(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte[] bytes = byteBuffer.array();
        Log.e("------", "length" + bytes.length);

        String test_byte = "start";
        for (int i = 0; i < 100; i++) {
            test_byte = test_byte + "*" + (int) (bytes[i]);
        }
        byteBuffer.rewind();
        for (int i = 0; i < bytes.length; i++) {

            bytes[i] = (byte) (bytes[i] - 127);

        }
        try {
            byteBuffer.get(bytes);
            byteBuffer.rewind();
        } catch (BufferUnderflowException e) {
            Log.e("------", "wtf");
        }
        Log.e("------", "test_byte".concat(test_byte));
        bitmap.copyPixelsFromBuffer(byteBuffer);
        iv_result.setImageBitmap(bitmap);
        return bitmap;
    }
    public Bitmap minus100(Bitmap bitmap) {
        int size = bitmap.getRowBytes() * bitmap.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bitmap.copyPixelsToBuffer(byteBuffer);
        byte[] bytes = byteBuffer.array();
        Log.e("------", "length" + bytes.length);

        String test_byte = "start";
        for (int i = 0; i < 100; i++) {
            test_byte = test_byte + "*" + (int) (bytes[i]);
        }
        byteBuffer.rewind();
        for (int i = 0; i < bytes.length; i++) {

            bytes[i] = (byte) (bytes[i] - 100);

        }
        try {
            byteBuffer.get(bytes);
            byteBuffer.rewind();
        } catch (BufferUnderflowException e) {
            Log.e("------", "wtf");
        }
        Log.e("------", "test_byte".concat(test_byte));
        bitmap.copyPixelsFromBuffer(byteBuffer);
        iv_result.setImageBitmap(bitmap);
        return bitmap;
    }
}
