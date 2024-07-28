package com.example.metroegypt;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MainActivity extends AppCompatActivity {
    TextView welcomeTxt, descTxt, whereTxt;
    ImageView metroLogo;
    Button metroStationsButton;

    @Override
    protected void onStart() {
        super.onStart();
        YoYo.with(Techniques.FadeInDown).duration(2000).playOn(metroLogo);
        YoYo.with(Techniques.FadeInDown).duration(2000).playOn(welcomeTxt);
        YoYo.with(Techniques.Shake).delay(2000).duration(700).playOn(whereTxt);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        welcomeTxt = findViewById(R.id.welcomeTxt);
        descTxt = findViewById(R.id.descTxt);
        whereTxt = findViewById(R.id.whereTxt);
        metroLogo = findViewById(R.id.metroLogo);
        metroStationsButton = findViewById(R.id.metroStationsButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });


    }

    public void metroStations(View view) {
        Intent goTo = new Intent(this, MetroActivity.class);
        startActivity(goTo);
    }

    public void goTo(View view) {
        Intent goTo = new Intent(this, LocationActivity.class);
        startActivity(goTo);
    }
}