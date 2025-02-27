package com.example.pathtrekker;

public class LocalEvent {
    private int id;
    private String eventName;
    private String startDate;
    private String endDate;
    private String openingTime;
    private String closingTime;
    private String location;
    private String description;

    public LocalEvent(int id, String eventName, String startDate, String endDate, String openingTime, String closingTime, String location, String description) {
        this.id = id;
        this.eventName = eventName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
        this.location = location;
        this.description = description;
    }

    public String getEventName() { return eventName; }
    public String getStartDate() { return startDate; }
    public String getEndDate() { return endDate; }
    public String getOpeningTime() { return openingTime; }
    public String getClosingTime() { return closingTime; }
    public String getLocation() { return location; }
    public String getDescription() { return description; }
}
