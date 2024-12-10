package com.radio.shows.data;

import java.time.LocalDate;

public class Episode{

    private String title;

    public Episode(String title, LocalDate publishdateutc, String description) {
        this.title = title;
        this.publishdateutc = publishdateutc;
        this.description = description;
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

    public LocalDate getPublishdateutc() {
        return publishdateutc;
    }

    public void setPublishdateutc(LocalDate publishdateutc) {
        this.publishdateutc = publishdateutc;
    }

    private String description;

    private LocalDate publishdateutc;


}
