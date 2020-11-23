package com.cityguide;

import java.util.List;

import com.cityguide.place.Place;

/***
 * Utility class to write messages to console and act as a view layer
 */
public final class MessageOut {

    private MessageOut() {
    }
    
   /***
    * prints welcome message
    */
    static void welcome(){
        System.out.println("Welcome to food urban explorer...");
    }
    
    /***
     * prints options to search on
     */
    static void menu(){
        System.out.println("Select from the options below\n1-find food trucks near me\n0-terminate the app");
    }

    public static void displayMessage(String message){
        System.out.println(message);
    }
    
    /***
     * message to display in case of an incorrect input
     */
    public static void notValidInput() {
        System.out.println("Please type in a valid input");
    }
    
    /***
     * message to display before terminating the application
     */
    public static void closing() {
        System.out.println("Thanks for using, bye...");
    }
    
    /***
     * options within place finder
     */
	public static void printPlaceFinderOptions() {
		System.out.println("Select from the options below\na-find all\no-find open now\np-find open now in pages of 10\n0-to go to previous menu");
	}
	
	public static void gettingNewResults() {
		System.out.println("getting new results...");	
	}
	
	/***
	 * prints results line by line utilizing Entity class's toString() method
	 * @param list
	 */
	public static void printResults(List<? extends Place> list) {
		list.stream().forEach(item -> System.out.println(item.toString() + "\n"));
	}
	
	/***
	 * end of paged results options
	 */
	public static void endOfResults() {
		System.out.println("no more results...\npress 0 to go to previous menu");
		
	}
	
	/***
	 * options after printing paged results
	 */
	public static void askForMore() {
		System.out.println("Select\nm for more results\n0-to go to previous menu");
		
	}
	
	/***
	 * message for no results
	 */
	public static void printNoResults() {
		System.out.println("No results found");
		
	}
}
