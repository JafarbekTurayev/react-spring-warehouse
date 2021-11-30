package com.example.warehouseapp.bot.locationmodels;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.asif.gsonpojogenerator")
public class Options implements Serializable {

	@SerializedName("thumbMaps")
	private boolean thumbMaps;

	@SerializedName("maxResults")
	private int maxResults;

	@SerializedName("ignoreLatLngInput")
	private boolean ignoreLatLngInput;

	public void setThumbMaps(boolean thumbMaps){
		this.thumbMaps = thumbMaps;
	}

	public boolean isThumbMaps(){
		return thumbMaps;
	}

	public void setMaxResults(int maxResults){
		this.maxResults = maxResults;
	}

	public int getMaxResults(){
		return maxResults;
	}

	public void setIgnoreLatLngInput(boolean ignoreLatLngInput){
		this.ignoreLatLngInput = ignoreLatLngInput;
	}

	public boolean isIgnoreLatLngInput(){
		return ignoreLatLngInput;
	}

	@Override
 	public String toString(){
		return 
			"Options{" + 
			"thumbMaps = '" + thumbMaps + '\'' + 
			",maxResults = '" + maxResults + '\'' + 
			",ignoreLatLngInput = '" + ignoreLatLngInput + '\'' + 
			"}";
		}
}