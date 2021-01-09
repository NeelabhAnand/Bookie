package com.example.bookie;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookie.models.Volume;
import com.example.bookie.models.VolumeInfo;

import java.util.ArrayList;

/**
 * Created by Neelabh Anand on 08/01/21.
 */
public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookVH> {
    private final ArrayList<Volume> volumes;

    public BooksAdapter(ArrayList<Volume> volumes) {
        this.volumes = volumes;
    }

    @NonNull
    @Override
    public BookVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BookVH(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_book, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull BookVH holder, int position) {
        VolumeInfo info = volumes.get(position).getVolumeInfo();
        if (info != null) {
            if (info.getTitle() != null) {
                holder.tvTitle.setText(info.getTitle());
            }
            if (info.getAuthors() != null && !info.getAuthors().isEmpty()) {
                holder.tvAuthorName.setText(info.getAuthors().get(0));
            }
            if (info.getPublishedDate() != null) {
                holder.tvDate.setText(info.getPublishedDate());
            }
        }
    }

    @Override
    public int getItemCount() {
        return volumes.size();
    }

    static class BookVH extends RecyclerView.ViewHolder {
        private final AppCompatTextView tvTitle;
        private final AppCompatTextView tvAuthorName;
        private final AppCompatTextView tvDate;

        public BookVH(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_book_title);
            tvAuthorName = itemView.findViewById(R.id.tv_item_book_author_name);
            tvDate = itemView.findViewById(R.id.tv_item_book_date);
        }
    }
}