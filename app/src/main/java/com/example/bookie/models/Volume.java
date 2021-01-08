package com.example.bookie.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Neelabh Anand on 08/01/21.
 */
public class Volume implements Serializable {

    @SerializedName("volumeInfo")
    private VolumeInfo volumeInfo;

    public VolumeInfo getVolumeInfo() {
        return volumeInfo;
    }

    public void setVolumeInfo(VolumeInfo volumeInfo) {
        this.volumeInfo = volumeInfo;
    }
}