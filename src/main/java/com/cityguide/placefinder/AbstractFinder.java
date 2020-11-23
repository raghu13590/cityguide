package com.cityguide.placefinder;

import java.net.http.HttpClient;
import java.util.logging.Logger;

/***
 * abstract class for place finder to hold common data elements
 *
 */
public abstract class AbstractFinder {

	HttpClient client;
    String key;
    String uri;
    Logger logger;    
    
    public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public HttpClient getClient() {
        return client;
    }

    public void setClient(HttpClient client) {
        this.client = client;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
   
}
