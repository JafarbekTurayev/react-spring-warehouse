package com.example.warehouseapp.bot;

import com.example.warehouseapp.bot.locationmodels.LocationsItem;
import com.example.warehouseapp.bot.locationmodels.MapQuest;
import com.google.gson.Gson;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

@Service
public class HttpService {
    private static String url = "http://open.mapquestapi.com/geocoding/v1/reverse?key=iFaKV4Rp3rWRtGeJoQQYBEUnqJcxF8Cl&location=";

    public String getAddress(Float lat, Float lon) {
        Gson gson = new Gson();
        StringBuilder stringBuilder = new StringBuilder();
        String address = null;
        try {
            URL url1 = new URL(url + lat + "," + lon);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url1.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if (responseCode >= 200 && responseCode <= 299) {
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String row;
                while ((row = bufferedReader.readLine()) != null) {
                    stringBuilder.append(row);
                }
                MapQuest mapQuest = gson.fromJson(stringBuilder.toString(), MapQuest.class);
                LocationsItem locationsItem = mapQuest.getResults().get(0).getLocations().get(0);
                address = locationsItem.getAdminArea5() + ", " + locationsItem.getStreet();
            } else {
                address = "Not found";
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return address;
    }
}
