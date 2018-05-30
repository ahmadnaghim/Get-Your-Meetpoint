package com.example.ahmadnaghimfp.weatheraroundyou;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private DataCoy refData = new DataCoy();
    private double sumLat, sumLng, count, finalLat, finalLng;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        refData.MeetPoint();
        refData.getDataPertama().addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                double x = dataSnapshot.getValue(Double.class);
                sumLat = x;
                refData.getDataKedua().addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        double y = dataSnapshot.getValue(Double.class);
                        sumLng = y;
                        refData.getDataKetiga().addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                double z = dataSnapshot.getValue(Double.class);
                                count = z;
                                mMap = googleMap;
                                float zoomLvl = 16.0f;
                                LatLng posisi = new LatLng(x/z,y/z);
                                mMap.addMarker(new MarkerOptions().position(posisi).title("MeetPoint"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(posisi, zoomLvl));
                            }

                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}