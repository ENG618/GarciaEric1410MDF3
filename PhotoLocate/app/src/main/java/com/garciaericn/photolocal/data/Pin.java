package com.garciaericn.photolocal.data;

import com.google.android.gms.maps.model.LatLng;

/**
 * Full Sail University
 * Mobile Development BS
 * Created by ENG618-Mac on 10/22/14.
 */
public class Pin {

    public static final String LAT_LNG = "com.garciaericn.photolocal.data.LAT_LNG";
    private LatLng latLng;
    private String title;
    private String description;

    public Pin() {

    }

    public Pin newPin(LatLng latLng, String title, String description) {
        Pin pin = new Pin();

        setLatLng(latLng);
        setTitle(title);
        setDescription(description);

        return pin;
    }

    public Pin newPinWithPhoto(LatLng latLng, String title, String description) {
        Pin pin = new Pin();

        setLatLng(latLng);
        setTitle(title);
        setDescription(description);

        return pin;
    }

    /**
     * Getter and Setter Methods
     * */

     public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
