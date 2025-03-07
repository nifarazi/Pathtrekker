package com.example.pathtrekker;

public class Hotel {
    private String name;
    private String amenities;
    private double nightlyRate;
    private double totalCost;
    private String email;
    private String phoneNumber;
    private String division; // New field

    public Hotel(String name, String amenities, double nightlyRate, double totalCost, String email, String phoneNumber, String division) {
        this.name = name;
        this.amenities = amenities;
        this.nightlyRate = nightlyRate;
        this.totalCost = totalCost;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.division = division;
    }

    public String getName() { return name; }
    public String getAmenities() { return amenities; }
    public double getNightlyRate() { return nightlyRate; }
    public double getTotalCost() { return totalCost; }
    public String getEmail() { return email; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getDivision() { return division; } // New getter
}