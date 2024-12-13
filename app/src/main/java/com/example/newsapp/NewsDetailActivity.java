package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.share:
                shareNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.share_menu, menu);
        return true;
    }
    private void shareNote() {

        String newsTitle=titleTextView.getText().toString();
        String newsContent=contentTextView.getText().toString();


        String noteContent = "This is the content of the note. It can contain detailed information about the note.";


        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, newsTitle);
        shareIntent.putExtra(Intent.EXTRA_TEXT, newsContent);


        startActivity(Intent.createChooser(shareIntent, "Share via"));
    }
}