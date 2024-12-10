package com.radio.shows.data;


import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Program {

    @JacksonXmlProperty(isAttribute = true)
    private int id;

    public Program(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @JacksonXmlProperty(isAttribute = true)
    private String name;

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Program{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}