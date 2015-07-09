package com.example.ngu.myinstagram.activity;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
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
import com.example.ngu.myinstagram.helper.listener.RecyclerItemClickListener;
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
    static Bitmap bitmapRoot;
    Bitmap bitmapUsedForEdit;
    RecyclerView rv_filter;
    private List<Filter> filters;
    RelativeLayout rl_filter;
    ImageButton bt_close_edit;

    // This method creates an ArrayList that has three Person objects
// Checkout the project associated with this tutorial on Github if
// you want to use the same images.
    private void initializeData() {
        filters = new ArrayList<>();
        //neu them them thi phai can than cho addItemOnTouchListener!
        filters.add(new Filter(R.drawable.ic_edit_filter_i, "nicholas"));
        filters.add(new Filter(R.drawable.filter_test, "ngu"));
        filters.add(new Filter(R.drawable.ic_edit_filter_i, "summer"));
        filters.add(new Filter(R.drawable.filter_test, "spring"));
        filters.add(new Filter(R.drawable.filter_test, "fall"));
        filters.add(new Filter(R.drawable.filter_test, "winter"));
        filters.add(new Filter(R.drawable.filter_test, "motor"));
        filters.add(new Filter(R.drawable.filter_test, "car"));
        filters.add(new Filter(R.drawable.ic_edit_setting, "setting"));
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
        bitmapRoot = BitmapFactory.decodeByteArray(data, 0, data.length);


        Thread threadFirst = new Thread(new Runnable() {
            @Override
            public void run() {
                //Bitmap bitmapFirst = editFirstPictureBitmap(bitmap);
                // iv_result.setImageBitmap(bitmapFirst);

                bitmapRoot = editFirstPictureBitmap(bitmapRoot);
                iv_result.setImageBitmap(bitmapRoot);
            }
        });

        threadFirst.start();
        //bitmapUsedForEdit=bitmapRoot;

        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        rv_filter.setLayoutManager(layoutManager);
        initializeData();
        ChooseFilterRVAdapter chooseFilterRVAdapter = new ChooseFilterRVAdapter(filters);
        rv_filter.setAdapter(chooseFilterRVAdapter);

        bt_close_edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),CameraActivity.class));
            }
        });

        rv_filter.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), new RecyclerItemClickListener.OnItemClickListener() {
                    @Override
                    public void onItemClick(View view, int position) {
                        switch (position) {
                            case 0:
                                Log.e("------", " touch 0 ");
                                bitmapUsedForEdit = minus127(bitmapRoot);
                                iv_result.setImageBitmap(bitmapUsedForEdit);
                                break;
                            case 1:
                                Log.e("------", " touch 1 ");
                                bitmapUsedForEdit = minus100(bitmapRoot);
                                iv_result.setImageBitmap(bitmapUsedForEdit);
                                break;

                            case 2:
                                Log.e("------", " touch 2 ");
                                bitmapUsedForEdit=toGrayscale(bitmapRoot);
                                iv_result.setImageBitmap(bitmapUsedForEdit);

                                break;

                            case 3:
                                Log.e("------", " touch 3 ");
                                break;

                            case 4:
                                Log.e("------", " touch 4 ");
                                break;

                            case 5:
                                Log.e("------", " touch 5 ");
                                break;

                            case 6:
                                Log.e("------", " touch 6 ");
                                break;

                            case 7:
                                Log.e("------", " touch 7 ");
                                break;

                            case 8:
                                Log.e("------", " touch 8 ");
                                break;

                        }
                    }
                })
        );


    }

    //xoay bitmap
    private static Bitmap rotationPictureBitmap(Bitmap bitmapx) {
        int rotationAngle = 90;
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmapx.getWidth() / 2, (float) bitmapx.getHeight() / 2);
        Bitmap x = Bitmap.createBitmap(bitmapx, 0, 0, bitmapRoot.getWidth(), bitmapRoot.getHeight(), matrix, true);
        return x;
    }

    //xu li khoi tao Bitmap
    private static Bitmap editFirstPictureBitmap(Bitmap bitmapx) {
        int rotationAngle = 90;
        Matrix matrix = new Matrix();
        matrix.setRotate(rotationAngle, (float) bitmapx.getWidth() / 2, (float) bitmapx.getHeight() / 2);
        Bitmap x = Bitmap.createBitmap(bitmapx, 0, 0, bitmapRoot.getHeight(), bitmapRoot.getHeight(), matrix, true);
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
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public Bitmap minus127(Bitmap bitmap127) {


        int  height;
        height = bitmap127.getHeight();
        Bitmap bmpMinus127 = Bitmap.createBitmap(bitmap127,0,0,height,height);//gan qua dau bang la sai!


        int size = bmpMinus127.getRowBytes() * bmpMinus127.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bmpMinus127.copyPixelsToBuffer(byteBuffer);
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
        bmpMinus127.copyPixelsFromBuffer(byteBuffer);
        return bmpMinus127;
    }

    public Bitmap minus100(Bitmap bitmap100) {
        int  height;
        height = bitmap100.getHeight();
        Bitmap bmpMinus100 = Bitmap.createBitmap(bitmap100,0,0,height,height);//gan qua dau bang la sai!


        //Bitmap bitmapxxx = bitmap100;
        int size = bmpMinus100.getRowBytes() * bmpMinus100.getHeight();
        ByteBuffer byteBuffer = ByteBuffer.allocate(size);
        bmpMinus100.copyPixelsToBuffer(byteBuffer);
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
        bmpMinus100.copyPixelsFromBuffer(byteBuffer);
        return bmpMinus100;
    }

    public Bitmap toGrayscale(Bitmap bmpOriginal)
    {
        int width, height;
        height = bmpOriginal.getHeight();
        width = bmpOriginal.getWidth();

        Bitmap bmpGrayscale = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(bmpGrayscale);
        Paint paint = new Paint();
        ColorMatrix cm = new ColorMatrix();
        cm.setSaturation(0);
        ColorMatrixColorFilter f = new ColorMatrixColorFilter(cm);
        paint.setColorFilter(f);
        c.drawBitmap(bmpOriginal, 0, 0, paint);
        return bmpGrayscale;
    }
}
