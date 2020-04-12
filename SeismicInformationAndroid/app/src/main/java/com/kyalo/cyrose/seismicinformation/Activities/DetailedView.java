package com.kyalo.cyrose.seismicinformation.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.fragment.app.FragmentActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kyalo.cyrose.seismicinformation.Data.Earthquakes;
import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;
import com.kyalo.cyrose.seismicinformation.R;

import java.text.SimpleDateFormat;
import java.util.Locale;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class DetailedView extends FragmentActivity implements OnMapReadyCallback {

    TextView txtLocation, txtDate, txtLong, txtLat, txtMag, txtDepth, txtTime;

    GoogleMap mMap;

    EarthquakeOccurence selectedEarthquake;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setTitle("Detailed View");

        txtDate = findViewById(R.id.txtDate);
        txtDepth = findViewById(R.id.txtDepth);
        txtLocation = findViewById(R.id.txtLocation);
        txtLong = findViewById(R.id.txtLong);
        txtLat = findViewById(R.id.txtLat);
        txtMag = findViewById(R.id.txtMag);
        txtTime = findViewById(R.id.txtTime);


        Intent intent = getIntent();

        int selected =  intent.getIntExtra("Selected", 0);

        selectedEarthquake = Earthquakes.getInstance().getEarthQuake(selected);

        txtLocation.setText(selectedEarthquake.getLocation());

        txtMag.setText(String.valueOf( selectedEarthquake.getMagnitude()));

        txtLat.setText(String.valueOf(selectedEarthquake.getLatitude()));

        txtLong.setText(String.valueOf(selectedEarthquake.getLongitude()));

        txtDepth.setText(String.valueOf(selectedEarthquake.getDepth()));

        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm:ss a", Locale.ENGLISH);
        SimpleDateFormat dateFormat = new SimpleDateFormat("E, dd MMM yyyy", Locale.ENGLISH);

        txtTime.setText(timeFormat.format(selectedEarthquake.getDate()));
        txtDate.setText(dateFormat.format(selectedEarthquake.getDate()));


        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

        getWindow().getDecorView().setBackgroundColor(Color.parseColor(selectedEarthquake.getColor()));
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
       mMap = googleMap;

       mMap.addMarker(new MarkerOptions().position(selectedEarthquake.getLatLng()).title(selectedEarthquake.getLocation()));
       mMap.addMarker(new MarkerOptions().position(SearchView.mauritusLocation).alpha(0.2f).title("Mauritius"));

       for (EarthquakeOccurence occurence: Earthquakes.getInstance().getEarthquakes()) {
           mMap.addMarker(new MarkerOptions().position(occurence.getLatLng()).alpha(0.5f).title(occurence.getLocation()));

       }


       mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(selectedEarthquake.getLatLng(), 3));
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("acking up", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
