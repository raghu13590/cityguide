package com.cityguide;

/***
 * Enum to limit and identify user input
 */
public enum ValidInput {

    FOOD_TRUCK("1"), MORE("m"), END("0"), FINDALL("a"), FINDOPENNOW("o"), FINDOPENNOWPAGINATED("p");

    private String input;

    ValidInput(String input) {
        this.input = input;
    }

    public String getInput(){
        return input;
    }
}
