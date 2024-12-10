package com.radio.shows.data;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JacksonXmlRootElement(localName = "sr")
public class ProgramsResponse{

    private String copyright;

    private Pagination pagination;

    @JacksonXmlElementWrapper(localName = "programs")
    @JacksonXmlProperty(localName = "program")
    private List<Program> programs;

    // Getters and setters
    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }

    public Pagination getPagination() {
        return pagination;
    }

    public void setPagination(Pagination pagination) {
        this.pagination = pagination;
    }

    public List<Program> getPrograms() {
        return programs;
    }

    public void setPrograms(List<Program> programs) {
        this.programs = programs;
    }

    @Override
    public String toString() {
        return "ProgramsResponse{" +
                "copyright='" + copyright + '\'' +
                ", pagination=" + pagination +
                ", programs=" + programs +
                '}';
    }
}



