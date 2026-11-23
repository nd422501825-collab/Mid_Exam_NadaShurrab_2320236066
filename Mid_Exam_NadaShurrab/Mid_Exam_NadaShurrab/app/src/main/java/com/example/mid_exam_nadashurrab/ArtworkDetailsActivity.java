package com.example.mid_exam_nadashurrab;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ArtworkDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artwork_details);

        TextView tvDetailTitle = findViewById(R.id.tvDetailTitle);
        TextView tvDetailArtist = findViewById(R.id.tvDetailArtist);
        TextView tvDetailYear = findViewById(R.id.tvDetailYear);
        Button btnVisitWebsite = findViewById(R.id.btnVisitWebsite);
        Button btnBack = findViewById(R.id.btnBack);

        Intent intent = getIntent();
        String title = intent.getStringExtra("EXTRA_TITLE");
        String artist = intent.getStringExtra("EXTRA_ARTIST");
        int year = intent.getIntExtra("EXTRA_YEAR", 0);
        String url = intent.getStringExtra("EXTRA_URL");

        tvDetailTitle.setText(title != null ? title : "");
        tvDetailArtist.setText("Artist: " + (artist != null ? artist : ""));
        tvDetailYear.setText("Year: " + year);

        btnVisitWebsite.setOnClickListener(v -> {
            if (url != null && !url.isEmpty()) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        btnBack.setOnClickListener(v -> finish());
    }
}