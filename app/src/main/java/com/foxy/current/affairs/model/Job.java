package com.foxy.current.affairs.model;


public class Job {
    private String header;
    private String title;

    public Job() {
    }

    public Job(String header, String title) {
        this.header = header;
        this.title = title;
    }

    public String getHeader() {
        return header;
    }

    public String getTitle() {
        return title;
    }
}
