package com.example.bookie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    private ArrayList<String> filters = new ArrayList<>();
    private String filterChosen;
    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);

        init();
        addList();
        initAdapter();

        filterChosen = getIntent().getStringExtra("selectedFilterFromActivity");

    }

        private void addList(){
            filters.add("ebooks");
            filters.add("free-ebooks");
            filters.add("full");
            filters.add("paid-ebooks");
            filters.add("partial");
        }

        private void init(){
            recyclerView = findViewById(R.id.filter_view);
        }

        private void initAdapter(){
            FilterRVAdapter rvAdapter = new FilterRVAdapter(filters, filterChosen);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(rvAdapter);
            rvAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void OnItemClick(int position) {
                    Intent filterResult = new Intent();
                    filterResult.putExtra("filterClicked", filters.get(position));
                    setResult(RESULT_OK,filterResult);
                    finish();

                }
            });

        }
    }
