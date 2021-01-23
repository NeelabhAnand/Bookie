package com.example.bookie;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bookie.utils.Preference;

import java.util.ArrayList;

public class FilterActivity extends AppCompatActivity {
    private ArrayList<String> filters = new ArrayList<>();
    private String filterChosen;
    private RecyclerView recyclerView;
    private Preference filterPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_filter);
        filterPref = Preference.getInstance(this);

        init();
        addList();
        initAdapter();
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
            FilterRVAdapter rvAdapter = new FilterRVAdapter(filters, filterPref.getSelectedFilter());
            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            recyclerView.setAdapter(rvAdapter);
            rvAdapter.setOnItemClickListener(new OnItemClickListener() {
                @Override
                public void OnItemClick(int position){
                    filterPref.setSelectedFilter(filters.get(position));
                    finish();
                }
            });

        }
    }
