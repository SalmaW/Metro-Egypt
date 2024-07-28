package com.example.metroegypt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MetroActivity extends AppCompatActivity {
    TextView appName;
    ImageView metroLogo, startLocIcon, endLocIcon;
    EditText startLoc, endLoc;
    Spinner startSpinner, endSpinner;
    Button routeButton;

    @Override
    protected void onStart() {
        super.onStart();
        YoYo.with(Techniques.FadeIn).duration(2000).playOn(metroLogo);
        YoYo.with(Techniques.FadeIn).duration(2000).playOn(appName);
        YoYo.with(Techniques.FadeIn).duration(2000).playOn(routeButton);
        YoYo.with(Techniques.FadeInLeft).duration(2000).playOn(startLocIcon);
        YoYo.with(Techniques.FadeInLeft).duration(2000).playOn(endLocIcon);
        YoYo.with(Techniques.FlipInX).duration(2000).playOn(startLoc);
        YoYo.with(Techniques.FlipInX).duration(2000).playOn(endLoc);
        YoYo.with(Techniques.FlipInX).duration(2000).playOn(startSpinner);
        YoYo.with(Techniques.FlipInX).duration(2000).playOn(endSpinner);


//        YoYo.with(Techniques.Shake).delay(2000).duration(700).playOn(whereTxt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_metro);
        appName = findViewById(R.id.textView);
        metroLogo = findViewById(R.id.metroLogo);
        startLocIcon = findViewById(R.id.startLocIcon);
        endLocIcon = findViewById(R.id.endLocIcon);
        startLoc = findViewById(R.id.startLoc);
        endLoc = findViewById(R.id.endLoc);
        startSpinner = findViewById(R.id.startSpinner);
        endSpinner = findViewById(R.id.endSpinner);
        routeButton = findViewById(R.id.routeButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void mainPage(View view) {
        Intent goTo = new Intent(this, MainActivity.class);
        startActivity(goTo);
    }

    public void calcRoute(View view) {
        Intent goTo = new Intent(this, RouteActivity.class);
        startActivity(goTo);
    }
}