package com.taufic.vr_fantasy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taufic.vr_fantasy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VR_FantasyActivity extends AppCompatActivity implements OnMapReadyCallback {

    @BindView(R.id.detail_container)
    LinearLayout mDetailContainer;
    @BindView(R.id.)

    SupportMapFragment mMapFragment;

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr__fantasy);
        ButterKnife.bind(this);
        initializeView();

        mMapFragment = SupportMapFragment.newInstance();
        mMapFragment.getMapAsync(this);

    }

    private void initializeView() {
        mDetailContainer.setVisibility(View.GONE);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

}
