package com.example.bookie;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatImageButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookie.models.BookResponse;
import com.example.bookie.models.Volume;
import com.example.bookie.network.ApiClient;
import com.example.bookie.network.BooksService;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private static final int MAX_RESULTS = 10;
    private static final String relevance = "relevance";

    private AppCompatEditText mEtBookName;
    private AppCompatImageButton mBtnSearch;
    private AppCompatImageButton mBtnClear;
    private AppCompatTextView mTvEmpty;
    private ProgressBar mProgressBar;
    private RecyclerView mRvBooks;
    private BooksAdapter mAdapter;
    private ArrayList<Volume> mVolumes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mEtBookName = findViewById(R.id.et_book_name);
        mBtnSearch = findViewById(R.id.btn_search);
        mBtnClear = findViewById(R.id.btn_clear);
        mProgressBar = findViewById(R.id.progressBar);
        mTvEmpty = findViewById(R.id.tv_activity_main_empty);
        mRvBooks = findViewById(R.id.rv_books);
        mRvBooks.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new BooksAdapter(this, mVolumes);
        mRvBooks.setAdapter(mAdapter);

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
    }

    private void searchBooks() {
        showProgress();
        ApiClient client = ApiClient.getInstance();
        BooksService service = client.createService(BooksService.class);
        service.getBooks(mEtBookName.getText().toString(), MAX_RESULTS, relevance).enqueue(new Callback<BookResponse>() {

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
                    showToast("Something went wrong. Please try again later.");
                }
            }

            @Override
            public void onFailure(@NotNull Call<BookResponse> call, @NotNull Throwable t) {
                hideProgress();
                showToast(t.getMessage() != null ? t.getMessage() : "Please check your network connection");
            }
        });
        mAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {
                Intent i = new Intent(getApplicationContext(), VolumeDesc.class);
                startActivity(i);
            }
        });
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    private void clearBooks() {
        mEtBookName.setText("");
        mTvEmpty.setVisibility(View.VISIBLE);
        mVolumes.clear();
        mAdapter.notifyDataSetChanged();
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