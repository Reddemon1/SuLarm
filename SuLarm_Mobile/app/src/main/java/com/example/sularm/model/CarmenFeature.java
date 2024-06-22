package com.example.sularm.model;

import com.google.gson.annotations.SerializedName;
import com.mapbox.geojson.Point;

public class CarmenFeature {

    @SerializedName("id")
    private String id;

    @SerializedName("type")
    private String type;

    @SerializedName("text")
    private String text;

    @SerializedName("place_name")
    private String placeName;

    @SerializedName("geometry")
    private Point geometry;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPlaceName() {
        return placeName;
    }

    public void setPlaceName(String placeName) {
        this.placeName = placeName;
    }

    public Point getGeometry() {
        return geometry;
    }

    public void setGeometry(Point geometry) {
        this.geometry = geometry;
    }
}
