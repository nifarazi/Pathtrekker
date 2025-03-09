package com.example.pathtrekker;

public class Destination {
    private String name, description, topAttractions, weatherInfo, localCuisine, transportInfo, openingTime, closingTime;
    private int day;         // Day of the itinerary (1-based)
    private String timeSlot; // "Morning" or "Afternoon"

    // Constructor
    public Destination(String name, String description, String topAttractions, String weatherInfo,
                       String localCuisine, String transportInfo, String openingTime, String closingTime) {
        this.name = name != null ? name : "Unknown"; // Prevent null name
        this.description = description;
        this.topAttractions = topAttractions;
        this.weatherInfo = weatherInfo;
        this.localCuisine = localCuisine;
        this.transportInfo = transportInfo;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.day = 0;        // Default to unassigned day
        this.timeSlot = null; // Default to unassigned time slot
    }

    // Getters
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

    // Setters
    public void setDay(int day) {
        if (day < 0) throw new IllegalArgumentException("Day cannot be negative");
        this.day = day;
    }
    public void setTimeSlot(String timeSlot) {
        if (timeSlot != null && !timeSlot.equals("Morning") && !timeSlot.equals("Afternoon")) {
            throw new IllegalArgumentException("Time slot must be 'Morning' or 'Afternoon'");
        }
        this.timeSlot = timeSlot;
    }

    // Optional: toString for debugging
    @Override
    public String toString() {
        return "Destination{name='" + name + "', day=" + day + ", timeSlot='" + timeSlot + "'}";
    }
}