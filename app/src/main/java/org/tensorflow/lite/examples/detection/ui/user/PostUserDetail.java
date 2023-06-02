package org.tensorflow.lite.examples.detection.ui.user;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.clans.fab.FloatingActionButton;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import org.tensorflow.lite.examples.detection.MainAcitvity;
import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.ui.post.UpdateActivity;

public class PostUserDetail extends AppCompatActivity {

    TextView detailDesc, detailTitle, detailCategory, detailUserName, detailPlaceName, detilContact;
    ImageView detailImage, detailProfileImage, detailUserAddedPostDetailPageImage;
    FloatingActionButton deleteButton, editButton;
    String key = "";
    String imageUrl = "";
    DatabaseReference mDatabaseRef;

    String profileImage;

    ImageView rollback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState)   {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_profile);

        detailDesc = findViewById(R.id.detailDesc);
        detailImage = findViewById(R.id.detailImage);
        detailTitle = findViewById(R.id.detailTitle);
        deleteButton = findViewById(R.id.deleteButton);
        editButton = findViewById(R.id.editButton);
        detailCategory = findViewById(R.id.postCategory);
        detailUserName = findViewById(R.id.detailUserName);
        detailUserAddedPostDetailPageImage = findViewById(R.id.userAddedPostDetailPageImage);
        detailPlaceName = findViewById(R.id.detailPlaceName);
        detilContact = findViewById(R.id.detailContact);

        rollback = findViewById(R.id.rollback);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null){
            detailDesc.setText(bundle.getString("Description"));
            detailTitle.setText(bundle.getString("Title"));
            detailCategory.setText(bundle.getString("Category"));
            detailPlaceName.setText(bundle.getString("PlaceName"));
            detilContact.setText(bundle.getString("Contacts"));
            key = bundle.getString("Key");
            imageUrl = bundle.getString("Image");
            Glide.with(this).load(imageUrl).into(detailImage);
        }


        rollback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), MainAcitvity.class));
            }
        });

        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();


        mDatabaseRef = FirebaseDatabase.getInstance().getReference("Tourist Posts").child(key);


        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String userName = snapshot.child("userName").getValue(String.class);
                    detailUserName.setText(userName);
                    String postedUserImage = snapshot.child("userImage").getValue(String.class);
                    Glide.with(getApplicationContext())
                            .load(postedUserImage)
                            .into(detailUserAddedPostDetailPageImage);
                    Toast.makeText(org.tensorflow.lite.examples.detection.ui.user.PostUserDetail.this, detailUserName.getText(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(org.tensorflow.lite.examples.detection.ui.user.PostUserDetail.this, "Error: " + error.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        detailProfileImage = findViewById(R.id.userPostDetailPageImage);
        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    profileImage = snapshot.child("profileImageUrl").getValue(String.class);

                    Glide.with(getApplicationContext())
                            .load(profileImage)
                            .into(detailProfileImage);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Tourist Posts");
                FirebaseStorage storage = FirebaseStorage.getInstance();

                StorageReference storageReference = storage.getReferenceFromUrl(imageUrl);
                storageReference.delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        reference.child(key).removeValue();
                        Toast.makeText(org.tensorflow.lite.examples.detection.ui.user.PostUserDetail.this, "Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), MainAcitvity.class));
                        finish();
                    }
                });
            }
        });

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(org.tensorflow.lite.examples.detection.ui.user.PostUserDetail.this, UpdateActivtyPost.class)
                        .putExtra("Title", detailTitle.getText().toString())
                        .putExtra("Description", detailDesc.getText().toString())
                        .putExtra("Category", detailCategory.getText().toString())
                        .putExtra("Image", imageUrl)
                        .putExtra("PlaceName", detailPlaceName.getText().toString())
                        .putExtra("Contacts", detilContact.getText().toString())
                        .putExtra("Key", key);
                startActivity(intent);
            }
        });
    }
}