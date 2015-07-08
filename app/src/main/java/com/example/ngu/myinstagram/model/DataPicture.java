package com.example.ngu.myinstagram.model;

/**
 * Created by Ngu on 7/7/2015.
 */
public class DataPicture {
    public static DataPicture x=new DataPicture();
    private byte[] data;

    public byte[] getData() {
        return x.data;
    }

    public void setData(byte[] data) {
        x.data=new byte[data.length];
        for (int i = 0; i <data.length ; i++) {
            x.data[i]=data[i];
        }
    }
}
