package com.example.basalam;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.basalam.adapter.adapter;
import com.example.basalam.api.ApiService;
import com.example.basalam.model.Data;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private adapter adapter;
    private final ApiService apiService = new ApiService(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        apiService.getPosts(new ApiService.DataCallback() {
            @Override
            public void onDataReceived(List<Data> data) {
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(
                        new StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
                );
                adapter = new adapter(data);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
