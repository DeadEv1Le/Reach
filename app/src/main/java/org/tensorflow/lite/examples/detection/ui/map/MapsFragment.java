package org.tensorflow.lite.examples.detection.ui.map;


import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.MapView;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;

import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;


import org.tensorflow.lite.examples.detection.R;

public class MapsFragment extends Fragment implements OnMapReadyCallback {
    private MapView mapView;
    private GoogleMap googleMap;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.activity_maps, container, false);

        mapView = rootView.findViewById(R.id.mapContainer);
        mapView.onCreate(savedInstanceState);

        mapView.getMapAsync(this);

        return rootView;
    }

    @Override
    public void onMapReady(GoogleMap map) {
        googleMap = map;

        // Create LatLng objects for the mountains
        LatLng mountArarat = new LatLng(39.6997, 44.2984);
        LatLng aragats = new LatLng(40.52539856723772, 44.193545707322);
        LatLng ajdahak = new LatLng(40.22610773990947, 44.94748172661121);
        LatLng khustup = new LatLng(39.138178673039725, 46.330332601256984);
        LatLng araiLer = new LatLng(40.40290400097858, 44.469171430004806);
        LatLng gutanasar = new LatLng(40.36950596560745, 44.68863831209622);
        LatLng kaputjugh = new LatLng(39.15969393455372, 46.008722516876794);
        LatLng hatis = new LatLng(40.30838185522866, 44.727075489425346);

        // Add markers for the mountains
        googleMap.addMarker(new MarkerOptions()
                .position(mountArarat)
                .title("Mount Ararat"));
        googleMap.addMarker(new MarkerOptions()
                .position(aragats)
                .title("Mount Aragats"));
        googleMap.addMarker(new MarkerOptions()
                .position(ajdahak)
                .title("Mount Ajdahak"));
        googleMap.addMarker(new MarkerOptions()
                .position(khustup)
                .title("Mount Khustup"));
        googleMap.addMarker(new MarkerOptions()
                .position(araiLer)
                .title("Mount Arai Ler"));
        googleMap.addMarker(new MarkerOptions()
                .position(gutanasar)
                .title("Mount Gutanasar"));
        googleMap.addMarker(new MarkerOptions()
                .position(kaputjugh)
                .title("Mount Kaputjugh"));
        googleMap.addMarker(new MarkerOptions()
                .position(hatis)
                .title("Mount Hatis"));

        // Move the camera to a specific mountain (e.g., Aragats)
        googleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(aragats, 10f));

        // Add additional map customization code here if needed
    }


    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        mapView.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mapView.onDestroy();
    }
}
