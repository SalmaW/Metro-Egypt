package com.example.metroegypt;

import android.os.Bundle;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class AllPathsActivity extends AppCompatActivity {
    TextView result;
    String startStation = "";
    String endStation = "";
    ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_all_paths);
        result = findViewById(R.id.result);
        scrollView = findViewById(R.id.scrollView);

        startStation = getIntent().getStringExtra("startStation");
        endStation = getIntent().getStringExtra("endStation");

        paths();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void paths() {
        scrollView.post(() -> scrollView.fullScroll(ScrollView.FOCUS_UP));
        List<List<String>> allPaths = MainCode.findAllPaths(startStation, endStation);
        result.setText("\nAll possible paths from " + startStation.toUpperCase() + " to " + endStation.toUpperCase() + ":\n");
        int min = allPaths.get(0).size();
        int shortIndex = 0;
        for (List<String> path : allPaths) {
            if (path.size() < min) {
                min = path.size();
                shortIndex = allPaths.indexOf(path);
            }
            result.append("\n" + path + "\n\n");
        }
        result.append("\nShortest Route: " + allPaths.get(shortIndex));
    }
}