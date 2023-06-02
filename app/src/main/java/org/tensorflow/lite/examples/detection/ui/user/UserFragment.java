package org.tensorflow.lite.examples.detection.ui.user;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
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

import org.tensorflow.lite.examples.detection.R;


import org.tensorflow.lite.examples.detection.databinding.FragmentUserBinding;
import org.tensorflow.lite.examples.detection.ui.sign.LoginActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class UserFragment extends Fragment {
    Uri uri;
    AlertDialog dialog;
    private FragmentUserBinding binding; // Add binding variable
    private FirebaseStorage storage;
    private DatabaseReference database;

    private ImageView logoutBtn;

    private RecyclerView recyclerView;
    private UserAdapter userAdapter;
    private List<TouristPost> postsList;
    String userImage;
    String username;
    public UserFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = Objects.requireNonNull(auth.getCurrentUser()).getUid();

        storage = FirebaseStorage.getInstance();
        database = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);
        androidx.appcompat.app.AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        dialog = builder.create();
        dialog.show();

        recyclerView = binding.recyclerViewProfile;
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        postsList = new ArrayList<>();
        userAdapter = new UserAdapter(postsList, getActivity());
        recyclerView.setAdapter(userAdapter);

        // Get current user's username
        String currentUserId = auth.getCurrentUser().getUid();
        DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Tourist Posts");





        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        usersRef.child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.getValue(String.class);
                    username = userName;

                    // Here, username has a valid value, so you can proceed with the posts retrieval
                    postsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            postsList.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                TouristPost post = snapshot.getValue(TouristPost.class);
                                post.setKey(snapshot.getKey());

                                if (post.getUserName().equals(username)) {
                                    postsList.add(post);
                                }
                            }
                            userAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle error
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });







        binding.logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Clear saved credentials from SharedPreferences
                SharedPreferences sharedPreferences = requireActivity().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.remove("email");
                editor.remove("password");
                editor.apply();

                // Start LoginActivity
                Intent intent = new Intent(requireContext(), LoginActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                requireActivity().finish();
            }
        });


        ActivityResultLauncher<Intent> activityResultLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                new ActivityResultCallback<ActivityResult>() {
                    @Override
                    public void onActivityResult(ActivityResult result) {
                        if (result.getResultCode() == Activity.RESULT_OK){
                            Intent data = result.getData();
                            uri = data.getData();
                            binding.userProfileImage.setImageURI(uri);

                            StorageReference storageRef = storage.getReference();
                            StorageReference imageRef = storageRef.child("Userimages/" + uid + "/profile.jpg");
                            imageRef.putFile(uri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                @Override
                                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                                    imageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                                        @Override
                                        public void onSuccess(Uri downloadUrl) {
                                            // Save the download URL to the Firebase Realtime Database
                                            database.child("profileImageUrl").setValue(downloadUrl.toString());
                                        }
                                    });
                                }
                            });
                        } else {
                            Toast.makeText(getContext().getApplicationContext(), "No Image Selected", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
        );

        binding.userProfileImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent photoPicker = new Intent(Intent.ACTION_PICK);
                photoPicker.setType("image/*");
                activityResultLauncher.launch(photoPicker);
            }
        });





        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (!isAdded() || getContext() == null) {
//                    Toast.makeText(getActivity(), String.valueOf(isAdded()), Toast.LENGTH_SHORT).show();
                    return;
                }
                if (dataSnapshot.exists()) {
                    // Get the user's email
                    String email = dataSnapshot.child("email").getValue(String.class);
                    binding.proffileEmail.setText(email);
                    // Get the user's name
                    String name = dataSnapshot.child("name").getValue(String.class);
                    binding.profileUserCountry.setText(name);
                    // Get the user's profile image URL
                    String profileImageUrl = dataSnapshot.child("profileImageUrl").getValue(String.class);
                    userImage = profileImageUrl;
                    // Get the user's username
                    if (profileImageUrl == null) {
                        binding.userProfileImage.setImageResource(R.drawable.uploadimg);
                    } else {
                        Glide.with(requireContext())
                                .load(profileImageUrl)
                                .into(binding.userProfileImage);
                    }
                    String username = dataSnapshot.child("username").getValue(String.class);
                    binding.profileUserName.setText(username);
                    // Do something with the retrieved data
                    // ...
                    dialog.dismiss();
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle errors here
            }
        });

        binding.editUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), UpdateUser.class);
                intent.putExtra("UserName", binding.profileUserName.getText().toString());
                intent.putExtra("Email", binding.proffileEmail.getText().toString());
                intent.putExtra("Country", binding.profileUserCountry.getText().toString());
                intent.putExtra("Image", userImage);

                startActivity(intent);
            }
        });


        return binding.getRoot();
    }

    @Override
    public void onResume() {
        super.onResume();
        // Get current user's username
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String currentUserId = auth.getCurrentUser().getUid();
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users").child(currentUserId);
        DatabaseReference postsRef = FirebaseDatabase.getInstance().getReference().child("Tourist Posts");
        usersRef.child("username").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    String userName = dataSnapshot.getValue(String.class);
                    username = userName;

                    // Here, username has a valid value, so you can proceed with the posts retrieval
                    postsRef.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            postsList.clear();
                            for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                                TouristPost post = snapshot.getValue(TouristPost.class);
                                post.setKey(snapshot.getKey());

                                if (post.getUserName().equals(username)) {
                                    postsList.add(post);
                                }
                            }
                            userAdapter.notifyDataSetChanged();
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            // Handle error
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                // Handle error
            }
        });

    }
}


