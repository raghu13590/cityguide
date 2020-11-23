package com.cityguide.placefinder;

import com.cityguide.MessageOut;
import com.cityguide.ValidInput;
import com.cityguide.place.Place;

import java.time.LocalTime;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/***
 * handler to manage requests to query places and acts a sub controller
 */
public class PlaceHandler {

    static Scanner scanner = new Scanner(System.in);
    static Logger logger = Logger.getLogger(PlaceHandler.class.getSimpleName());

    /***
     * lists options place finder can be queried with
     * like find all, find open now and find paginated
     * @param keyword
     */
    public static void placeFinderOptions(String keyword) {
        PlaceFinderFactory factory = new PlaceFinderFactory();
        IPlaceFinder finder = factory.getFinder(keyword);
        if(finder == null) {
            logger.log(Level.SEVERE, "finder not found");
            return;
        }

        MessageOut.printPlaceFinderOptions();
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine().strip();
            if (ValidInput.FINDALL.getInput().equals(input)) {
                MessageOut.printResults(finder.find());
            } else if (ValidInput.FINDOPENNOW.getInput().equals(input)) {
                List<? extends Place> results = finder.findOpenNow();
                if (!results.isEmpty()) {
                    MessageOut.printResults(results);
                } else {
                    MessageOut.printNoResults();
                }
                // paginated request
            } else if (ValidInput.FINDOPENNOWPAGINATED.getInput().equals(input)) {
                handlePaginated(finder, 10);
            } else if (ValidInput.END.getInput().equals(input)) {
                break;
            } else {
                MessageOut.notValidInput();
            }
            MessageOut.printPlaceFinderOptions();
        }
    }

    /***
     * handles paginated request, fetches results in pages of size limit.
     * checks if the first request is within 5 minutes of the subsequent later requests
     * makes a new call if the first request is older than 5 minutes
     * @param finder
     * @param limit
     */
    static void handlePaginated(IPlaceFinder finder, int limit) {
        LocalTime firstReqTime = LocalTime.now();
        findPagedResults(finder, limit, 0);
        int offset = limit;
        while(scanner.hasNextLine()) {
            String input = scanner.nextLine();
            if (ValidInput.MORE.getInput().equals(input)) {
                    // checks if the current call is no later than 5 minutes of first request
                if (firstReqTime.plusMinutes(5L).isAfter(LocalTime.now())) {
                    findPagedResults(finder, limit, offset);
                    offset = offset + limit;
                    //makes a new call if the first request is older than 5 mins
                } else {
                    MessageOut.gettingNewResults();
                    findPagedResults(finder, limit, 0);
                    offset = limit;
                }
            } else if (ValidInput.END.getInput().equals(input)) {
                break;
            } else {
                MessageOut.notValidInput();
            }
        }
    }

    /***
     * prints paginated results of {@link Place}
     * @param finder
     * @param limit
     * @param offset
     */
    static void findPagedResults(IPlaceFinder finder, int limit, int offset) {
        List<? extends Place> results;
        results = finder.findOpenNowPaginated(offset, limit);
        if (!results.isEmpty()) {
            MessageOut.printResults(results);
            MessageOut.askForMore();
        } else {
            MessageOut.endOfResults();
        }
    }

}
