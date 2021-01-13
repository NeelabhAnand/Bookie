package com.example.bookie.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.ArrayList;

public class VolumeInfo implements Serializable {

    @SerializedName("title")
    private String title;

    @SerializedName("publishedDate")
    private String publishedDate;

    @SerializedName("authors")
    private ArrayList<String> authors;

    @SerializedName("imageLinks")
    private ImageLinks imageLinks;

    @SerializedName("subtitle")
    private String subtitle;

    @SerializedName("description")
    private String description;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        this.publishedDate = publishedDate;
    }

    public ArrayList<String> getAuthors() {
        return authors;
    }

    public void setAuthors(ArrayList<String> authors) {
        this.authors = authors;
    }

    public String getDescription() { return description; }

    public void setDescription(String description) {this.description = description; }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public ImageLinks getImageLinks() {
        return imageLinks;
    }

    public void setImageLinks(ImageLinks imageLinks) {
        this.imageLinks = imageLinks;
    }

    public static class ImageLinks implements Serializable {

        @SerializedName("smallThumbnail")
        private String smallThumbnail;

        @SerializedName("small")
        private String small;

        public String getSmallThumbnail() {
            return smallThumbnail;
        }

        public void setSmallThumbnail(String smallThumbnail) {
            this.smallThumbnail = smallThumbnail; }

        public String getSmall() { return small; }

        public void setSmall(String small) { this.small = small;}
    }

}