package com.cityguide.place;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

/***
 * custom deserializer for {@link FoodTruck}
 *
 */
public class FoodTruckDeserializer implements JsonDeserializer<FoodTruck>{

	@Override
	public FoodTruck deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
		JsonObject jsonObject = json.getAsJsonObject();
		String applicant = jsonObject.get("applicant").getAsString();
		String location = jsonObject.get("location").getAsString();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern( "HH:mm" );
		LocalTime open = LocalTime.parse(jsonObject.get("start24").getAsString(), dateTimeFormatter);
		LocalTime close = LocalTime.parse(jsonObject.get("end24").getAsString(), dateTimeFormatter);
		
		String locationDesc = null;
		if (jsonObject.has("locationdesc")) {
			locationDesc = jsonObject.get("locationdesc").getAsString();
		}
		
		String optionalText = null;
		if (jsonObject.has("optionaltext")) {
			optionalText = jsonObject.get("optionaltext").getAsString();
		}
		return new FoodTruck(applicant, location, open, close, locationDesc, optionalText);
	}
}
