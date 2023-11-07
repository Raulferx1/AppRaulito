package com.example.appraulito;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class mapa extends AppCompatActivity implements OnMapReadyCallback {
    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mapa);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.mapFragment);
        mapFragment.getMapAsync(this);
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        LatLng ubicacion1 = new LatLng(-38.95551005004482, -72.62684538661978);
        MarkerOptions markerOptions1 = new MarkerOptions()
                .position(ubicacion1)
                .title("Tienda Martin Pescador Freire")
                .snippet("Tienda de pesca ubicada en Freire cerca del río Tolten");
        mMap.addMarker(markerOptions1);


        LatLng ubicacion2 = new LatLng(-39.28035583298907, -71.97961924433307);
        MarkerOptions markerOptions2 = new MarkerOptions()
                .position(ubicacion2)
                .title("ChilePescaMosca")
                .snippet("Tienda especializada en la pesca con mosca en Pucon");
        mMap.addMarker(markerOptions2);


        LatLng ubicacion3 = new LatLng(-38.736149901014066, -72.61849278908103);
        MarkerOptions markerOptions3 = new MarkerOptions()
                .position(ubicacion3)
                .title("Planeta Outdoor")
                .snippet("Tienda de pesca con mosca cerca del río Cautín");
        mMap.addMarker(markerOptions3);


        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        builder.include(ubicacion1);
        builder.include(ubicacion2);
        builder.include(ubicacion3);
        LatLngBounds bounds = builder.build();


        int padding = 100; //
        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));


    }
}

