package com.cityguide.placefinder;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.cityguide.place.FoodTruck;
import com.cityguide.place.FoodTruckDeserializer;
import com.cityguide.place.Place;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

/***
 * Class to interface with socrata api
 * implements {@link IPlaceFinder}
 */
public class FoodTruckFinder extends AbstractFinder implements IPlaceFinder{

	/***
	 * constructor for {@link FoodTruckFinder}
	 * @param uri
	 */
	public FoodTruckFinder(String uri) {
		this.client = HttpClient.newBuilder().build();
		this.uri = uri;
		this.logger = Logger.getLogger(FoodTruckFinder.class.getSimpleName());
	}

	/***
	 * returns all results
	 * @return
	 */
	@Override
	public List<FoodTruck> find() {
		logger.log(Level.SEVERE, "not implemented");
		return new ArrayList<>();
	}

	/***
	 * returns all open food trucks
	 * @return
	 */
	@Override
	public List<FoodTruck> findOpenNow() {
		List<FoodTruck> foodTrucks = new ArrayList<>();
		String query = getOpenNowRequestQuery();
		foodTrucks = makeGetCall(foodTrucks, query);
		return foodTrucks;
	}

	/***
	 * returns open food trucks in pages
	 * @param offset
	 * @param limit
	 * @return
	 */
	@Override
	public List<? extends Place> findOpenNowPaginated(int offset, int limit) {
		List<FoodTruck> foodTrucks = new ArrayList<>();
		String query = getOpenNowRequestQuery(offset, limit);
		foodTrucks = makeGetCall(foodTrucks, query);
		return foodTrucks;
	}
	
	/***
	 * makes a get call to socrata api with query provided and deserialized results
	 * @param foodTrucks
	 * @param query
	 * @return
	 */
	@SuppressWarnings("serial")
	List<FoodTruck> makeGetCall(List<FoodTruck> foodTrucks, String query) {
		try {
			HttpRequest request = HttpRequest.newBuilder(URI.create(getUri() + query)).GET().build();
			HttpResponse<String> response = getClient().send(request, HttpResponse.BodyHandlers.ofString());
			GsonBuilder gsonBuilder = new GsonBuilder();
			gsonBuilder.registerTypeAdapter(FoodTruck.class, new FoodTruckDeserializer());
			foodTrucks = gsonBuilder.create().fromJson(response.body(), new TypeToken<List<FoodTruck>>() {
			}.getType());
		} catch (Exception e) {
			logger.log(Level.SEVERE, "Error retrieving food truck results");
		}
		return foodTrucks;
	}
	
	/***
	 * returns url query to find food trucks open at current time with pagination
	 * @param offset
	 * @param limit
	 * @return
	 */
	String getOpenNowRequestQuery(int offset, int limit) {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String currentTime = LocalTime.now().format(dateTimeFormatter);
		int day = LocalDate.now().getDayOfWeek().getValue() % 7;

		return "?$offset=" + offset + "&$limit=" + limit + "&$select=applicant,location,start24,end24,optionaltext"
				+ "&$where="
				+ URLEncoder.encode(
						"start24<'" + currentTime + "'" + " and end24>'" + currentTime + "'" + " and dayorder=" + day,
						StandardCharsets.UTF_8).toString()
				+ "&$order=applicant";
	}
	
	/***
	 * returns url query to find all food trucks open at current time
	 * @return
	 */
	String getOpenNowRequestQuery() {
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
		String currentTime = LocalTime.now().format(dateTimeFormatter);
		int day = LocalDate.now().getDayOfWeek().getValue() % 7;

		return "?$select=applicant,location,start24,end24,optionaltext" + "&$where="
				+ URLEncoder.encode(
						"start24<'" + currentTime + "'" + " and end24>'" + currentTime + "'" + " and dayorder=" + day,
						StandardCharsets.UTF_8).toString()
				+ "&$order=applicant";
	}
}