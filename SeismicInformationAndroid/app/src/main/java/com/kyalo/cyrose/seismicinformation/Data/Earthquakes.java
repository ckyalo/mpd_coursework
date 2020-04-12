package com.kyalo.cyrose.seismicinformation.Data;

import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;

import java.util.ArrayList;
import java.util.List;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class Earthquakes {
    private static final Earthquakes ourInstance = new Earthquakes();

    public static Earthquakes getInstance() {
        return ourInstance;
    }

    private Earthquakes() {

    }

    private List<EarthquakeOccurence> earthquakeOccurences = new ArrayList<EarthquakeOccurence>();

    public boolean isDownloading;


    public void addEarthquake(EarthquakeOccurence earthquakeOccurence) {
       earthquakeOccurences.add(earthquakeOccurence);
    }

    public void setEarthquakeOccurences(List<EarthquakeOccurence> earthquakeOccurences) {
        this.earthquakeOccurences = earthquakeOccurences;
    }

    public EarthquakeOccurence getEarthQuake(int i) {
        return earthquakeOccurences.get(i);
    }

    public List<EarthquakeOccurence> getEarthquakes() {
        return  earthquakeOccurences;
    }


    public int getIndex(EarthquakeOccurence earthquakeOccurence) {
        int index = 0;
        for (int i = 0; i < earthquakeOccurences.size(); i++) {
            if (earthquakeOccurence.getLocation().equals(earthquakeOccurences.get(i).getLocation()) &&
                    earthquakeOccurence.getDateTime().equals(earthquakeOccurences.get(i).getDateTime())
                    ) {
                index = i;
            }
        }

        return  index;
    }
}
