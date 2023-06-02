package org.tensorflow.lite.examples.detection.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.lite.examples.detection.R;

public class UpdateUser extends AppCompatActivity {
    private static final int REQUEST_IMAGE_PICKER = 1;
    private EditText userNameUpdate;
    private EditText updateEmail;
    private EditText updateCountry;
    private EditText placeNameEditText , updateLang;
    private Button saveButton;

    private DatabaseReference mDatabaseRef;
    private String key;
    private String imageUrl;
    private ImageView uploadimg;
    String postUsername;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_user);


        Toast.makeText(this, "BAREV ", Toast.LENGTH_SHORT).show();

        userNameUpdate = findViewById(R.id.updateUserName);
        updateEmail = findViewById(R.id.updateEmail);
        updateCountry = findViewById(R.id.updateCountry);
        uploadimg = findViewById(R.id.updateuserImage);

        saveButton = findViewById(R.id.saveButton);



        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {

            userNameUpdate.setText(bundle.getString("UserName"));
            updateEmail.setText(bundle.getString("Email"));
            updateCountry.setText(bundle.getString("Country"));
            imageUrl = bundle.getString("Image");




            Glide.with(this).load(imageUrl).into(uploadimg);

        }


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Users");

        FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        String uid = currentUser.getUid();

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String topic = userNameUpdate.getText().toString().trim();
                String desc = updateEmail.getText().toString().trim();
                String lang = updateCountry.getText().toString().trim();

                DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Tourist Posts");
                ValueEventListener valueEventListener = new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                            String userName = snapshot.child("userName").getValue(String.class);
                            if (bundle.getString("UserName").equals(userName)) {
                                snapshot.getRef().child("userName").setValue(topic);
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Handle potential errors
                        Log.e("TAG", "Error retrieving data", databaseError.toException());
                    }
                };

                postsRef.addListenerForSingleValueEvent(valueEventListener);

                // Update the post details in the database
                mDatabaseRef.child(uid).child("username").setValue(topic);

                mDatabaseRef.child(uid).child("email").setValue(desc);
                mDatabaseRef.child(uid).child("name").setValue(lang);


                Toast.makeText(UpdateUser.this, "User updated successfully", Toast.LENGTH_SHORT).show();
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
                mDatabaseRef.child(key).child("profileImageUrl").setValue(newImageUrl);
            }
        }
    }
}
