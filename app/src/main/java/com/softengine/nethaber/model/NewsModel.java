package com.softengine.nethaber.model;

/**
 * Created by mehme on 12.06.2017.
 */

public class NewsModel {
    private String categori;
    private String title;
    private String description;
    private String pubDate;
    private String image;
    private String link;

    public NewsModel(String categori, String title, String description, String pubDate, String image, String link) {
        this.categori = categori;
        this.title = title;
        this.description = description;
        this.pubDate = pubDate;
        this.image = image;
        this.link = link;
    }

    public NewsModel() {
    }

    public String getCategori() {
        return categori;
    }

    public void setCategori(String categori) {
        this.categori = categori;
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

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
