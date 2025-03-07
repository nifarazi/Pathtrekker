package com.example.pathtrekker;

public class Destination {
    private String name, description, topAttractions, weatherInfo, localCuisine, transportInfo, openingTime, closingTime;
    private int day;
    private String timeSlot;

    public Destination(String name, String description, String topAttractions, String weatherInfo,
                       String localCuisine, String transportInfo, String openingTime, String closingTime) {
        this.name = name;
        this.description = description;
        this.topAttractions = topAttractions;
        this.weatherInfo = weatherInfo;
        this.localCuisine = localCuisine;
        this.transportInfo = transportInfo;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    // Getters and setters
    public String getName() { return name; }
    public String getDescription() { return description; }
    public String getTopAttractions() { return topAttractions; }
    public String getWeatherInfo() { return weatherInfo; }
    public String getLocalCuisine() { return localCuisine; }
    public String getTransportInfo() { return transportInfo; }
    public String getOpeningTime() { return openingTime; }
    public String getClosingTime() { return closingTime; }
    public int getDay() { return day; }
    public String getTimeSlot() { return timeSlot; }
    public void setDay(int day) { this.day = day; }
    public void setTimeSlot(String timeSlot) { this.timeSlot = timeSlot; }
}