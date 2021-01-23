package com.example.bookie;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.IntDef;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookie.models.BookResponse;
import com.example.bookie.models.Volume;
import com.example.bookie.models.VolumeInfo;
import com.example.bookie.network.ApiClient;
import com.example.bookie.network.BooksService;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_RESULTS = 10;
    private static final String RELEVANCE = "relevance";
    private static String selectedFilter = "ebooks";
    private AppCompatEditText mEtBookName;
    private AppCompatImageButton mBtnSearch;
    private AppCompatImageButton mBtnClear;
    private AppCompatImageButton mBtnFilter;
    private AppCompatTextView mTvEmpty;
    private ProgressBar mProgressBar;
    private RecyclerView mRvBooks;
    private BooksAdapter mAdapter;
    public static final int REQUEST_CODE_FILTER = 1124;
    private ArrayList<Volume> mVolumes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initAdapter();
        setOnClickListeners();

       }

    private void initView(){
        mEtBookName = findViewById(R.id.et_book_name);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnClear = findViewById(R.id.btn_clear);
        mBtnFilter = findViewById(R.id.btn_filter);
        mProgressBar = findViewById(R.id.progressBar);
        mTvEmpty = findViewById(R.id.tv_activity_main_empty);
        mRvBooks = findViewById(R.id.rv_books);
    }

    private void initAdapter(){
        mRvBooks.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BooksAdapter(this, mVolumes);
        mRvBooks.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent i = new Intent(MainActivity.this, VolumeDescriptionActivity.class);
                i.putExtra(VolumeDescriptionActivity.EXTRA_VOLUME_ID, mVolumes.get(position).getVolumeInfo());
                startActivity(i);
            }
        });
    }

    private void setOnClickListeners(){
        mBtnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchBooks();
            }
        });

        mBtnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                clearBooks();
            }
        });
        mBtnFilter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectFilter();
            }
        });
    }

    private void searchBooks() {
        showProgress();
        BooksService service = ApiClient.getInstance().createService(BooksService.class);
        service.getBooks(mEtBookName.getText().toString(), MAX_RESULTS, RELEVANCE, selectedFilter).enqueue(new Callback<BookResponse>() {
            @Override
            public void onResponse(@NotNull Call<BookResponse> call, @NotNull Response<BookResponse> response) {
                hideProgress();
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        if (response.body().getVolumes() != null) {
                            mVolumes.clear();
                            mVolumes.addAll(response.body().getVolumes());
                            mAdapter.notifyDataSetChanged();
                        }
                    }
                } else {
                   Utils.showToast("Something went wrong. Please try again later.", MainActivity.this);
                }
            }

            @Override
            public void onFailure(@NotNull Call<BookResponse> call, @NotNull Throwable t) {
                hideProgress();
                Utils.showToast(t.getMessage() != null ? t.getMessage() : "Please check your network connection", MainActivity.this);
            }
        });

    }


    private void clearBooks() {
        mEtBookName.setText("");
        mTvEmpty.setVisibility(View.VISIBLE);
        mVolumes.clear();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {

            super.onActivityResult(requestCode, resultCode, data);

            if (requestCode == REQUEST_CODE_FILTER  && resultCode  == RESULT_OK) {

                selectedFilter = data.getStringExtra("filterClicked");


            }
    }

    private void selectFilter(){
        Intent i = new Intent(MainActivity.this, FilterActivity.class);
        startActivityForResult(i,REQUEST_CODE_FILTER);
    }

    private void showProgress() {
        mTvEmpty.setVisibility(View.GONE);
        mRvBooks.setVisibility(View.GONE);
        mProgressBar.setVisibility(View.VISIBLE);
    }

    private void hideProgress() {
        mProgressBar.setVisibility(View.GONE);
        mRvBooks.setVisibility(View.VISIBLE);

    }


    }

