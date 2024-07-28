package com.example.metroegypt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class LocationActivity extends AppCompatActivity {
    TextView appName;
    ImageView metroLogo, startLocIcon, endLocIcon, soundIcon;
    EditText startLoc, endLoc;
    Button routeButton;
    SharedPreferences pref;
    boolean isSoundON = true;

    @Override
    protected void onDestroy() {
        SharedPreferences.Editor editor = pref.edit();
        editor.putBoolean("sound", isSoundON);
        editor.apply();

        super.onDestroy();
    }

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
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_location);

        appName = findViewById(R.id.textView);
        metroLogo = findViewById(R.id.metroLogo);
        startLocIcon = findViewById(R.id.startLocIcon);
        endLocIcon = findViewById(R.id.endLocIcon);
        startLoc = findViewById(R.id.startLoc);
        endLoc = findViewById(R.id.endLoc);
        routeButton = findViewById(R.id.routeButton);
        soundIcon = findViewById(R.id.soundIcon);
        pref = getSharedPreferences("settings", MODE_PRIVATE);
        isSoundON = pref.getBoolean("sound", true);
        if (isSoundON) {
            soundIcon.setImageResource(R.drawable.sound_on);
        } else {
            soundIcon.setImageResource(R.drawable.sound_off);
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void changeSound(View view) {
        YoYo.with(Techniques.Swing).duration(300).playOn(soundIcon);
        if (isSoundON) {
            soundIcon.setImageResource(R.drawable.sound_off);
            Toast.makeText(this, "Reading Out Loud Sound OFF", Toast.LENGTH_SHORT).show();
            isSoundON = false;
        } else {
            soundIcon.setImageResource(R.drawable.sound_on);
            Toast.makeText(this, "Reading Out Loud Sound ON", Toast.LENGTH_SHORT).show();
            isSoundON = true;
        }
    }

    public void goToMap(View view) {
        Intent goTo = new Intent(this, MapActivity.class);
        startActivity(goTo);
    }

    public void errorMovementLoc() {
        YoYo.with(Techniques.Shake).duration(700).playOn(startLoc);
        YoYo.with(Techniques.Shake).duration(700).playOn(endLoc);
    }


    public void calcRoute(View view) {
        if ((startLoc.getText().toString().equals(endLoc.getText().toString()))) {
            Toast.makeText(this, "Please select another station or enter another Location", Toast.LENGTH_SHORT).show();
            errorMovementLoc();
            return;
        }
        Toast.makeText(this, "this feature is not available at the moment", Toast.LENGTH_SHORT).show();
    }
}