package com.cityguide.place;

import java.time.Duration;
import java.time.LocalTime;

/***
 * abstract class for a place in the context of maps
 * like businesses, restaurants, gas stations, indoor and outdoor places to visit
 *
 */
public abstract class Place {

    private String name;
    private String location;
    protected LocalTime open;
    protected LocalTime close;

    public Place(String name, String location, LocalTime open, LocalTime close) {
		super();
		this.name = name;
		this.location = location;
		this.open = open;
		this.close = close;
	}

	public Duration opensIn() {
        return null;
    }

    public Duration closesIn() {
        return null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public LocalTime getOpen() {
        return open;
    }

    public void setOpen(LocalTime open) {
        this.open = open;
    }

    public LocalTime getClose() {
        return close;
    }

    public void setClose(LocalTime close) {
        this.close = close;
    }
}