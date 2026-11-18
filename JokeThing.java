package danaddr.jokething;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONException;
import org.json.JSONObject;

/*
 * Make a request to jokeapi.dev, for one of the following categories:
 * Programming, Misc, Dark, Pun, Spooky, Christmas.
 */
public class JokeThing {
	
	/**
	 * Makes a call to jokeapi.dev, only asks for two parameters.
	 * @param String category - A specific category that jokeapi.dev offers.
	 * @param String type - Whether the user wants a one or two part.
	 * @return The specific output that is given from a JSONObject. 
	 * @throws IOException, JSONException, InterruptedException 
	 */
	public static void displayJoke(String category, String type) throws IOException, JSONException, InterruptedException {
		String url = String.format("https://v2.jokeapi.dev/joke/%s?safe-mode&type=%s", category, type);
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create(url))
				.build();
		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		
		if (response.statusCode() == 200) {
			JSONObject data = new JSONObject(response.body());
			// If we retrieved it successfully, print the category, type, setup, and delivery.
			System.out.println("Category: " + data.getString("category"));
			System.out.println("Type: " + data.getString("type"));
			System.out.println("Setup: " + data.getString("setup"));
			System.out.println("Delivery: " + data.getString("delivery"));
		} else {
			System.out.println("Request status: " + response.statusCode());
		}
		
	}
	
	public static JSONObject writeJoke(String category, String type) throws IOException, JSONException, InterruptedException {
		return null;
	}
	
	public static void main(String[] args) throws JSONException, IOException, InterruptedException {
		/* 
		 * TODO: Get user input for both the category and type.
		 * TODO: If the user does not give a correct category, default to Programming.
		 * TODO: If the user tries to type an iteration of onepart or twopart, default it to either.
		 * TODO: If time permits, ask the user if they want it in a file, create it: or, ignore them and make it.	
		 */
		displayJoke("Programming", "twopart");
	}

}
