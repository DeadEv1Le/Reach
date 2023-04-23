package org.tensorflow.lite.examples.detection;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import org.tensorflow.lite.examples.detection.databinding.MainResBinding;


public class MountainInfoActivity extends AppCompatActivity {
    ImageView cameraIcon, homeIcon, hikeIcon, mapIcon;
    ImageView tourImage;
    ImageView arrowBack;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ararat_info_fragment);

        arrowBack = findViewById(R.id.arrow_back_to_main);
        arrowBack.setClickable(true);



    }



    public void onReturnClickBtn(View view) {
        Log.d("Barev", "onReturnClickBtn: barev aper");
        Intent myIntent = new Intent(MountainInfoActivity.this, MainAcitvity.class);
        MountainInfoActivity.this.startActivity(myIntent);
    }

}
