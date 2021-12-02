package com.example.warehouseapp.bot.locationmodels;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.asif.gsonpojogenerator")
public class DisplayLatLng implements Serializable {

    @SerializedName("lng")
    private double lng;

    @SerializedName("lat")
    private double lat;

    public void setLng(double lng) {
        this.lng = lng;
    }

    public double getLng() {
        return lng;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLat() {
        return lat;
    }

    @Override
    public String toString() {
        return
                "DisplayLatLng{" +
                        "lng = '" + lng + '\'' +
                        ",lat = '" + lat + '\'' +
                        "}";
    }
}