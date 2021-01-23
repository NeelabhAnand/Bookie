package com.example.bookie;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class FilterRVAdapter extends RecyclerView.Adapter<FilterRVAdapter.FilterVH> {

    private final ArrayList<String> filters;
    private OnItemClickListener itemClickListener;

    public FilterRVAdapter(ArrayList<String> filters) {
        this.filters = filters;
    }

    @NonNull
    @Override
    public FilterRVAdapter.FilterVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FilterVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_filter, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FilterRVAdapter.FilterVH holder, int position) {

        holder.tvFilters.setText(filters.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                if (itemClickListener != null){
                    itemClickListener.OnItemClick(holder.getAdapterPosition());
                }
            }
        });

    }
    public void setOnItemClickListener (OnItemClickListener OnItemListener){
        this.itemClickListener = OnItemListener;
    }


    @Override
    public int getItemCount() {
        return filters.size();
    }


    static class FilterVH extends RecyclerView.ViewHolder {
        private final AppCompatTextView tvFilters;
        public FilterVH(@NonNull View itemView) {
            super(itemView);
            tvFilters = itemView.findViewById(R.id.tv_filter_name);
        }
    }
}