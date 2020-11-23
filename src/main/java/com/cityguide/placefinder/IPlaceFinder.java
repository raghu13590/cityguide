package com.cityguide.placefinder;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cityguide.MessageOut;
import com.cityguide.ValidInput;
import com.cityguide.place.Place;

/***
 * interface to hold common ways/actions to find a place
 *
 */
public interface IPlaceFinder {
	
    public List<? extends Place> find();

    public List<? extends Place> findOpenNowPaginated(int offset, int limit);
    
    public List<? extends Place> findOpenNow();

}
