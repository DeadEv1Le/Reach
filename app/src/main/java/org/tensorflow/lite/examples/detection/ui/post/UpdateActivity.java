package org.tensorflow.lite.examples.detection.ui.post;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import org.tensorflow.lite.examples.detection.MainAcitvity;
import org.tensorflow.lite.examples.detection.R;

public class UpdateActivity extends AppCompatActivity {
//    ImageView updateImage;
//    Button updateButton;
//    EditText updateDesc, updateTitle, updateLang, updatePlaceName;
//    String title, desc, lang, userName, profileImage, placeName;
//    String imageUrl;
//    String key, oldImageURL;
//    Uri uri;
//    DatabaseReference databaseReference;
//    StorageReference storageReference;
//    @SuppressLint("MissingInflatedId")
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_update);
//        updateButton = findViewById(R.id.updateButton);
//        updateDesc = findViewById(R.id.updateDesc);
//        updateImage = findViewById(R.id.updateImage);
//        updateLang = findViewById(R.id.updateLang);
//        updateTitle = findViewById(R.id.updateTitle);
//        updatePlaceName = findViewById(R.id.updatePlaceName);
//        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
//                new ActivityResultContracts.StartActivityForResult(),
//                new ActivityResultCallback<ActivityResult>() {
//                    @Override
//                    public void onActivityResult(ActivityResult result) {
//                        if (result.getResultCode() == Activity.RESULT_OK){
//                            Intent data = result.getData();
//                            uri = data.getData();
//                            updateImage.setImageURI(uri);
//                        } else {
//                            Toast.makeText(UpdateActivity.this, "No Image Selected", Toast.LENGTH_SHORT).show();
//                        }
//                    }
//                }
//        );
//        Bundle bundle = getIntent().getExtras();
//        if (bundle != null){
//            Glide.with(UpdateActivity.this).load(bundle.getString("Image")).into(updateImage);
//            updateTitle.setText(bundle.getString("Title"));
//            updateDesc.setText(bundle.getString("Description"));
//            updateLang.setText(bundle.getString("Language"));
//            key = bundle.getString("Key");
//            oldImageURL = bundle.getString("Image");
//        }
//        databaseReference = FirebaseDatabase.getInstance().getReference("Android Tutorials").child(key);
//        updateImage.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent photoPicker = new Intent(Intent.ACTION_PICK);
//                photoPicker.setType("image/*");
//                activityResultLauncher.launch(photoPicker);
//            }
//        });
//        updateButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                saveData();
//                Intent intent = new Intent(UpdateActivity.this, MainAcitvity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    public void saveData(){
//        if (uri == null) {
//            Toast.makeText(this, "No image selected", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        storageReference = FirebaseStorage.getInstance().getReference().child("Android Images").child(uri.getLastPathSegment());
//        AlertDialog.Builder builder = new AlertDialog.Builder(UpdateActivity.this);
//        builder.setCancelable(false);
//        builder.setView(R.layout.progress_layout);
//        AlertDialog dialog = builder.create();
//        dialog.show();
//        storageReference.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
//            @Override
//            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
//                Task<Uri> uriTask = taskSnapshot.getStorage().getDownloadUrl();
//                while (!uriTask.isComplete());
//                Uri urlImage = uriTask.getResult();
//                imageUrl = urlImage.toString();
//                updateData();
//                dialog.dismiss();
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                dialog.dismiss();
//            }
//        });
//
//    }
//    public void updateData(){
//        FirebaseAuth auth = FirebaseAuth.getInstance();
//        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Tourist Posts");
//
//
//        title = updateTitle.getText().toString().trim();
//        desc = updateDesc.getText().toString().trim();
//        lang = updateLang.getText().toString();
//
//        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot snapshot) {
//                if (snapshot.exists()) {
//                    profileImage = snapshot.child("userImage").getValue(String.class);
//                    userName = snapshot.child("userName").getValue(String.class);
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//                // Handle errors here
//            }
//        });
//
//        placeName = updatePlaceName.getText().toString().trim();
//
//        DataClass dataClass = new DataClass(title, desc, lang, imageUrl, userName, profileImage,placeName);
//        databaseReference.setValue(dataClass).addOnCompleteListener(new OnCompleteListener<Void>() {
//            @Override
//            public void onComplete(@NonNull Task<Void> task) {
//                if (task.isSuccessful()){
//                    StorageReference reference = FirebaseStorage.getInstance().getReferenceFromUrl(oldImageURL);
//                    reference.delete();
//                    Toast.makeText(UpdateActivity.this, "Updated", Toast.LENGTH_SHORT).show();
//                    finish();
//                }
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception e) {
//                Toast.makeText(UpdateActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
//}
}