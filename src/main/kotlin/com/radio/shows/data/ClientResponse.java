package com.radio.shows.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "programinfo")
public class ClientResponse{

    String name;

    public ClientResponse(String name, List<Episode> episodeList) {
        this.name = name;
        this.episodeList = episodeList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Episode> getEpisodeList() {
        return episodeList;
    }

    public void setEpisodeList(List<Episode> episodeList) {
        this.episodeList = episodeList;
    }

    List<Episode> episodeList;
}
