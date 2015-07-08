package com.example.ngu.myinstagram.adapter;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ngu.myinstagram.R;
import com.example.ngu.myinstagram.model.Filter;

import java.util.List;

/**
 * Created by Ngu on 7/8/2015.
 */

public class ChooseFilterRVAdapter extends RecyclerView.Adapter<ChooseFilterRVAdapter.FilterViewHolder> {
    List<Filter> filters;

    public ChooseFilterRVAdapter(List<Filter> filters) {
        this.filters = filters;
    }

    @Override
    public FilterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_of_recycler_view_choose_filter, parent, false);//cardview_edit_filter
        FilterViewHolder pvh = new FilterViewHolder(v);
        return pvh;
    }

    @Override
    public void onBindViewHolder(FilterViewHolder filterViewHolder, int i) {
        filterViewHolder.filterName.setText(filters.get(i).nameFilter);
        filterViewHolder.filterPhoto.setImageResource(filters.get(i).photoFilterId);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return filters.size();
    }

    public static class FilterViewHolder extends RecyclerView.ViewHolder {

        CardView cv;
        ImageView filterPhoto;
        TextView filterName;

        FilterViewHolder(View itemView) {
            super(itemView);
            filterPhoto = (ImageView) itemView.findViewById(R.id.filter_photo);
            filterName = (TextView) itemView.findViewById(R.id.filter_name);
        }
    }

}

