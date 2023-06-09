package org.tensorflow.lite.examples.detection;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;


import com.google.android.material.floatingactionbutton.FloatingActionButton;


import org.tensorflow.lite.examples.detection.databinding.MainResBinding;
import org.tensorflow.lite.examples.detection.ui.home.HomeFragment;
import org.tensorflow.lite.examples.detection.ui.map.MapsFragment;

import org.tensorflow.lite.examples.detection.ui.post.PostFragment;
import org.tensorflow.lite.examples.detection.ui.user.UserFragment;


public class MainAcitvity extends AppCompatActivity  {

    RelativeLayout araratImage, aragatsmount;

    private MainResBinding binding;

    FloatingActionButton cameraOpenButton;




//    int[] mountainInfoName = {R.array.mountain_names};
    @SuppressLint({"MissingInflatedId", "ResourceType"})
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = MainResBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new PostFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new PostFragment());
                    break;
                case R.id.shorts:

                    replaceFragment(new HomeFragment());
                    break;
                case R.id.subscriptions:
                    replaceFragment(new MapsFragment());
                    break;
                case R.id.library:
                    replaceFragment(new UserFragment());
                    break;
            }
            return true;
        });

        cameraOpenButton = findViewById(R.id.cameraButton);

        cameraOpenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainAcitvity.this, DetectorActivity.class));
            }
        });



        // ============ Tour Image Shape =========
//        tourImage = findViewById(R.drawable.walk);
//
//
// ============ Mountain Images ==========

        araratImage = findViewById(R.id.araratImageContainer);
//
//
//
//        getSupportFragmentManager().beginTransaction().add(R.id.container, new HomeFragment()).commit();
//        homeIcon.setImageResource(R.drawable.home_yellow);




    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }

//    @Override
//    protected void onResume() {
//        super.onResume();
//
//        Log.d("fuck", "onRestart: Activity restarted");
//    }



    // =========== On Ararat Image Click ==============




    @Override
    public void onBackPressed() {
        //ignored
    }
}
