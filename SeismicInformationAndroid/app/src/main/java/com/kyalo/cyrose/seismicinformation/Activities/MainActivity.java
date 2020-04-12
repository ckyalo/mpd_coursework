package com.kyalo.cyrose.seismicinformation.Activities;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toolbar;

import com.kyalo.cyrose.seismicinformation.Adapters.SeismicAdapter;
import com.kyalo.cyrose.seismicinformation.Data.Earthquakes;
import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;
import com.kyalo.cyrose.seismicinformation.R;
import com.kyalo.cyrose.seismicinformation.Services.RssReader;
import com.kyalo.cyrose.seismicinformation.Services.XmlParser;

import java.util.ArrayList;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class MainActivity extends Activity {

    XmlParser xmlParser = new XmlParser();
    RssReader rssReader = new RssReader();
    AsyncParser asyncParser = new AsyncParser();


    RecyclerView recyclerView;
    RecyclerView.Adapter mAdapter;
    RecyclerView.LayoutManager layoutManager;
    ProgressBar progressBar;
    ArrayList<EarthquakeOccurence> earthquakeOccurences;

    Handler handler = new Handler(Looper.getMainLooper());

    ImageView searchIcon;

    Context context;


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

//        Toolbar toolbar = findViewById(R.id.toolbar);
//
//        setActionBar(toolbar);



        getActionBar().setTitle("Seismology");





        context = this;

        recyclerView =  findViewById(R.id.seismicList);
        progressBar = findViewById(R.id.loading);
//        searchIcon = findViewById(R.id.searchIcon);

        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        asyncParser.execute();


//        searchIcon.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent intent = new Intent(context, SearchView.class);
//                startActivity(intent);
//            }
//        });

    }


    private class  AsyncParser extends AsyncTask<Void, Void, Void> {

        @Override
        protected Void doInBackground(Void... voids) {
            earthquakeOccurences = xmlParser.parseXML(rssReader.fetchRSS());

            Earthquakes.getInstance().setEarthquakeOccurences(earthquakeOccurences);
            mAdapter = new SeismicAdapter( earthquakeOccurences, MainActivity.this );

            handler.post(new Runnable() {
                @Override
                public void run() {
                    recyclerView.setAdapter(mAdapter);
                }
            });

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            handler.post(new Runnable() {
                @Override
                public void run() {
                    progressBar.setVisibility(View.INVISIBLE);
                }
            });

        }


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return   true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.search_btn:
                Intent intent = new Intent(MainActivity.this, SearchView.class);
                startActivity(intent);

                return true;

            default:

                    return super.onOptionsItemSelected(item);
        }

//        return super.onOptionsItemSelected(item);
    }
}



