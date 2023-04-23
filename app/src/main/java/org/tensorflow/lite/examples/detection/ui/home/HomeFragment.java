package org.tensorflow.lite.examples.detection.ui.home;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import org.tensorflow.lite.examples.detection.R;
import org.tensorflow.lite.examples.detection.dataBase.MountainDatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private RecyclerView recyclerView;
    private MountainAdapter adapter;

    private MountainDatabaseHelper dbHelper;

    public HomeFragment() {
        // Required empty public constructor
    }

    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.home_fragment, container, false);

        // Initialize the RecyclerView and its adapter
        RecyclerView recyclerView = view.findViewById(R.id.recyclerView);

        recyclerView = view.findViewById(R.id.recyclerViewMount);
        adapter = new MountainAdapter(getSampleMountainList());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        // Initialize the database helper
//        dbHelper = new MountainDatabaseHelper(getContext());

        // Load the data from the database



        return view;
    }

    private List<Mountain> getSampleMountainList() {
        List<Mountain> mountains = new ArrayList<>();
        mountains.add(new Mountain("ARARAT", 5137, R.drawable.ararat));
        mountains.add(new Mountain("ARAGATS", 4095, R.drawable.aragats));
        mountains.add(new Mountain("KAPUTJUGH", 3906, R.drawable.kaputjugh));
        mountains.add(new Mountain("KHUSTUP", 3206, R.drawable.xustup1));
        mountains.add(new Mountain("ISHXANASAR", 3552, R.drawable.isxhanasar));
        mountains.add(new Mountain("AJDAHAK", 3597, R.drawable.ajdahak));
        mountains.add(new Mountain("ARAILER", 2575, R.drawable.arailer));
        mountains.add(new Mountain("ARTAVAZ", 2929, R.drawable.artavaz));

//        mountains.add(new Mountain("Mount Kilimanjaro", 5895, R.drawable.mount_kilimanjaro));
        return mountains;
    }





}
