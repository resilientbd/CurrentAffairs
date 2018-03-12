package com.foxy.current.affairs;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.foxy.current.affairs.adapter.JobsAdapter;
import com.foxy.current.affairs.model.Job;
import com.gturedi.views.StatefulLayout;

import java.util.ArrayList;
import java.util.List;

public class JobsActivity extends AppCompatActivity  {
    private RecyclerView recyclerView;
    private JobsAdapter adapter;
    private List<Job> listItems;

    Toolbar toolbar;
    StatefulLayout statefulLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jobs);

        statefulLayout = findViewById(R.id.stateful);
        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        listItems = new ArrayList<>();

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Jobs Circular");
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getData();
    }

    private void getData() {
        listItems.add(new Job("this a job","description"));
        adapter=new JobsAdapter(listItems,this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (android.R.id.home == item.getItemId()) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
