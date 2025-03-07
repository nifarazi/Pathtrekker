package com.example.pathtrekker;

public class Event {
    private String name;
    private String openingTime;
    private String closingTime;
    private String location;
    private String description;

    public Event(String name, String openingTime, String closingTime, String location, String description) {
        this.name = name;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.location = location;
        this.description = description;
    }

    public String getName() { return name; }
    public String getOpeningTime() { return openingTime; }
    public String getClosingTime() { return closingTime; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
}