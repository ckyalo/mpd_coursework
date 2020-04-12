package com.kyalo.cyrose.seismicinformation.Services;

import android.util.Log;

import java.net.*;
import java.io.*;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class RssReader {

    public String fetchRSS() {
        String rssString = "";

        try {

            URL url = new URL("https://quakes.bgs.ac.uk/feeds/WorldSeismology.xml");
            URLConnection conn = url.openConnection();

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                rssString += inputLine;
            }
            in .close();

            return rssString;

        } catch (Exception e) {
            Log.d("XMLreader Error", "" + e.toString());
            return  "";
        }
    }
}
