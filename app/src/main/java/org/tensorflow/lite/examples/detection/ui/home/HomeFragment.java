package org.tensorflow.lite.examples.detection.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.dataBase.MountainDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MountainAdapter adapter;

     List<MountModel> mountainList;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        mountainList = new ArrayList<>();
        // Initialize the RecyclerView and its adapter
        recyclerView = view.findViewById(R.id.recyclerViewMount);
        adapter = new MountainAdapter(new ArrayList<>(), getActivity()); // Pass an empty list for now
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Retrieve mountain information from Firebase
        DatabaseReference mountainRef = FirebaseDatabase.getInstance().getReference().child("Mountains Info");
        mountainRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                mountainList.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String mountainName = dataSnapshot.child("mountainName").getValue(String.class);
                    String mountainType = dataSnapshot.child("mountainType").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    String difficulty = dataSnapshot.child("difficulty").getValue(String.class);
                    String distance = dataSnapshot.child("distance").getValue(String.class);
                    int elevation = dataSnapshot.child("elevation").getValue(Integer.class);

                    String itemImage = dataSnapshot.child("itemImage").getValue(String.class);
                    String infoImage = dataSnapshot.child("infoImage").getValue(String.class);

                    String firstHike = dataSnapshot.child("firstHike").getValue(String.class);


                    Log.d("fuck", String.valueOf(firstHike));


                    String location = dataSnapshot.child("location").getValue(String.class);


                    MountModel mountain = new MountModel(mountainName, mountainType, description, difficulty,
                            distance, elevation, firstHike, location, itemImage, infoImage);
                    mountainList.add(mountain);

                }
                adapter.setMountainList(mountainList);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle database error
            }
        });

        return view;
    }
}




