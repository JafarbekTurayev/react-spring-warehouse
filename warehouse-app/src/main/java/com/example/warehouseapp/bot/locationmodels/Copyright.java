package com.example.warehouseapp.bot.locationmodels;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;
import java.io.Serializable;

@Generated("com.asif.gsonpojogenerator")
public class Copyright implements Serializable {

    @SerializedName("imageAltText")
    private String imageAltText;

    @SerializedName("imageUrl")
    private String imageUrl;

    @SerializedName("text")
    private String text;

    public void setImageAltText(String imageAltText) {
        this.imageAltText = imageAltText;
    }

    public String getImageAltText() {
        return imageAltText;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String toString() {
        return
                "Copyright{" +
                        "imageAltText = '" + imageAltText + '\'' +
                        ",imageUrl = '" + imageUrl + '\'' +
                        ",text = '" + text + '\'' +
                        "}";
    }
}