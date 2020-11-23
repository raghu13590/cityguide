package com.cityguide.placefinder;

import com.cityguide.ValidInput;

/***
 * factory class to return right type of finder
 *
 */
public class PlaceFinderFactory {

    public IPlaceFinder getFinder(String finderType){

        IPlaceFinder placeFinder = null;
        if (ValidInput.FOOD_TRUCK.getInput().equals(finderType)) {
            placeFinder = new FoodTruckFinder("https://data.sfgov.org/resource/jjew-r69b.json");
        }
        return placeFinder;
    }

}
