package com.cityguide.place;

import java.time.Duration;
import java.time.LocalTime;

/***
 * entity class for food truck
 *
 */
public class FoodTruck extends Place {

    private String locationDescription;
    private String foodType;

    /***
     * constructor for {@link FoodTruck} with all contents
     * @param name
     * @param location
     * @param open
     * @param close
     * @param locationDescription
     * @param foodType
     */
    public FoodTruck(String name, String location, LocalTime open, LocalTime close, String locationDescription, String foodType) {
		super(name, location, open, close);
		this.locationDescription = locationDescription;
		this.foodType = foodType;
	}

    public String getLocationDescription() {
        return locationDescription;
    }

    public void setLocationDescription(String locationDescription) {
        this.locationDescription = locationDescription;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    @Override
    public Duration opensIn() {
        return null;
    }

    @Override
    public Duration closesIn() {
        return null;
    }

    /***
     * converts food truck's contents to a readable format
     * @return
     */
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(getName() + "\n\tlocated at :: " + getLocation() + "\n\topens at :: " + getOpen() + " closes at :: " + getClose());
    	if (getFoodType() != null && !getFoodType().isEmpty()) {
    		sb.append("\n\tmenu :: " + getFoodType());
    	}
    	if (getLocationDescription() != null && !getLocationDescription().isEmpty()) {
    		sb.append("\n\tlocation description :: " + getLocationDescription());
    	}
    	return sb.toString();
    }
}
