package com.example.pathtrekker;

public class ItineraryData {
    private static Hotel hotel;
    private static int numPeople;
    private static int numDays;

    public static void setHotel(Hotel h) { hotel = h; }
    public static void setNumPeople(int n) { numPeople = n; }
    public static void setNumDays(int n) { numDays = n; }

    public static Hotel getHotel() { return hotel; }
    public static int getNumPeople() { return numPeople; }
    public static int getNumDays() { return numDays; }
}
