package org.tensorflow.lite.examples.detection.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import org.tensorflow.lite.examples.detection.MainAcitvity;
import org.tensorflow.lite.examples.detection.R;


public class MountainInfoActivity extends AppCompatActivity {
    ImageView cameraIcon, homeIcon, hikeIcon, mapIcon;
    ImageView tourImage;
    ImageView arrowBack;

    ImageView mountainInfoImage;

    TextView mountainInfoName;
    TextView difficulty, elevation, distance;
    TextView description;

    TextView firstHike, placeName, mountainType;

    MountModel mountModel;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ararat_info_fragment);

        arrowBack = findViewById(R.id.arrow_back_to_main);
        arrowBack.setClickable(true);

        mountainInfoImage = findViewById(R.id.imageView2);
        mountainInfoName = findViewById(R.id.mountNameInfo);
        difficulty = findViewById(R.id.dificulty);
        elevation = findViewById(R.id.elevation);
        distance = findViewById(R.id.distance2);
        description = findViewById(R.id.descri);
        firstHike = findViewById(R.id.firstHike);
        placeName = findViewById(R.id.placeName);
        mountainType = findViewById(R.id.mountainType);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mountModel = bundle.getParcelable("mountain");

            if (mountModel != null) {
                Glide.with(this).load(mountModel.getInfoImage()).into(mountainInfoImage);
                mountainInfoName.setText(mountModel.getMountainName());
                difficulty.setText(mountModel.getDifficulty());
                description.setText(mountModel.getDescription());
                distance.setText(mountModel.getDistance());
                elevation.setText(String.valueOf(mountModel.getElevation()));
                firstHike.setText(mountModel.getFirstHike());
                placeName.setText(mountModel.getLocation());
                mountainType.setText(mountModel.getMountainType());

            }

        }

    }




    public void onReturnClickBtn(View view) {
        Log.d("Barev", "onReturnClickBtn: barev aper");
        Intent myIntent = new Intent(MountainInfoActivity.this, MainAcitvity.class);
        MountainInfoActivity.this.startActivity(myIntent);
    }

}
