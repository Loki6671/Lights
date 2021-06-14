package com.project.task.models;

public class Country {
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public Country(String name) {
        this.name = name;
    }
}
