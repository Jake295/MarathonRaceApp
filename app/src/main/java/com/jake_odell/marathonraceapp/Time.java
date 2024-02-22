package com.jake_odell.marathonraceapp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Time extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_time);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        TextView pace = (TextView) findViewById(R.id.pace);
        ImageView image = (ImageView) findViewById(R.id.imgMedal);

        SharedPreferences sharedPref = PreferenceManager.getDefaultSharedPreferences(this);

        int distance = 26;
        int hours = sharedPref.getInt("key1", 0);
        int minutes = sharedPref.getInt("key2", 0);
        int totalMinutes = (hours * 60) + minutes;

        double averagePace = totalMinutes / distance;

        pace.setText("Average pace: " + averagePace + " minutes per mile");

        if (averagePace < 11) {
            image.setImageResource(R.drawable.gold);
        } else if (averagePace < 15) {
            image.setImageResource(R.drawable.silver);
        } else if (totalMinutes <= 600) {
            image.setImageResource(R.drawable.bronze);
        } else {
            pace.setText("Completion time cannot be more than 10 hours.");
        }
    }
}