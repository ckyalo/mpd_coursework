package com.kyalo.cyrose.seismicinformation.Activities;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.kyalo.cyrose.seismicinformation.Data.Earthquakes;
import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;
import com.kyalo.cyrose.seismicinformation.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

import androidx.cardview.widget.CardView;
import androidx.core.app.NavUtils;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class SearchView extends Activity {

    Button fromBtn, toBtn, searchBtn;

    CardView deepCard, magnitudeCard, northCard, westCard,
            eastCard, southCard;

    TextView northTitle, southTitle, eastTitle, westTitle, deepTitle, magnitudeTitle,
            northContent, southContent, eastContent, westContent, deepContent, magnitudeContent,
            noResult;


    Date fromDate, toDate;



    ArrayList<EarthquakeOccurence> earthquakeOccurences = new ArrayList<>();

    public static LatLng mauritusLocation = new LatLng(-20.3434, 57.5522);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_view);

        getActionBar().setDisplayHomeAsUpEnabled(true);

        getActionBar().setTitle("Search");



        fromBtn = findViewById(R.id.fromBtn);
        toBtn = findViewById(R.id.toBtn);
        searchBtn = findViewById(R.id.searchBtn);

        deepCard = findViewById(R.id.deepCard);
        magnitudeCard = findViewById(R.id.magnitudeCard);
        northCard = findViewById(R.id.northCard);
        westCard = findViewById(R.id.westCard);
        eastCard = findViewById(R.id.eastCard);
        southCard = findViewById(R.id.southCard);

        northTitle = findViewById(R.id.northTitle); northContent = findViewById(R.id.northContent);
        southTitle = findViewById(R.id.southTitle); southContent = findViewById(R.id.southContent);
        westTitle = findViewById(R.id.westTitle); westContent = findViewById(R.id.westContent);
        eastTitle = findViewById(R.id.eastTitle); eastContent = findViewById(R.id.eastContent);
        deepTitle = findViewById(R.id.deepTitle); deepContent = findViewById(R.id.deepContent);
        magnitudeContent = findViewById(R.id.magnitudeContent); magnitudeTitle = findViewById(R.id.magnitudeTitle);

        noResult = findViewById(R.id.noResult);



        final DatePickerDialog fromPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                fromDate = new Date(year -1900, month, dayOfMonth);

                fromBtn.setText(new SimpleDateFormat("dd, MMM yyyy", Locale.ENGLISH).format(fromDate));
            }
        }, 2020, 2,1);

        final DatePickerDialog toPicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                toDate = new Date(year -1900, month, dayOfMonth);

                toBtn.setText(new SimpleDateFormat("dd, MMM yyyy", Locale.ENGLISH).format(toDate));
            }
        }, 2020, 2,3);



        fromBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fromPicker.show();
            }
        });

        toBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                toPicker.show();
            }
        });


        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (toDate != null && fromDate != null ) {
                    Log.d("Siku", "Searching");
                    earthquakeOccurences = filterByDate(fromDate, toDate);

                    if (earthquakeOccurences.size() > 0) {
                        toggleVisibility(true);

                        try {
                            final EarthquakeOccurence north = getNearestNortherly(earthquakeOccurences);
                            northTitle.setText(north.getLocation());
                            String distance = String.format(Locale.ENGLISH, "%.2f",distanceOfTwoPoints(north.getLatLng(), mauritusLocation) / 1000);
                            northContent.setText(distance + " km from Maurituis");

                            northCard.setCardBackgroundColor(Color.parseColor(north.getColor()));

                            northCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(north));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            northTitle.setText("No earthquake north of Mauritius");
                        }

                        try {
                            final EarthquakeOccurence east = getNearestEasterly(earthquakeOccurences);
                            eastTitle.setText(east.getLocation());
                            String distance = String.format(Locale.ENGLISH, "%.2f",distanceOfTwoPoints(east.getLatLng(), mauritusLocation) / 1000);
                            eastContent.setText(distance + " km from Maurituis");

                            eastCard.setCardBackgroundColor(Color.parseColor(east.getColor()));

                            eastCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(east));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            eastTitle.setText("No earthquake east of Mauritius");
                        }

                        try {
                            final EarthquakeOccurence west = getNearestWesterly(earthquakeOccurences);
                            westTitle.setText(west.getLocation());
                            String distance = String.format(Locale.ENGLISH, "%.2f",distanceOfTwoPoints(west.getLatLng(), mauritusLocation) / 1000);
                            westContent.setText(distance + " km from Maurituis");

                            westCard.setCardBackgroundColor(Color.parseColor(west.getColor()));

                            westCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(west));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            westTitle.setText("No earthquake west of Mauritius");
                        }

                        try {
                            final EarthquakeOccurence south = getNearestSoutherly(earthquakeOccurences);
                            southTitle.setText(south.getLocation());
                            String distance = String.format(Locale.ENGLISH, "%.2f",distanceOfTwoPoints(south.getLatLng(), mauritusLocation) / 1000);
                            southContent.setText(distance + " km from Maurituis");

                            southCard.setCardBackgroundColor(Color.parseColor(south.getColor()));

                            southCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(south));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            southTitle.setText("No earthquake south of Mauritius");
                        }


                        try {
                            final EarthquakeOccurence deepest = getDeepest(earthquakeOccurences);
                            deepTitle.setText(deepest.getLocation());
                            deepContent.setText("Depth: "+ deepest.getDepth());

                            deepCard.setCardBackgroundColor(Color.parseColor(deepest.getColor()));

                            deepCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(deepest));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            Log.d("Siku", e.getLocalizedMessage());
                        }

                        try {
                            final EarthquakeOccurence magnitude = getLargestMagnitude(earthquakeOccurences);
                            magnitudeTitle.setText(magnitude.getLocation());
                            magnitudeContent.setText("Magnitude: "+ magnitude.getMagnitude());

                            magnitudeCard.setCardBackgroundColor(Color.parseColor(magnitude.getColor()));

                            magnitudeCard.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Intent intent = new Intent(SearchView.this, DetailedView.class);
                                    intent.putExtra("Selected", Earthquakes.getInstance().getIndex(magnitude));
                                    startActivity(intent);
                                }
                            });
                        } catch (NullPointerException e) {
                            Log.d("Siku", e.getLocalizedMessage());
                        }





                    } else {
                        toggleVisibility(false);
                    }



//                    try {
//                        Log.d("Siku North", getNearestNortherly(earthquakeOccurences).getLocation());
//                        Log.d("Siku East", getNearestEasterly(earthquakeOccurences).getLocation());
//                        Log.d("Siku West", getNearestWesterly(earthquakeOccurences).getLocation());
//                        Log.d("Siku South", getNearestSoutherly(earthquakeOccurences).getLocation());
//                    } catch (NullPointerException e) {
//                        Log.d("Siku Error", e.getLocalizedMessage());
//                    }

                } else {
                    Toast.makeText(SearchView.this, "Please enter the from date and to date", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    private void toggleVisibility(boolean show) {
        noResult.setVisibility(show ? View.INVISIBLE : View.VISIBLE);
        deepCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        northCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        westCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        eastCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        southCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
        magnitudeCard.setVisibility(show ? View.VISIBLE : View.INVISIBLE);
    }


    private ArrayList<EarthquakeOccurence> filterByDate(Date fromDate, Date toDate) {
        ArrayList<EarthquakeOccurence> earthquakeOccurences = new ArrayList<>();

        for (EarthquakeOccurence earthquakeOccurence : Earthquakes.getInstance().getEarthquakes()) {

            int fromCompare = earthquakeOccurence.getDate().compareTo(fromDate);

            int toCompare = earthquakeOccurence.getDate().compareTo(toDate);

            if (fromCompare >= 0 && toCompare <= 0) {
                earthquakeOccurences.add(earthquakeOccurence);
            }
        }



        return earthquakeOccurences;
    }

    private EarthquakeOccurence getNearestNortherly(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence nearest = null;
        for (EarthquakeOccurence occurence: earthquakeOccurences) {
           if (nearest == null && occurence.getLatitude() > mauritusLocation.latitude) {
               nearest = occurence;
           } else if (nearest != null && occurence.getLatitude() > mauritusLocation.latitude &&
                   distanceOfTwoPoints(occurence.getLatLng(), mauritusLocation)
                   < distanceOfTwoPoints(nearest.getLatLng(), mauritusLocation)
                   ) {
               nearest = occurence;
           }
        }

        return nearest;
    }

    private EarthquakeOccurence getNearestSoutherly(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence nearest = null;
        for (EarthquakeOccurence occurence: earthquakeOccurences) {
            if (nearest == null && occurence.getLatitude() < mauritusLocation.latitude) {
                nearest = occurence;
            } else if (nearest != null && occurence.getLatitude() < mauritusLocation.latitude &&
                    distanceOfTwoPoints(occurence.getLatLng(), mauritusLocation)
                            < distanceOfTwoPoints(nearest.getLatLng(), mauritusLocation)
                    ) {
                nearest = occurence;
            }
        }

        return nearest;
    }

    private EarthquakeOccurence getNearestEasterly(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence nearest = null;
        for (EarthquakeOccurence occurence: earthquakeOccurences) {
            if (nearest == null && occurence.getLongitude() > mauritusLocation.longitude) {
                nearest = occurence;
            } else if (nearest != null && occurence.getLongitude() > mauritusLocation.longitude &&
                    distanceOfTwoPoints(occurence.getLatLng(), mauritusLocation)
                            < distanceOfTwoPoints(nearest.getLatLng(), mauritusLocation)
                    ) {
                nearest = occurence;
            }
        }

        return nearest;
    }

    private EarthquakeOccurence getNearestWesterly(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence nearest = null;
        for (EarthquakeOccurence occurence: earthquakeOccurences) {
            if (nearest == null && occurence.getLongitude() < mauritusLocation.longitude) {
                nearest = occurence;
            } else if (nearest != null && occurence.getLongitude() < mauritusLocation.longitude &&
                    distanceOfTwoPoints(occurence.getLatLng(), mauritusLocation)
                            < distanceOfTwoPoints(nearest.getLatLng(), mauritusLocation)
                    ) {
                nearest = occurence;
            }
        }

        return nearest;
    }

    private EarthquakeOccurence getDeepest(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence deepest = null;

        for (EarthquakeOccurence occurence : earthquakeOccurences) {
            if (deepest == null) {
                deepest = occurence;
            } else if (occurence.getDepth() > deepest.getDepth()) {
                deepest = occurence;
            }
        }

        return deepest;
    }

    private EarthquakeOccurence getLargestMagnitude(ArrayList<EarthquakeOccurence> earthquakeOccurences) {
        EarthquakeOccurence largestMagnitude = null;

        for (EarthquakeOccurence occurence : earthquakeOccurences) {
            if (largestMagnitude == null) {
                largestMagnitude = occurence;
            } else if (occurence.getMagnitude() > largestMagnitude.getMagnitude()) {
                largestMagnitude = occurence;
            }
        }

        return largestMagnitude;
    }


    private double distanceOfTwoPoints(LatLng p1, LatLng p2) {
        return distance(p1.latitude, p2.latitude, p1.longitude, p2.longitude, 0,0);
    }


    public static double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 1000; // convert to meters

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
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
