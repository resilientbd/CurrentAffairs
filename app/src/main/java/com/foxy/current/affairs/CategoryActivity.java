package com.foxy.current.affairs;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foxy.current.affairs.adapter.CategoryAdapter;
import com.foxy.current.affairs.model.Category;
import com.gturedi.views.StatefulLayout;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {


    Toolbar toolbar;
    ActionBarDrawerToggle toggle;
    RecyclerView categoryList;
    NavigationView navigationView;
    StatefulLayout statefulLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        StatefulLayout stateful = findViewById(R.id.stateful);
        stateful.showContent();
//        stateful.showEmpty("please check internet");
        navigationView = findViewById(R.id.navigationView);
        navigationView.setNavigationItemSelectedListener(this);
        toolbar = findViewById(R.id.toolbar);
        categoryList = findViewById(R.id.categoryList);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Current Affairs");
        toolbar.setSubtitle("welcome");
        DrawerLayout drawerLayout = findViewById(R.id.drawerId);
        toggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        List<Category> categories = new ArrayList<>();
        categories.add(new Category("Bangla"));
        categories.add(new Category("English"));
        categories.add(new Category("Generale knowledge"));
        categories.add(new Category("World Politics"));
        CategoryAdapter adapter = new CategoryAdapter(categories, this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        categoryList.setLayoutManager(layoutManager);
        categoryList.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        categoryList.setAdapter(adapter);
        getCategories();
    }

    private void getCategories() {
        StringRequest request = new StringRequest(Request.Method.GET, Config.host + "categories", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(CategoryActivity.this, "success", Toast.LENGTH_SHORT).show();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CategoryActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }
//        switch (item.getItemId()) {
//
//            case R.id.search:
//                startActivity(new Intent(CategoryActivity.this, CurrentAffairsActivity.class));
//                break;
//
//        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jobcircular:
                startActivity(new Intent(CategoryActivity.this, JobsActivity.class));
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent exit = new Intent(Intent.ACTION_MAIN);
        exit.addCategory( Intent.CATEGORY_HOME );
        exit.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(exit);
    }
}
