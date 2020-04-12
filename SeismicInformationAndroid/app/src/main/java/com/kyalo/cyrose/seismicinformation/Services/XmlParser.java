package com.kyalo.cyrose.seismicinformation.Services;

import android.util.Log;

import com.kyalo.cyrose.seismicinformation.Data.Earthquakes;
import com.kyalo.cyrose.seismicinformation.Models.EarthquakeOccurence;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.List;

/*
 * Name: Cyrose Kyalo
 * Matric Number: S1803439
 */

public class XmlParser {
    private String xml = "<rss xmlns:geo=\"http://www.w3.org/2003/01/geo/wgs84_pos#\" xmlns:dc=\"http://purl.org/dc/elements/1.1/\" version=\"2.0\">\n" +
            "<channel>\n" +
            "<title>Recent earthquakes</title>\n" +
            "<link>http://earthquakes.bgs.ac.uk/</link>\n" +
            "<description>\n" +
            "Recent world seismic events recorded by the BGS Seismology team\n" +
            "</description>\n" +
            "<language>en-gb</language>\n" +
            "<lastBuildDate>Mon, 30 Mar 2020 07:50:01</lastBuildDate>\n" +
            "<image>\n" +
            "<title>BGS Logo</title>\n" +
            "<url>\n" +
            "http://www.bgs.ac.uk/images/logos/bgs_c_w_227x50.gif\n" +
            "</url>\n" +
            "<link>http://earthquakes.bgs.ac.uk/</link>\n" +
            "</image>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 7.5 :KURIL ISLANDS, Wed, 25 Mar 2020 02:49:21\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Wed, 25 Mar 2020 02:49:21 ; Location: KURIL ISLANDS ; Lat/long: 48.986,157.693 ; Depth: 56 km ; Magnitude: 7.5\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200325025908.html\n" +
            "</link>\n" +
            "<pubDate>Wed, 25 Mar 2020 02:49:21</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>48.986</geo:lat>\n" +
            "<geo:long>157.693</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 5.3 :CROATIA, Sun, 22 Mar 2020 05:24:02\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Sun, 22 Mar 2020 05:24:02 ; Location: CROATIA ; Lat/long: 45.870,16.030 ; Depth: 10 km ; Magnitude: 5.3\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200322052630.html\n" +
            "</link>\n" +
            "<pubDate>Sun, 22 Mar 2020 05:24:02</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>45.870</geo:lat>\n" +
            "<geo:long>16.030</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 7.0 :KURIL ISLANDS, Thu, 13 Feb 2020 10:33:44\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Thu, 13 Feb 2020 10:33:44 ; Location: KURIL ISLANDS ; Lat/long: 45.668,148.893 ; Depth: 142 km ; Magnitude: 7.0\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200213104520.html\n" +
            "</link>\n" +
            "<pubDate>Thu, 13 Feb 2020 10:33:44</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>45.668</geo:lat>\n" +
            "<geo:long>148.893</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 7.7 :CARIBBEAN SEA, Tue, 28 Jan 2020 19:10:32\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Tue, 28 Jan 2020 19:10:32 ; Location: CARIBBEAN SEA ; Lat/long: 19.440,-78.754 ; Depth: 10 km ; Magnitude: 7.7\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200128191903.html\n" +
            "</link>\n" +
            "<pubDate>Tue, 28 Jan 2020 19:10:32</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>19.440</geo:lat>\n" +
            "<geo:long>-78.754</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.7 :EASTERN TURKEY, Fri, 24 Jan 2020 17:55:18\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Fri, 24 Jan 2020 17:55:18 ; Location: EASTERN TURKEY ; Lat/long: 38.407,39.070 ; Depth: 10 km ; Magnitude: 6.7\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200124180002.html\n" +
            "</link>\n" +
            "<pubDate>Fri, 24 Jan 2020 17:55:18</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>38.407</geo:lat>\n" +
            "<geo:long>39.070</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.4 :PUERTO RICO, Tue, 07 Jan 2020 08:24:26\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Tue, 07 Jan 2020 08:24:26 ; Location: PUERTO RICO ; Lat/long: 17.862,-66.829 ; Depth: 10 km ; Magnitude: 6.4\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20200107083303.html\n" +
            "</link>\n" +
            "<pubDate>Tue, 07 Jan 2020 08:24:26</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>17.862</geo:lat>\n" +
            "<geo:long>-66.829</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.8 :PHILIPPINES, Sun, 15 Dec 2019 06:11:51\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Sun, 15 Dec 2019 06:11:51 ; Location: PHILIPPINES ; Lat/long: 6.697,125.174 ; Depth: 18 km ; Magnitude: 6.8\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191215061500.html\n" +
            "</link>\n" +
            "<pubDate>Sun, 15 Dec 2019 06:11:51</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>6.697</geo:lat>\n" +
            "<geo:long>125.174</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.4 :ALBANIA, Tue, 26 Nov 2019 02:54:10\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Tue, 26 Nov 2019 02:54:10 ; Location: ALBANIA ; Lat/long: 41.578,20.270 ; Depth: 22 km ; Magnitude: 6.4\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191126025639.html\n" +
            "</link>\n" +
            "<pubDate>Tue, 26 Nov 2019 02:54:10</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>41.578</geo:lat>\n" +
            "<geo:long>20.270</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 5.9 :NORTHWEST IRAN, Thu, 07 Nov 2019 22:47:11\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Thu, 07 Nov 2019 22:47:11 ; Location: NORTHWEST IRAN ; Lat/long: 38.207,47.062 ; Depth: 20 km ; Magnitude: 5.9\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191107225247.html\n" +
            "</link>\n" +
            "<pubDate>Thu, 07 Nov 2019 22:47:11</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>38.207</geo:lat>\n" +
            "<geo:long>47.062</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.5 :PHILIPPINES, Thu, 31 Oct 2019 01:11:23\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Thu, 31 Oct 2019 01:11:23 ; Location: PHILIPPINES ; Lat/long: 6.910,125.178 ; Depth: 10 km ; Magnitude: 6.5\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191031011500.html\n" +
            "</link>\n" +
            "<pubDate>Thu, 31 Oct 2019 01:11:23</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>6.910</geo:lat>\n" +
            "<geo:long>125.178</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.6 :PHILIPPINES, Tue, 29 Oct 2019 01:05:07\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Tue, 29 Oct 2019 01:05:07 ; Location: PHILIPPINES ; Lat/long: 6.757,125.008 ; Depth: 15 km ; Magnitude: 6.6\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191029011001.html\n" +
            "</link>\n" +
            "<pubDate>Tue, 29 Oct 2019 01:05:07</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>6.757</geo:lat>\n" +
            "<geo:long>125.008</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 5.5 :SOUTHERN IRAN, Mon, 21 Oct 2019 10:58:49\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Mon, 21 Oct 2019 10:58:49 ; Location: SOUTHERN IRAN ; Lat/long: 27.104,55.686 ; Depth: 10 km ; Magnitude: 5.5\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191021110020.html\n" +
            "</link>\n" +
            "<pubDate>Mon, 21 Oct 2019 10:58:49</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>27.104</geo:lat>\n" +
            "<geo:long>55.686</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.4 :PHILIPPINES, Wed, 16 Oct 2019 11:37:16\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Wed, 16 Oct 2019 11:37:16 ; Location: PHILIPPINES ; Lat/long: 6.715,125.007 ; Depth: 16 km ; Magnitude: 6.4\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20191016114000.html\n" +
            "</link>\n" +
            "<pubDate>Wed, 16 Oct 2019 11:37:16</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>6.715</geo:lat>\n" +
            "<geo:long>125.007</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 6.5 :SERAM,INDONESIA, Wed, 25 Sep 2019 23:46:41\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Wed, 25 Sep 2019 23:46:41 ; Location: SERAM,INDONESIA ; Lat/long: -3.453,128.370 ; Depth: 12 km ; Magnitude: 6.5\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20190925235001.html\n" +
            "</link>\n" +
            "<pubDate>Wed, 25 Sep 2019 23:46:41</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>-3.453</geo:lat>\n" +
            "<geo:long>128.370</geo:long>\n" +
            "</item>\n" +
            "<item>\n" +
            "<title>\n" +
            "World Earthquake alert : M 5.8 :INDIA, Tue, 24 Sep 2019 11:01:48\n" +
            "</title>\n" +
            "<description>\n" +
            "Origin date/time: Tue, 24 Sep 2019 11:01:48 ; Location: INDIA ; Lat/long: 32.963,74.920 ; Depth: 10 km ; Magnitude: 5.8\n" +
            "</description>\n" +
            "<link>\n" +
            "http://earthquakes.bgs.ac.uk/earthquakes/recent_events/20190924111000.html\n" +
            "</link>\n" +
            "<pubDate>Tue, 24 Sep 2019 11:01:48</pubDate>\n" +
            "<category>EQMH</category>\n" +
            "<geo:lat>32.963</geo:lat>\n" +
            "<geo:long>74.920</geo:long>\n" +
            "</item>\n" +
            "</channel>\n" +
            "</rss>";

    public ArrayList<EarthquakeOccurence> parseXML(String xml) {
        XmlPullParserFactory parserFactory;
        try {
            parserFactory = XmlPullParserFactory.newInstance();
            XmlPullParser parser = parserFactory.newPullParser();

            parser.setInput(new StringReader(xml));

            return processParsing(parser);

        } catch (XmlPullParserException e) {
            return  null;

        } catch (IOException e) {
            return  null;
        }

    }

    private ArrayList<EarthquakeOccurence> processParsing(XmlPullParser parser) throws IOException, XmlPullParserException{

        int eventType = parser.getEventType();

        // This boolean checks to se if it is the first description
        boolean firstdescription = true;

        ArrayList<EarthquakeOccurence> earthquakeOccurences = new ArrayList<EarthquakeOccurence>();

        while (eventType != XmlPullParser.END_DOCUMENT) {

            switch (eventType) {
                case XmlPullParser.START_TAG:
                    if ("description".equals(parser.getName()))
                        if (!firstdescription)
                            earthquakeOccurences.add(extractDescription(parser.nextText()));
                        else
                            firstdescription = false;

            }

            eventType = parser.next();
        }

        return earthquakeOccurences;
    }

    private EarthquakeOccurence extractDescription(String description) {

        String[] splited = description.split(" ; ");


        String dateTime = splited[0].substring(18);




        String location = splited[1].substring(10);

        String latLongString = splited[2].substring(10);
        String[] latLong = latLongString.split(",");

        double latitude = Double.parseDouble( latLong[0]);
        double longitude = Double.parseDouble(latLong[1]);

        int depth = Integer.parseInt(splited[3].substring(7, splited[3].length()-3));
        double magnitude = Double.parseDouble(splited[4].substring(11));

        return new EarthquakeOccurence(
                dateTime,
                location,
                longitude,
                latitude,
                depth,
                magnitude
        );
    }


}
