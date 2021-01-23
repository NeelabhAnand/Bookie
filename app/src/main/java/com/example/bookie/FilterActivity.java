package com.example.bookie;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    private String filterChosen;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);


        ArrayList<String> filters = new ArrayList<>();
        filters.add(0,"ebooks");
        filters.add(1,"free-ebooks");
        filters.add(2,"full");
        filters.add(3,"paid-ebooks");
        filters.add(4,"partial");

        FilterRVAdapter rvAdapter = new FilterRVAdapter(filters);
        RecyclerView recyclerView = findViewById(R.id.filter_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(rvAdapter);
        rvAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void OnItemClick(int position) {

                    Intent filterResult = getIntent();
                    filterResult.putExtra("filterClicked", filters.get(position));
                    setResult(FilterActivity.RESULT_OK,filterResult);
                    finish();

            }
        });

        }
    }
