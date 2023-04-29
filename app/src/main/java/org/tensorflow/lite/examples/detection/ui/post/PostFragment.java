package org.tensorflow.lite.examples.detection.ui.post;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;

import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.imageview.ShapeableImageView;
import com.google.android.material.shape.CornerFamily;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.tensorflow.lite.examples.detection.R;

import java.util.ArrayList;
import java.util.List;

public class PostFragment extends Fragment {
    FloatingActionButton fab;
    DatabaseReference databaseReference;
    ValueEventListener eventListener;
    RecyclerView recyclerView;
    List<DataClass> dataList;
    MyAdapter adapter;
    SearchView searchView;
    AlertDialog dialog;
    Button araratFilter, aragatsFilter, ajdahakFilter, khustupFilter, kaputjughFilter, ishxanasarFilter, araFilter, artavazFilter;

    String username, profileImage;

    TextView welcomeText;
    @SuppressLint("MissingInflatedId")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.post_acticity_new, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        fab = view.findViewById(R.id.fab);
        searchView = view.findViewById(R.id.search);
        searchView.clearFocus();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerView.setLayoutManager(gridLayoutManager);
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(false);
        builder.setView(R.layout.progress_layout);
        dialog = builder.create();
        dialog.show();
        dataList = new ArrayList<>();
        adapter = new MyAdapter(getActivity(), dataList);
        recyclerView.setAdapter(adapter);
        databaseReference = FirebaseDatabase.getInstance().getReference("Tourist Posts");
        dialog.show();

        welcomeText = view.findViewById(R.id.welcome_greed);


        //================= Filter Buttons ==================
        araratFilter = view.findViewById(R.id.ararat);
        aragatsFilter = view.findViewById(R.id.aragats);
        ajdahakFilter = view.findViewById(R.id.ajdahak);
        khustupFilter = view.findViewById(R.id.khustup);
        kaputjughFilter = view.findViewById(R.id.kaputjugh);
        ishxanasarFilter = view.findViewById(R.id.ishxanasar);
        araFilter = view.findViewById(R.id.araLer);
        artavazFilter = view.findViewById(R.id.artavazFilter);



        araratFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = araratFilter.getText().toString();
                araratFilter.setTextColor(R.color.black);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        aragatsFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = aragatsFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.black);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        ajdahakFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = ajdahakFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.black);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        khustupFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = khustupFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.black);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        kaputjughFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = kaputjughFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.black);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
       ishxanasarFilter .setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = ishxanasarFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.black);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        araFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = araFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.black);
                artavazFilter.setTextColor(R.color.light_gray);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });
        artavazFilter.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {
                @SuppressLint("ResourceType")
                String text = artavazFilter.getText().toString();
                araratFilter.setTextColor(R.color.light_gray);
                aragatsFilter.setTextColor(R.color.light_gray);
                ajdahakFilter.setTextColor(R.color.light_gray);
                khustupFilter.setTextColor(R.color.light_gray);
                kaputjughFilter.setTextColor(R.color.light_gray);
                ishxanasarFilter.setTextColor(R.color.light_gray);
                araFilter.setTextColor(R.color.light_gray);
                artavazFilter.setTextColor(R.color.black);

                ArrayList<DataClass> searchList = new ArrayList<>();
                for (DataClass dataClass: dataList){
                    if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase()) || dataClass.getPlaceName().toLowerCase().contains(text.toLowerCase())){

                        searchList.add(dataClass);
                    }
                }
                Toast.makeText(getContext(), text, Toast.LENGTH_SHORT).show();
                adapter.searchDataList(searchList);
            }
        });





        eventListener = databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                dataList.clear();
                for (DataSnapshot itemSnapshot: snapshot.getChildren()){
                    DataClass dataClass = itemSnapshot.getValue(DataClass.class);
                    dataClass.setKey(itemSnapshot.getKey());
                    dataList.add(dataClass);
                }
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                dialog.dismiss();
            }
        });




        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                searchList(newText);
                return true;
            }
        });


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UploadActivity.class);
                startActivity(intent);
            }
        });
        FirebaseAuth auth = FirebaseAuth.getInstance();
        String uid = auth.getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference().child("Users").child(uid);

        userRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    username = snapshot.child("username").getValue(String.class);
                    profileImage = snapshot.child("profileImageUrl").getValue(String.class);

                    // You can use the `userName` variable here
                    welcomeText.setText("Welcome! \n" + username);
                    Glide.with(requireContext())
                            .load(profileImage)
                            .into((ImageView) view.findViewById(R.id.userPostPageImage));

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle errors here
            }
        });

        return view;
    }


    public void searchList(String text){
        ArrayList<DataClass> searchList = new ArrayList<>();
        for (DataClass dataClass: dataList){
            if (dataClass.getDataTitle().toLowerCase().contains(text.toLowerCase())){
                searchList.add(dataClass);
            }
        }
        adapter.searchDataList(searchList);
    }
}