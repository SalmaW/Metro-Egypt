package com.example.metroegypt;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class MetroActivity extends AppCompatActivity implements TextToSpeech.OnInitListener {
    TextView appName;
    ImageView metroLogo, startLocIcon, endLocIcon, soundIcon;
    EditText startLoc, endLoc;
    Spinner startSpinner, endSpinner;
    Button routeButton;

    String startStation;
    String endStation;

    TextToSpeech tts;
    SharedPreferences pref;
    boolean isSoundON = true;

    protected void onDestroy() {
        if (tts != null) {
            tts.stop();
            tts.shutdown();
        }
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
        soundIcon = findViewById(R.id.soundIcon);
        tts = new TextToSpeech(this, this);
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

        // Set up listeners for Spinners
        startSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                startStation = parent.getItemAtPosition(position).toString();
                if (isSoundON && tts != null) {
                    tts.speak(startStation, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        endSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                endStation = parent.getItemAtPosition(position).toString();
                if (isSoundON && tts != null) {
                    tts.speak(endStation, TextToSpeech.QUEUE_FLUSH, null, null);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }


    public void mainPage(View view) {
        Intent goTo = new Intent(this, MainActivity.class);
        startActivity(goTo);
    }

    public void errorMovementLoc() {
        YoYo.with(Techniques.Shake).duration(700).playOn(startLoc);
        YoYo.with(Techniques.Shake).duration(700).playOn(endLoc);
    }

    public void errorMovementSpinner() {
        YoYo.with(Techniques.Shake).duration(700).playOn(startSpinner);
        YoYo.with(Techniques.Shake).duration(700).playOn(endSpinner);
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

    public void calcRoute(View view) {
        if ((startLoc.getText().toString().isEmpty() || endLoc.getText().toString().isEmpty()) && (startSpinner.getSelectedItem().toString().equals("Select Station") || endSpinner.getSelectedItem().toString().equals("Select Station"))) {
            Toast.makeText(this, "Please select another station or enter another Location", Toast.LENGTH_SHORT).show();
            errorMovementSpinner();
            errorMovementLoc();
            return;
        }

//        if (!(startLoc.getText().toString().equals(endLoc.getText().toString())) || (!startLoc.getText().toString().isEmpty() && !endLoc.getText().toString().isEmpty())) {
//            Toast.makeText(this, "Route by location", Toast.LENGTH_SHORT).show();
//            Intent goTo = new Intent(this, RouteActivity.class);
//            startActivity(goTo);
//        } else {
//            Toast.makeText(this, "Please enter a valid Location", Toast.LENGTH_SHORT).show();
//            errorMovementLoc();
//            return;
//        }

        if (!(startSpinner.getSelectedItem().toString().equals(endSpinner.getSelectedItem().toString())) && !(startSpinner.getSelectedItem().toString().equals("Select Station") || endSpinner.getSelectedItem().toString().equals("Select Station"))) {
//            Toast.makeText(this, "Route by station", Toast.LENGTH_SHORT).show();
            startStation = startSpinner.getSelectedItem().toString();
            endStation = endSpinner.getSelectedItem().toString();

            Intent goTo = new Intent(this, RouteActivity.class);
            goTo.putExtra("startStation", startStation);
            goTo.putExtra("endStation", endStation);
            startActivity(goTo);
        } else {
            Toast.makeText(this, "Please select another station", Toast.LENGTH_SHORT).show();
            errorMovementSpinner();
        }

    }

    @Override
    public void onInit(int status) {

    }
}