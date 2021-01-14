package com.example.bookie;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import com.bumptech.glide.Glide;
import com.example.bookie.models.BookResponse;
import com.example.bookie.models.Volume;
import com.example.bookie.models.VolumeInfo;
import com.example.bookie.network.ApiClient;
import com.example.bookie.network.BooksService;

import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VolumeDescriptionActivity extends AppCompatActivity {

    private AppCompatTextView d_title;
    private AppCompatTextView d_subtitle;
    private AppCompatTextView d_author;
    private AppCompatImageView d_image;
    private AppCompatTextView d_desc;
    public static final String EXTRA_VOLUME_ID= "VolumeId"; //made Intent key constant from MainActivity.
    private ProgressBar mProgressBar;
    private LinearLayout mdescriptionView;
    private AppCompatTextView mTvEmpty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_volume_desc);

        d_image = findViewById(R.id.iv_item_book_thumbnail);
        d_title = findViewById(R.id.tv_item_book_title1);
        d_author = findViewById(R.id.tv_item_book_author);
        d_subtitle = findViewById(R.id.tv_item_book_subtitle);
        d_desc = findViewById(R.id.tv_item_book_desc);
        mdescriptionView = findViewById(R.id.description_view);
        mProgressBar = findViewById(R.id.progressBar);
        mTvEmpty = findViewById(R.id.tv_activity_desc_empty);

        showProgressBar();

        String idFromIntent = getIntent().getStringExtra(EXTRA_VOLUME_ID);

        ApiClient client = ApiClient.getInstance();
        BooksService service = client.createService(BooksService.class);

        service.getVolumeDetails(idFromIntent).enqueue(new Callback<Volume>() {

            @Override
            public void onResponse(@NotNull Call<Volume> call, Response<Volume> response) {
                hideProgressBar();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                            displayDesc(response.body().getVolumeInfo());
                        }
                } else {
                    showToast("Something went wrong");
                }
            }

            @Override
            public void onFailure(@NotNull Call<Volume> call, Throwable t) {
                showToast(t.getMessage() != null ? t.getMessage() : "Please check your network connection");
            }
        });
    }

    public void displayDesc(VolumeInfo info) {

            if (info.getTitle() != null) {
                d_title.setText(info.getTitle());
            }
            if (info.getSubtitle() != null) {
                d_subtitle.setText(info.getSubtitle());
            }else d_subtitle.setVisibility(View.GONE);
            if (info.getDescription() != null) {
                d_desc.setText(info.getDescription());
            }
            if (info.getImageLinks() != null) {
                Glide.with(this).load(info.getImageLinks().getSmall()).into(d_image);
            }
            if (info.getAuthors() != null) {
                d_author.setText(info.getAuthors().get(0));
            }
        }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void showProgressBar() {
        mTvEmpty.setVisibility(View.GONE);
        mdescriptionView.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
        mdescriptionView.setVisibility(View.VISIBLE);
    }

}
