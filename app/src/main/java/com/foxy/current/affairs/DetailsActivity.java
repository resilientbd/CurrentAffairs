package com.foxy.current.affairs;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class DetailsActivity extends AppCompatActivity {

    TextView titleTextView, bodyTextView;
    Toolbar toolbar;
    Button quizButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        String title = getIntent().getExtras().getString("title");
        String body = getIntent().getExtras().getString("body");
        titleTextView = findViewById(R.id.detitleId);
        bodyTextView = findViewById(R.id.debodyId);
        titleTextView.setText(title);
        bodyTextView.setText(body);
        toolbar = findViewById(R.id.toolbar);
        quizButton = findViewById(R.id.quizButton);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Details");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        quizButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(DetailsActivity.this, QuizActivity.class));
            }
        });
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.details_menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        switch (item.getItemId()) {

            case R.id.home:
                startActivity(new Intent(DetailsActivity.this, CategoryActivity.class));
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}
