package com.example.pathtrekker;

import java.util.List;

public class ItineraryData {
    private static Hotel hotel;
    private static int numPeople;
    private static int numDays;
    private static List<Destination> destinations;
    private static List<Event> nightEvents;

    public static void setHotel(Hotel h) { hotel = h; }
    public static Hotel getHotel() { return hotel; }

    public static void setNumPeople(int n) { numPeople = n; }
    public static int getNumPeople() { return numPeople; }

    public static void setNumDays(int n) { numDays = n; }
    public static int getNumDays() { return numDays; }

    public static void setDestinations(List<Destination> d) { destinations = d; }
    public static List<Destination> getDestinations() { return destinations; }

    public static void setNightEvents(List<Event> e) { nightEvents = e; }
    public static List<Event> getNightEvents() { return nightEvents; }
}