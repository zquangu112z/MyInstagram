package com.example.ngu.myinstagram.model;

/**
 * Created by Ngu on 7/7/2015.
 */
public class DataPicture {
    public static DataPicture x = new DataPicture();
    private byte[] data;

    public byte[] getData() {
        return x.data;
    }

    public void setData(byte[] data) {
        x.data = new byte[data.length];
        for (int i = 0; i < data.length; i++) {
            x.data[i] = data[i];
        }
    }

    public static DataPicture x_thumbnails = new DataPicture();
    private byte[] data_thumbnails;

    public byte[] getData_thumbnails() {
        return x_thumbnails.data_thumbnails;
    }

    public void setData_thumbnails(byte[] data) {
        x_thumbnails.data_thumbnails = new byte[data_thumbnails.length];
        for (int i = 0; i < data_thumbnails.length; i++) {
            x_thumbnails.data_thumbnails[i] = data[i];
        }
    }
}
