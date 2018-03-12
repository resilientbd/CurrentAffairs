package com.foxy.current.affairs.model;


public class CurrentAffair {
    private String title;
    private String body;

    public CurrentAffair(String title, String body) {
        this.title = title;
        this.body = body;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }
}
