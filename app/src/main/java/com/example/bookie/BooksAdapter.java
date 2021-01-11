package com.example.bookie;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.bookie.models.Volume;
import com.example.bookie.models.VolumeInfo;

import java.util.ArrayList;

import static android.content.ContentValues.TAG;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.BookVH> {
    private final ArrayList<Volume> volumes;
    private Context context;


    public BooksAdapter(Context context, ArrayList<Volume> volumes) {
        this.volumes = volumes;
        this.context = context;
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

            if (info.getImageLinks() != null) {
                Glide.with(context).load(info.getImageLinks().getSmallThumbnail())
                        .into(holder.tvImage);
            }
            Log.d(TAG, "onBindViewHolder() returned: " + "No data found");

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
        private final AppCompatImageView tvImage;

        public BookVH(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.tv_item_book_title);
            tvAuthorName = itemView.findViewById(R.id.tv_item_book_author_name);
            tvDate = itemView.findViewById(R.id.tv_item_book_date);
            tvImage = itemView.findViewById(R.id.iv_item_book_image);
        }
    }
}