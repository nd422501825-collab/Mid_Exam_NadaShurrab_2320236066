package com.example.mid_exam_nadashurrab;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static final String KEY_INDEX = "current_artwork_index";

    private TextView tvCounter;
    private ImageView ivArtwork;
    private TextView tvTitle;
    private TextView tvArtistYear;
    private Button btnPrevious;
    private Button btnNext;

    private final List<Artwork> artworks = new ArrayList<>();
    private int currentIndex = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCounter = findViewById(R.id.tvCounter);
        ivArtwork = findViewById(R.id.imgArtwork);
        tvTitle = findViewById(R.id.tvTitle);
        tvArtistYear = findViewById(R.id.tvArtist);
        btnPrevious = findViewById(R.id.btnPrevious);
        btnNext = findViewById(R.id.btnNext);

        initArtworks();

        if (savedInstanceState != null) {
            currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        }

        updateUI();

        btnNext.setOnClickListener(v -> {
            if (currentIndex < artworks.size() - 1) {
                currentIndex++;
                updateUI();
            }
        });

        btnPrevious.setOnClickListener(v -> {
            if (currentIndex > 0) {
                currentIndex--;
                updateUI();
            }
        });

        ivArtwork.setOnClickListener(v -> {
            Artwork current = artworks.get(currentIndex);
            Intent intent = new Intent(MainActivity.this, ArtworkDetailsActivity.class);
            intent.putExtra("EXTRA_TITLE", current.getTitle());
            intent.putExtra("EXTRA_ARTIST", current.getArtist());
            intent.putExtra("EXTRA_YEAR", current.getYear());
            intent.putExtra("EXTRA_URL", current.getWebsiteUrl());
            startActivity(intent);
        });
    }

    private void initArtworks() {
        artworks.add(new Artwork(
                R.drawable.art_1,
                "Deep Field Stellar Deaths",
                "Smacs Carin",
                2021,
                "https://science.nasa.gov/mission/hubble/"
        ));
        artworks.add(new Artwork(
                R.drawable.art_2,
                "Pillars of Creation",
                "Hubble Heritage",
                2014,
                "https://science.nasa.gov/mission/hubble/"
        ));
        artworks.add(new Artwork(
                R.drawable.art_3,
                "Carina Nebula Glow",
                "James Webb Team",
                2022,
                "https://webbtelescope.org/"
        ));
        artworks.add(new Artwork(
                R.drawable.art_4,
                "Sombrero Galaxy Dust",
                "NASA Archive",
                2011,
                "https://science.nasa.gov/"
        ));
        artworks.add(new Artwork(
                R.drawable.art_5,
                "Orion Star Nursery",
                "ESA Observatory",
                2018,
                "https://www.esa.int/"
        ));
        artworks.add(new Artwork(
                R.drawable.art_6,
                "Hubble Ngc Flat Final",
                "Sonay Billan",
                2019,
                "https://science.nasa.gov/"
        ));
    }

    private void updateUI() {
        Artwork current = artworks.get(currentIndex);
        tvCounter.setText((currentIndex + 1) + " / " + artworks.size());
        ivArtwork.setImageResource(current.getImageResId());
        tvTitle.setText(current.getTitle());
        tvArtistYear.setText(current.getArtist() + " (" + current.getYear() + ")");

        btnPrevious.setEnabled(currentIndex > 0);
        btnNext.setEnabled(currentIndex < artworks.size() - 1);
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(KEY_INDEX, currentIndex);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        currentIndex = savedInstanceState.getInt(KEY_INDEX, 0);
        updateUI();
    }
}