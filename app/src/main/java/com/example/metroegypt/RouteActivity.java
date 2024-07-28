package com.example.metroegypt;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RouteActivity extends AppCompatActivity {
    TextView textView, timeTxt, priceTxt, noStationsTxt, routeDesc, result;
    int count = 0;
    int price = 0;


//    protected void onStart() {
//        super.onStart();
//        YoYo.with(Techniques.FadeIn).duration(2000).playOn(metroLogo);
//        YoYo.with(Techniques.FadeIn).duration(2000).playOn(appName);
//        YoYo.with(Techniques.FadeIn).duration(2000).playOn(routeButton);
//        YoYo.with(Techniques.FadeInLeft).duration(2000).playOn(startLocIcon);
//        YoYo.with(Techniques.FadeInLeft).duration(2000).playOn(endLocIcon);
//        YoYo.with(Techniques.FlipInX).duration(2000).playOn(startLoc);
//        YoYo.with(Techniques.FlipInX).duration(2000).playOn(endLoc);
//        YoYo.with(Techniques.FlipInX).duration(2000).playOn(startSpinner);
//        YoYo.with(Techniques.FlipInX).duration(2000).playOn(endSpinner);
//
//
////        YoYo.with(Techniques.Shake).delay(2000).duration(700).playOn(whereTxt);
//    }


    @Override
    public void onBackPressed() {
        MainCode.routeStations = new ArrayList<>();
//        price = 0;
        timeTxt.setText("");
        priceTxt.setText("");
        noStationsTxt.setText("");

        super.onBackPressed();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_route);
        textView = findViewById(R.id.textView);
        timeTxt = findViewById(R.id.timeTxt);
        priceTxt = findViewById(R.id.priceTxt);
        noStationsTxt = findViewById(R.id.noStationsTxt);
        routeDesc = findViewById(R.id.routeDesc);
        result = findViewById(R.id.result);


        String startStation = getIntent().getStringExtra("startStation");
        String endStation = getIntent().getStringExtra("endStation");

        functions(startStation, endStation);


//        result.setText(startStation + " - " + endStation); // Text

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    public void functions(String startStation, String endStation) {
        List<Integer> startLines = MainCode.findLines(startStation);
        List<Integer> endLines = MainCode.findLines(endStation);

        Set<Integer> commonLines = new HashSet<>(startLines);
        commonLines.retainAll(endLines);

        if (!commonLines.isEmpty()) {
            int commonLine = commonLines.iterator().next();
            routeDesc.setText(MainCode.getNewDirection(startStation, endStation));
            result.setText("Take Line " + commonLine + " from " + startStation.toUpperCase() + " to " + endStation.toUpperCase());
            result.append("\n" + MainCode.printStations(startStation, endStation, commonLine));
            count = MainCode.routeStations.size();
        } else {
            int startLine = startLines.get(0);
            int endLine = endLines.get(0);
            ArrayList<String> startLineName = MainCode.getLineName(startLine);

            String interchangeStation = MainCode.getInterchangeStation(startLine, endLine, startStation, startLineName);
            routeDesc.setText(MainCode.getNewDirection(startStation, interchangeStation));
            result.setText("Take Line " + startLine + " from -" + startStation.toUpperCase() + "- to -" + interchangeStation.toUpperCase() + "- :");
            result.append("\n" + MainCode.printStations(startStation, interchangeStation, startLine));
            routeDesc.append("\n" + MainCode.getNewDirection(interchangeStation, endStation));
            result.append("\n\nChange to Line " + endLine + " and from -" + interchangeStation.toUpperCase() + "- to -" + endStation.toUpperCase() + "- :");
            result.append("\n" + MainCode.printStations(interchangeStation, endStation, endLine));
            count = MainCode.routeStations.size() - 1;

        }
        noStationsTxt.setText(String.valueOf(count));
        price = MainCode.getPrice(count);
        priceTxt.setText(price + " EGP");
        timeTxt.setText(MainCode.calculateEstimatedTime(count));

    }
}