package com.example.ngu.myinstagram.model;

/**
 * Created by Ngu on 7/8/2015.
 */
public class Filter {
    public String nameFilter;
    public int photoFilterId;

    public Filter(int photoFilterId,String nameFilter) {
        this.nameFilter = nameFilter;
        this.photoFilterId = photoFilterId;
    }
}