package com.cityguide;

import java.util.Scanner;

import com.cityguide.placefinder.IPlaceFinder;
import com.cityguide.placefinder.PlaceHandler;

/***
 * application to find and explore places using open public city data
 * main class to interface with user, let them pick options to search for
 * and acts as a controller
 *
 */
public class Main {

    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        MessageOut.welcome();
        // print options to search on and take input from user
        MessageOut.menu();
        while(scanner.hasNextLine()){
            String input = scanner.nextLine().strip();
            if (ValidInput.FOOD_TRUCK.getInput().equals(input)){
                PlaceHandler.placeFinderOptions(input);
            } else if (ValidInput.END.getInput().equals(input)) {
                break;
            } else {
                MessageOut.notValidInput();
            }
            MessageOut.menu();
        }
        MessageOut.closing();
        System.exit(0);
    }
}
