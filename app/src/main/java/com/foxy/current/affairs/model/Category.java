package com.foxy.current.affairs.model;


public class Category {
    String name;

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirst() {
        return name.charAt(0) + "";
    }
}
