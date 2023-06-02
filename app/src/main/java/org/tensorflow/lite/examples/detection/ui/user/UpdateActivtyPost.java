package org.tensorflow.lite.examples.detection.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.tensorflow.lite.examples.detection.R;

public class UpdateActivtyPost extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICKER = 1;
    private EditText topicEditText;
    private EditText descEditText;
    private EditText langEditText;
    private EditText placeNameEditText , updateLang;
    private Button saveButton;

    private DatabaseReference mDatabaseRef;
    private String key;
    private String imageUrl;
    private ImageView uploadimg;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Toast.makeText(this, "BAREV ", Toast.LENGTH_SHORT).show();

        topicEditText = findViewById(R.id.updateTopic);
        descEditText = findViewById(R.id.updateDesc);
        langEditText = findViewById(R.id.updateLang);
        placeNameEditText = findViewById(R.id.updatePlaceName);
        saveButton = findViewById(R.id.updatesaveButton);


        uploadimg = findViewById(R.id.updateImage);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            topicEditText.setText(bundle.getString("Title"));
            descEditText.setText(bundle.getString("Description"));
            langEditText.setText(bundle.getString("Contacts"));
            placeNameEditText.setText(bundle.getString("PlaceName"));

            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");

            Glide.with(this).load(imageUrl).into(uploadimg);

        }


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Tourist Posts");


        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = topicEditText.getText().toString().trim();
                String desc = descEditText.getText().toString().trim();
                String lang = langEditText.getText().toString().trim();
                String placeName = placeNameEditText.getText().toString().trim();


                // Update the post details in the database
                mDatabaseRef.child(key).child("dataTitle").setValue(topic);
                mDatabaseRef.child(key).child("dataDesc").setValue(desc);
                mDatabaseRef.child(key).child("contacts").setValue(lang);
                mDatabaseRef.child(key).child("placeName").setValue(placeName);

                Toast.makeText(UpdateActivtyPost.this, "Post updated successfully", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        uploadimg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Open an image picker
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                startActivityForResult(photoPicker, REQUEST_IMAGE_PICKER);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_IMAGE_PICKER && resultCode == RESULT_OK) {
            if (data != null) {
                Uri selectedImageUri = data.getData();
                // Process the selected image URI
                // Here, you can update the image view and save the new image URL to the database
                uploadimg.setImageURI(selectedImageUri);

                // Save the new image URL to the database
                String newImageUrl = selectedImageUri.toString();
                mDatabaseRef.child(key).child("imageUrl").setValue(newImageUrl);
            }
        }
    }


}
