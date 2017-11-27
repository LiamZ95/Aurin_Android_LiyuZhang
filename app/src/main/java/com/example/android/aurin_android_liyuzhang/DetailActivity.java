/**
 * Created by PENGFEI XU on 2017.
 */
package com.example.android.aurin_android_liyuzhang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

public class DetailActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    // define functions for controllers.
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        // Initializing google map
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map_fragment);
        mapFragment.getMapAsync(this);

        Intent intent = getIntent();
        if ("action".equals(intent.getAction())) {
            // Get the selected single capacity from SecondActivity
            Capabilities cap = (Capabilities)intent.getSerializableExtra("capobj");
            Picked_City.cap_picked = cap;
            // Show the info about this capacity
            TextView title = (TextView) findViewById(R.id.title_text);
            title.setText(cap.title);

            TextView org = (TextView) findViewById(R.id.org_text);
            org.setText(cap.organization);

            TextView abstracts = (TextView) findViewById(R.id.abstract_text);
            abstracts.setText(cap.abstracts);

            // Get the coordinates of the capacity, which is bbox
            TextView lowla = (TextView) findViewById(R.id.lowerla_text);
            lowla.setText(cap.bbox.getLowerLa().toString());

            TextView hila = (TextView) findViewById(R.id.higherla_text);
            hila.setText(cap.bbox.getHigherLa().toString());

            TextView lowlo = (TextView) findViewById(R.id.lowerlo_text);
            lowlo.setText(cap.bbox.getLowerLon().toString());

            TextView hilo = (TextView) findViewById(R.id.higherlo_text);
            hilo.setText(cap.bbox.getHigherLon().toString());

            // Visualize the capacity using google map
            ImageButton showmap = (ImageButton) findViewById(R.id.show_map);
            showmap.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(DetailActivity.this,Map_Filter.class);
                    startActivity(intent);
                }
            });

        }

    }

    // map fragment
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Get the centre of the capacity
        double lla = Picked_City.cap_picked.bbox.getLowerLa();
        double hla = Picked_City.cap_picked.bbox.getHigherLa();
        double llo = Picked_City.cap_picked.bbox.getLowerLon();
        double hlo = Picked_City.cap_picked.bbox.getHigherLon();
        LatLng center = new LatLng((lla+hla)/2.0,(llo+hlo)/2.0);

        // Set zoom ratio
        int zoom = (int) Math.log(210/(hlo - llo)) + 1;
        // TOBEIMPROVED
        mMap.addMarker(new MarkerOptions().position(center).title("Marker in place"));

        mMap.moveCamera(CameraUpdateFactory.newLatLng(center));
        mMap.isMyLocationEnabled();
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(center, zoom));

        // Using polylines to show the area of capacity
        mMap.addPolyline(new PolylineOptions().geodesic(true)
                .add(new LatLng(lla, llo))
                .add(new LatLng(hla, llo))
                .add(new LatLng(hla, hlo))
                .add(new LatLng(lla, hlo))
                .add(new LatLng(lla, llo))
        );
    }

}