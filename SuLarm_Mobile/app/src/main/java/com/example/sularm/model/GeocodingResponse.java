package com.example.sularm.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GeocodingResponse {

    @SerializedName("type")
    private String type;

    @SerializedName("query")
    private List<String> query;

    @SerializedName("features")
    private List<CarmenFeature> features;

    // Getters and setters

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getQuery() {
        return query;
    }

    public void setQuery(List<String> query) {
        this.query = query;
    }

    public List<CarmenFeature> getFeatures() {
        return features;
    }

    public void setFeatures(List<CarmenFeature> features) {
        this.features = features;
    }
}
