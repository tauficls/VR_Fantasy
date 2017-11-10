package com.taufic.vr_fantasy.Activity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.taufic.vr_fantasy.BaseApi.ApiClass.Destination;
import com.taufic.vr_fantasy.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetailActivity extends AppCompatActivity {

    @BindView(R.id.see_AR_button)
    Button mSeeAR_Button;
    @BindView(R.id.booking_button)
    Button mBookButton;
    @BindView(R.id.name)
    TextView mName;
    @BindView(R.id.place)
    TextView mPlace;
    @BindView(R.id.description)
    TextView mDescription;
    @BindView(R.id.image)
    ImageView mImage;
    @BindView(R.id.type)
    TextView mType;
    
    private static final String DESTINATION = "destination";

    private Destination destination;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        destination = (Destination) getIntent().getSerializableExtra(DESTINATION);
        
        if (destination != null) {
            mName.setText(destination.getName());
            mPlace.setText(destination.getAddress());
            mDescription.setText(destination.getDescription());
            Glide.with(this)
                .load(destination.getImage())
                .into(mImage);
            mType.setText(destination.getType());
//            mType.setBackgroundColor(destination.type);
        }

        mBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWeb(destination.getBooking());
            }
        });
        mSeeAR_Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToWeb("http://" + destination.getLink());
            }
        });

    }

    public static void startThisActivitiy(Activity activity, Destination destination) {
        activity.startActivity(new Intent(activity, DetailActivity.class)
            .putExtra(DESTINATION, destination));
    }

    private void goToWeb(String link) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));;
        startActivity(intent);
    }
}
