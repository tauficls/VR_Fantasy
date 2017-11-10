package com.taufic.vr_fantasy.Activity;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.taufic.vr_fantasy.BaseApi.ApiClass.Destination;
import com.taufic.vr_fantasy.BaseApi.GoogleMapApi;
import com.taufic.vr_fantasy.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VR_FantasyActivity extends AppCompatActivity implements OnMapReadyCallback {
    @BindView(R.id.detail_container)
    LinearLayout mDetailContainer;
    @BindView(R.id.search_query)
    EditText mSearch;
    @BindView(R.id.view_detail)
    Button mDetailButton;

    SupportMapFragment mMapFragment;

    private GoogleMap googleMap;
    private Activity mActivity;
    private ArrayList<Destination> destinationList;
    private Destination dest;
    private int position = 0;

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference destination;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_home, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr_fantasy);
        ButterKnife.bind(this);
        initializeView();
        mActivity = this;

        getSupportActionBar().setDisplayOptions(
            ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.custom_action_bar);

        mMapFragment = (SupportMapFragment) getSupportFragmentManager()
            .findFragmentById(R.id.map);
        mMapFragment.getMapAsync(this);

        destinationList = new ArrayList<>();
        dest = new Destination();
        destination = database.getReference("Destination");
    }

    @Override
    protected void onStart() {
        super.onStart();
        GoogleMapApi.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
        GoogleMapApi.onStop();
    }

    private void retrieveData() {
        destination.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                Destination destination = dataSnapshot.getValue(Destination.class);
                destinationList.add(destination);

                // Create marker when map is ready
                createMarker(destination.getLatitude(), destination.getLongitude(), destination.getName(), 0);
                googleMap.setOnMarkerClickListener(new GoogleMap.OnMarkerClickListener() {
                    @Override
                    public boolean onMarkerClick(Marker marker) {
                        mDetailContainer.setVisibility(View.VISIBLE);
                        for (Destination data : destinationList) {
                            if(data.getName().equals(marker.getTitle())) {
                                dest = data;
                                break;
                            }
                        }
                        return true;
                    }
                });
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
        mDetailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoDetail();
            }
        });

    }


    private void gotoDetail() {
        DetailActivity.startThisActivitiy(mActivity, dest);
    }

    private void initCamera(Location location) {
        CameraPosition position = CameraPosition.builder()
            .target(new LatLng(location.getLatitude(),
                location.getLongitude()))
            .zoom(8f)
            .bearing(0.0f)
            .tilt(0.0f)
            .build();

        googleMap.animateCamera(CameraUpdateFactory
            .newCameraPosition(position), null);

        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);
        googleMap.setTrafficEnabled(true);
        if (ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(mActivity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

        } else {
            googleMap.setMyLocationEnabled(true);
        }
        googleMap.getUiSettings().setZoomControlsEnabled(true);
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        retrieveData();

        GoogleMapApi.myLocation(mActivity, new GoogleMapApi.GoogleLocationCallBack() {
            @Override
            public void OnSuccess(Location location) {
                System.out.println("latitude " + location.getLatitude() + " " + location.getLongitude());
                initCamera(location);
            }
        });
    }

    private Marker createMarker(double latitude, double longitude, String title, int iconID) {
        return googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(latitude, longitude))
            .anchor(0.5f, 0.5f)
            .title(title)
            .icon(BitmapDescriptorFactory.defaultMarker()));
    }

}
