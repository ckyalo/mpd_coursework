package com.kyalo.cyrose.seismicinformation.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kyalo.cyrose.seismicinformation.Activities.DetailedView;
import com.kyalo.cyrose.seismicinformation.Activities.MainActivity;
import com.kyalo.cyrose.seismicinformation.Data.Earthquakes;
import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;
import com.kyalo.cyrose.seismicinformation.R;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class SeismicAdapter extends RecyclerView.Adapter<SeismicAdapter.MyViewHolder> {

    private ArrayList<EarthquakeOccurence> earthquakeOccurences;

    private Context context;

    public SeismicAdapter(ArrayList<EarthquakeOccurence> earthquakeOccurences,Context context) {
        this.earthquakeOccurences = earthquakeOccurences;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v =  LayoutInflater.from(parent.getContext())
                .inflate(R.layout.earthquake_item, parent, false);
        return  new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder, final int i) {
        myViewHolder.updateUI(earthquakeOccurences.get(i), i);

//        myViewHolder.itemView.setBackgroundColor();

        myViewHolder.magnitude.setBackgroundColor(Color.parseColor(earthquakeOccurences.get(i).getColor()));


        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailedView.class);

                intent.putExtra("Selected", i);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return earthquakeOccurences.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private TextView earthquakeTitle;
        private TextView earthquakeDate;
        private TextView magnitude;
        private TextView positionText;
        private MyViewHolder(View itemView) {
            super(itemView);
            earthquakeDate = itemView.findViewById(R.id.earthquakeDate);
            earthquakeTitle = itemView.findViewById(R.id.earthquakeTitle);
            magnitude = itemView.findViewById(R.id.earthquakeMagnitude);
            positionText = itemView.findViewById(R.id.earthquakePosition);
        }

        private void updateUI(EarthquakeOccurence earthquakeOccurence, int position) {
            earthquakeTitle.setText(earthquakeOccurence.getLocation());
            earthquakeDate.setText(earthquakeOccurence.getDateTime());
            magnitude.setText("M " + String.valueOf( earthquakeOccurence.getMagnitude()));
            positionText.setText(String.valueOf(position + 1));
        }
    }

}

