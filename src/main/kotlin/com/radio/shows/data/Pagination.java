package com.radio.shows.data;

public class Pagination{
    private int page;
    private int size;
    private int totalhits;
    private int totalpages;
    private String nextpage;

    public Pagination(String nextpage) {
        this.nextpage = nextpage;
    }

    // Getters and setters
    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getTotalhits() {
        return totalhits;
    }

    public void setTotalhits(int totalhits) {
        this.totalhits = totalhits;
    }

    public int getTotalpages() {
        return totalpages;
    }

    public void setTotalpages(int totalpages) {
        this.totalpages = totalpages;
    }

    public String getNextpage() {
        return nextpage;
    }

    public void setNextpage(String nextpage) {
        this.nextpage = nextpage;
    }

    @Override
    public String toString() {
        return "Pagination{" +
                "page=" + page +
                ", size=" + size +
                ", totalhits=" + totalhits +
                ", totalpages=" + totalpages +
                ", nextpage='" + nextpage + '\'' +
                '}';
    }
}