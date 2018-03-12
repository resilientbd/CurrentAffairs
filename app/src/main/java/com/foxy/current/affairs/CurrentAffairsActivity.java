package com.foxy.current.affairs;

import android.view.Menu;
import android.view.MenuItem;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.RetryPolicy;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.foxy.current.affairs.adapter.CurrentAffairAdapter;
import com.foxy.current.affairs.model.CurrentAffair;
import com.gturedi.views.StatefulLayout;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentAffairsActivity extends AppCompatActivity {
    private static final String URL = "https://jsonplaceholder.typicode.com/posts";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private List<CurrentAffair> listItems;

    Toolbar toolbar;
    StatefulLayout statefulLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_affairs);
        statefulLayout = findViewById(R.id.stateful);
        recyclerView = findViewById(R.id.recycleViewId);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, layoutManager.getOrientation()));
        listItems = new ArrayList<>();
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        loadRecycleViewData();
    }

    private void loadRecycleViewData() {
        statefulLayout.showLoading();
        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray array = new JSONArray(response);
                            for (int i = 0; i < array.length(); i++) {
                                JSONObject o = array.getJSONObject(i);
                                CurrentAffair item = new CurrentAffair(
                                        o.getString("title"),
                                        o.getString("body")
                                );
                                listItems.add(item);
                            }
                            adapter = new CurrentAffairAdapter(listItems, CurrentAffairsActivity.this);
                            recyclerView.setAdapter(adapter);
                            statefulLayout.showContent();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CurrentAffairsActivity.this, error.toString(), Toast.LENGTH_SHORT).show();
                statefulLayout.showOffline("No Internet Connection", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadRecycleViewData();
                    }
                });
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        int socketTimeout = 30000; // 30 seconds. You can change it
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);

        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.current_menu, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return super.onOptionsItemSelected(item);
    }
}
