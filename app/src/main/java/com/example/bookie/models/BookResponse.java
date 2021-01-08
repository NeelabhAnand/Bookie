package com.example.bookie.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Neelabh Anand on 08/01/21.
 */
public class BookResponse implements Serializable {

    @SerializedName("items")
    private ArrayList<Volume> volumes;

    public ArrayList<Volume> getVolumes() {
        return volumes;
    }

    public void setVolumes(ArrayList<Volume> volumes) {
        this.volumes = volumes;
    }
}