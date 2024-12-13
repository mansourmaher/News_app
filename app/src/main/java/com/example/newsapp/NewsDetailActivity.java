package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class NewsDetailActivity extends AppCompatActivity {

    private ImageView imageView;
    private TextView titleTextView, contentTextView, dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_detail);

        // Initialize views
        imageView = findViewById(R.id.news_image);
        titleTextView = findViewById(R.id.news_title);
        contentTextView = findViewById(R.id.news_content);
        dateTextView = findViewById(R.id.news_date);

        // Get data from intent
        String imageUrl = getIntent().getStringExtra("image_url");
        String title = getIntent().getStringExtra("title");
        String content = getIntent().getStringExtra("content");
        String date = getIntent().getStringExtra("date");

        // Set the data in the views

        titleTextView.setText(title);
        contentTextView.setText(content);
        //dateTextView.setText(date);

        Glide.with(this)
                .load(imageUrl)
                .into(imageView);
    }
}