package com.example.bookie;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.example.bookie.models.Volume;
import com.example.bookie.models.VolumeInfo;
import com.example.bookie.network.ApiClient;
import com.example.bookie.network.BooksService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolumeDescriptionActivity extends AppCompatActivity {
    private AppCompatTextView mTvTitle;
    private AppCompatTextView mTvSubTitle;
    private AppCompatTextView mTvAuthor;
    private AppCompatImageView mIvThumbnail;
    private AppCompatTextView mTvDescription;
    public static final String EXTRA_VOLUME_ID = "VolumeId"; //made Intent key constant from MainActivity.

    private LinearLayout mLvDescription;
    private VolumeInfo mVolumeInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_desc);
        initView();

        if(getIntent().getSerializableExtra(EXTRA_VOLUME_ID) != null) {
            mVolumeInfo = (VolumeInfo) getIntent().getSerializableExtra(EXTRA_VOLUME_ID);
            displayDesc(mVolumeInfo);
        }

    }

    private void initView() {
        mIvThumbnail = findViewById(R.id.iv_item_book_thumbnail);
        mTvTitle = findViewById(R.id.tv_item_book_title1);
        mTvAuthor = findViewById(R.id.tv_item_book_author);
        mTvSubTitle = findViewById(R.id.tv_item_book_subtitle);
        mTvDescription = findViewById(R.id.tv_item_book_desc);
        mLvDescription = findViewById(R.id.description_view);
    }


    public void displayDesc(VolumeInfo info) {
        if (info!=null) {
            if (info.getTitle() != null) {
                mTvTitle.setText(info.getTitle());
            }
            if (info.getSubtitle() != null) {
                mTvSubTitle.setText(info.getSubtitle());
            } else
                mTvSubTitle.setVisibility(View.GONE);

            if (info.getDescription() != null) {
                mTvDescription.setText(info.getDescription());
            }
            if (info.getImageLinks() != null) {
                Glide.with(this).load(info.getImageLinks().getSmallThumbnail()).into(mIvThumbnail);
            }
            if (info.getAuthors() != null) {
                mTvAuthor.setText(info.getAuthors().get(0));
            }
        }
    }

}