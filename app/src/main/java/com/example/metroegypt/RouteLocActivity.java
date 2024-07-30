//package com.example.metroegypt;
//
//import android.content.Intent;
//import android.location.Location;
//import android.os.Bundle;
//import android.widget.TextView;
//import android.widget.Toast;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import mumayank.com.airlocationlibrary.AirLocation;
//
//
//public class RouteLocActivity extends AppCompatActivity implements AirLocation.Callback {
//
//    private AirLocation airLocation;
//    private TextView locationText;
//
//    // List of metro stations
//    private StationLatLog[] line1Stations = {
//            new StationLatLog("New El Marg", 30.164112161210504, 31.338407246966693),
//            new StationLatLog("El-Marg", 30.160889185079704, 31.334396874123186),
//            new StationLatLog("Ezbet El Nakhl", 30.13952207339943, 31.32446512078110),
//            new StationLatLog("Ain Shams", 30.131313304903674, 31.319037662856463),
//            new StationLatLog("El Matarya", 30.121512571825917, 31.313690117757158),
//            new StationLatLog("Helmiet El-Zaitoun", 30.113536390009884, 31.313907439478772),
//            new StationLatLog("Attaba", 30.052596686535175, 31.246813735292555),
//            new StationLatLog("Hadayeq El Zeitoun", 30.1061307408156, 31.31048331078931),
//            new StationLatLog("Saray El-Kobba", 30.097850160008868, 31.304584562978878),
//            new StationLatLog("Hammamat El Kobba", 30.09227468481345, 31.299212169672472),
//            new StationLatLog("Kobri El-Qobba", 30.08735476580684, 31.29409337506309),
//            new StationLatLog("Manshiet El-Sadr", 30.082003256288434, 31.28753159510405),
//            new StationLatLog("El Demerdash", 30.07719218254875, 31.27784178622453),
//            new StationLatLog("Ghamra", 30.069066164274854, 31.264616059345396),
//            new StationLatLog("Al-Shohadaa", 30.06112096043474, 31.24605172506954),
//            new StationLatLog("Orabi", 30.056846312396047, 31.24215076403469),
//            new StationLatLog("Sadat", 30.043820182094674, 31.235893778586146),
//            new StationLatLog("Saad Zaghloul", 30.037108761374522, 31.238295981646644),
//            new StationLatLog("El-Sayeda Zeinab", 30.029537745125488, 31.23543080637171),
//            new StationLatLog("El-Malek El-Saleh", 30.017961674213357, 31.23108243528616),
//            new StationLatLog("Mar Girgis", 30.00631307227942, 31.22960421900839),
//            new StationLatLog("El-Zahraa", 29.995533518680215, 31.23119330735051),
//            new StationLatLog("Dar El Salam", 29.982101270069652, 31.24216780063911),
//            new StationLatLog("Hadayek elmaadi", 29.970170701109165, 31.250583194636462),
//            new StationLatLog("Maadi", 29.96043812307291, 31.257624148940984),
//            new StationLatLog("Sakanat El Maadi", 29.953561133886616, 31.262913090985496),
//            new StationLatLog("Tora El-Balad", 29.946921288328074, 31.27303364840837),
//            new StationLatLog("Kozzika", 29.936507314797176, 31.281850154871513),
//            new StationLatLog("Tura El-Asmant", 29.926178912310633, 31.287565863193063),
//            new StationLatLog("El-Maasara", 29.914236361288356, 31.3202340817766),
//            new StationLatLog("Hadayek Helwan", 29.89994233485323, 31.302942281071697),
//            new StationLatLog("Wadi houf", 29.879149881173923, 31.313712534751417),
//            new StationLatLog("Helwan University", 29.870117950089504, 31.32029455234752),
//            new StationLatLog("Ain Helwan", 29.86421141400006, 31.324570957867326),
//            new StationLatLog("Helwan Metro Station", 29.850506555360035, 31.338341940430073)
//    };
//
//    private StationLatLog[] line2Stations = {
//            new StationLatLog("Kola alzraaa", 30.11369177008864, 31.248629557800403),
//            new StationLatLog("Mezlat", 30.104258316543266, 31.24560521782838),
//            new StationLatLog("Khalafawy", 30.097894257452975, 31.2454046834204),
//            new StationLatLog("St.teresa", 30.088077530923105, 31.245524889663365),
//            new StationLatLog("Rod El-Farag", 30.08081086386895, 31.245385447855785),
//            new StationLatLog("Masarra", 30.070904435666634, 31.245047455665354),
//            new StationLatLog("Attaba", 30.052596686535175, 31.246813735292555),
//            new StationLatLog("Mohamed Naguib", 30.04552586909326, 31.244130118463485),
//            new StationLatLog("Sadat", 30.043820182094674, 31.235893778586146),
//            new StationLatLog("Opera", 30.042105538627762, 31.22493648835908),
//            new StationLatLog("Dokki", 30.038680935734504, 31.212142074930817),
//            new StationLatLog("El Bohoos", 30.035984487949147, 31.20017398405803),
//            new StationLatLog("Cairo University", 30.026220200153265, 31.201009600831817),
//            new StationLatLog("Faisal", 30.017178131166798, 31.203911783598546),
//            new StationLatLog("Giza", 30.010905927334374, 31.20575952916723),
//            new StationLatLog("Omm El-Misryeen", 30.00563558462735, 31.20810959438553),
//            new StationLatLog("Sakiat Mekki", 29.995726048090724, 31.2085916324680),
//            new StationLatLog("El Monib", 29.981317386302095, 31.212378350159277)
//    };
//
//    private StationLatLog[] line3Stations = {
//            new StationLatLog("Adly Mansour", 30.146541448128655, 31.421373646449148),
//            new StationLatLog("Hikestep Metro", 30.144032187822322, 31.404620926893617),
//            new StationLatLog("Omar Ibn El Khattab", 30.14065799748161, 31.394387522771154),
//            new StationLatLog("Qubaa", 30.13520790483991, 31.383984302040393),
//            new StationLatLog("Hesham Barakat", 30.131681223676644, 31.373085106897836),
//            new StationLatLog("El Nozha", 30.12904127284386, 31.360267087378435),
//            new StationLatLog("El Shams Club", 30.125530327167915, 31.348848616279156),
//            new StationLatLog("Alf Maskan", 30.11920783446734, 31.340262107243568),
//            new StationLatLog("Heliopolis", 30.109226689932566, 31.338402335094226),
//            new StationLatLog("Haroun", 30.102417702505818, 31.333126127043393),
//            new StationLatLog("El Ahram", 30.09213696911747, 31.326315920445982),
//            new StationLatLog("Kolleyet El Banat", 30.08424776479164, 31.32910900429941),
//            new StationLatLog("Stadium", 30.072974476574103, 31.317113264403325),
//            new StationLatLog("Fair Zone", 30.073448483136193, 31.301025399821263),
//            new StationLatLog("El Abassiya", 30.072255226468055, 31.283377112466304),
//            new StationLatLog("Abdou Pasha", 30.06503633727404, 31.274826964174554),
//            new StationLatLog("El Geish", 30.062006652698113, 31.266959408011147),
//            new StationLatLog("Bab El Shaariya", 30.05416364404914, 31.255842449941415),
//            new StationLatLog("Attaba", 30.052514804500177, 31.246841457474687),
//            new StationLatLog("Nasser", 30.053503439999307, 31.238739731126138),
//            new StationLatLog("Maspero", 30.055877590377992, 31.232137684846524),
//            new StationLatLog("Safaa Hegazy", 30.06252158207906, 31.223377233111375),
//            new StationLatLog("Kit-Kat", 30.06678264029256, 31.213044862184674),
//            new StationLatLog("Sudan", 30.070166001930062, 31.20470905213861),
//            new StationLatLog("Imbaba", 30.075998375571718, 31.20748066211138),
//            new StationLatLog("EL-Bohy", 30.082279260166896, 31.210462317841223),
//            new StationLatLog("EL-Qawmia", 30.09326703424394, 31.209039661198418),
//            new StationLatLog("Rod El Farag", 30.101969799343554, 31.184408122923152),
//            new StationLatLog("Al Tawfiqeya Metro Station", 30.06528576629012, 31.202010198188564),
//            new StationLatLog("Wadi Al Nile Metro Station", 30.058401353414407, 31.20101139625351),
//            new StationLatLog("Gamet El Dowel", 30.050383530232256, 31.19907921407237),
//            new StationLatLog("Boulaq Metro Station", 30.03786350962994, 31.19565005413398),
//            new StationLatLog("Cairo University", 30.026220200153265, 31.201009600831817)
//    };
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//
//        locationText = findViewById(R.id.locationText);
//
//        airLocation = new AirLocation(this, this, true, 0, "");
//
//        // Request location
//        airLocation.start();
//    }
//
////    @Override
////    public void onLocationChanged(Location location) {
////        if (location != null) {
////            double currentLatitude = location.getLatitude();
////            double currentLongitude = location.getLongitude();
////
////            StationLatLog nearestStation = findNearestStation(currentLatitude, currentLongitude);
////            if (nearestStation != null) {
////                locationText.setText("Nearest Station: " + nearestStation.getName() + "\nDistance: " +
////                        distanceBetween(currentLatitude, currentLongitude, nearestStation.getLatitude(), nearestStation.getLongitude()) + " meters");
////            }
////        }
////    }
//
//    private StationLatLog findNearestStation(double currentLatitude, double currentLongitude) {
//        StationLatLog nearestStation = null;
//        double minDistance = Double.MAX_VALUE;
//
//        StationLatLog[] allStations = concatArrays(line1Stations, line2Stations, line3Stations);
//
//        for (StationLatLog station : allStations) {
//            double distance = distanceBetween(currentLatitude, currentLongitude, station.getLatitude(), station.getLongitude());
//            if (distance < minDistance) {
//                minDistance = distance;
//                nearestStation = station;
//            }
//        }
//        return nearestStation;
//    }
//
//    private double distanceBetween(double lat1, double lon1, double lat2, double lon2) {
//        float[] results = new float[1];
//        Location.distanceBetween(lat1, lon1, lat2, lon2, results);
//        return results[0];
//    }
//
//    private StationLatLog[] concatArrays(StationLatLog[]... arrays) {
//        List<StationLatLog> list = new ArrayList<>();
//        for (StationLatLog[] array : arrays) {
//            for (StationLatLog item : array) {
//                list.add(item);
//            }
//        }
//        return list.toArray(new StationLatLog[0]);
//    }
//
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        airLocation.onActivityResult(requestCode, resultCode, data);
//    }
//
//    @Override
//    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
//        airLocation.onRequestPermissionsResult(requestCode, permissions, grantResults);
//    }
//
////    @Override
////    public void onAirLocationPermissionGranted() {
////        Toast.makeText(this, "Location permission granted", Toast.LENGTH_SHORT).show();
////    }
////
////    @Override
////    public void onAirLocationPermissionDenied() {
////        Toast.makeText(this, "Location permission denied", Toast.LENGTH_SHORT).show();
////    }
////
////    @Override
////    public void onAirLocationProviderEnabled() {
////        Toast.makeText(this, "Location provider enabled", Toast.LENGTH_SHORT).show();
////    }
//
////    @Override
////    public void onAirLocationProviderDisabled() {
////        Toast.makeText(this, "Location provider disabled", Toast.LENGTH_SHORT).show();
////    }
//
//    @Override
//    public void onSuccess(@NonNull ArrayList<Location> locations) {
//        double lat = locations.get(0).getLatitude();
//        double lon = locations.get(0).getLongitude();
//
//        StationLatLog nearestStation = findNearestStation(lat, lon);
//        if (nearestStation != null) {
//            locationText.setText("Nearest Station: " + nearestStation.getName() + "\nDistance: " +
//                    distanceBetween(lat, lon, nearestStation.getLatitude(), nearestStation.getLongitude()) + " meters");
//        }
//    }
//
//    @Override
//    public void onFailure(@NonNull AirLocation.LocationFailedEnum locationFailedEnum) {
//        Toast.makeText(this, locationFailedEnum.name(), Toast.LENGTH_SHORT).show();
//    }
//}