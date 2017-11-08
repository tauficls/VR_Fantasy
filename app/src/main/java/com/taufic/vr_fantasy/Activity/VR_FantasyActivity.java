package com.taufic.vr_fantasy.Activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.taufic.vr_fantasy.R;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vr__fantasy);
        ButterKnife.bind(this);
        initializeView();

        mMapFragment = SupportMapFragment.newInstance();
        mMapFragment.getMapAsync(this);

    }

    private void retrieveData() {

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
    public void onMapReady(GoogleMap googleMap) {
        googleMap = googleMap;

        // Add a marker in Sydney, Australia, and move the camera.
        LatLng sydney = new LatLng(-34, 151);
        googleMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }

    private Marker createMarker(double latitude, double longitude, String title, int iconResID) {
        return googleMap.addMarker(new MarkerOptions()
            .position(new LatLng(new LatLng(latitude, longitude)))
            .anchor(0.5f, 0.5f)
            .title(title)
            .icon(BitmapDescriptorFactory.fromResource(iconResID)));
    }

}
