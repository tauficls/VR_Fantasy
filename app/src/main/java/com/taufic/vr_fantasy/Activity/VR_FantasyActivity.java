package com.taufic.vr_fantasy.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taufic.vr_fantasy.BaseApi.ApiClass.Destination;
import com.taufic.vr_fantasy.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VR_FantasyActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.detail_container)
    LinearLayout mDetailContainer;
    @BindView(R.id.view_detail)
    Button mDetailButton;
    @BindView(R.id.view_web)
    Button mWebViewButton;

    SupportMapFragment mMapFragment;

    private GoogleMap googleMap;
    private Activity mActivity;
    ArrayList<Destination> destinationList;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference destination;


    private boolean isDataReady = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_fantasy);
        ButterKnife.bind(this);
        initializeView();
        mActivity = this;

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        destinationList = new ArrayList<>();
        destination = database.getReference("Destination");

    }

    private void retrieveData() {
        destination.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Destination destination = dataSnapshot.getValue(Destination.class);
                destinationList.add(destination);
                System.out.println("lat " + destination.getLatitude() + "long" + destination.getLongitude());
                // Create marker when map is ready
                createMarker(destination.getLatitude(), destination.getLongitude(), destination.getName(), 0);
                LatLng now = new LatLng(destination.getLatitude(), destination.getLongitude());
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(now));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void initializeView() {
        mDetailContainer.setVisibility(View.GONE);
        mWebViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWeb();
            }
        });
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail();
            }
        });

    }

    private void goToWeb() {

    }

    private void gotoDetail() {

    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;
//
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        retrieveData();

        // Add a marker in Sydney and move the camera

    }

    private Marker createMarker(double latitude, double longitude, String title, int iconResID) {
        return googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(latitude, longitude))
            .anchor(0.5f, 0.5f)
            .title(title)
            .icon(BitmapDescriptorFactory.defaultMarker()));
    }

}
