package com.example.basalam;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import android.os.Bundle;
import com.example.basalam.adapter.adapter;
import com.example.basalam.apiservice.apiservice;
import com.example.basalam.datamodel.Getset;


import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    adapter adapter;
    apiservice apiservice=new apiservice(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiservice.getpost(new apiservice.onpost() {
            @Override
            public void onpost(List<Getset> data) {
                recyclerView=(RecyclerView)findViewById(R.id.recyclerview);
                recyclerView.setLayoutManager(new StaggeredGridLayoutManager(1,StaggeredGridLayoutManager.VERTICAL));
                adapter=new adapter(MainActivity.this,data);
                recyclerView.setAdapter(adapter);
            }
        });
    }
}
