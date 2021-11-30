package com.example.warehouseapp.bot.locationmodels;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.asif.gsonpojogenerator")
public class ProvidedLocation implements Serializable {

	@SerializedName("latLng")
	private LatLng latLng;

	public void setLatLng(LatLng latLng){
		this.latLng = latLng;
	}

	public LatLng getLatLng(){
		return latLng;
	}

	@Override
 	public String toString(){
		return 
			"ProvidedLocation{" + 
			"latLng = '" + latLng + '\'' + 
			"}";
		}
}