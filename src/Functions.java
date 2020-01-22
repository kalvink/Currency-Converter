import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;

import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Functions {
	static double rate, rate2;
	static String json;
	static String beforeCurrencySign = "$";
	static String finalCurrencySign = "$";
	static double result;
	static String base = null, to = null;

	// Sends a GET Request to a free exchange rate api and retrieves the updated
	// currency rates.
	public static void getRequest(String from, String to) throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.exchangeratesapi.io/latest?base=" + from + "&symbols=" + to + "")).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		json = response.body();
	}

	//
	public static double convertTo(int cfrom, int cto, double money) throws Exception {
		if (cfrom == 0) {
			base = "CAD";
			beforeCurrencySign = "$";
		} else if (cfrom == 1) {
			base = "USD";
			beforeCurrencySign = "$";

		} else if (cfrom == 2) {
			base = "GBP";
			beforeCurrencySign = "£";

		} else if (cfrom == 3) {
			base = "CNY";
			beforeCurrencySign = "¥";

		} else if (cfrom == 4) {
			base = "JPY";
			beforeCurrencySign = "¥";
		}

		if (cto == 0) {
			to = "CAD";
			finalCurrencySign = "$";

		} else if (cto == 1) {
			to = "USD";
			finalCurrencySign = "$";

		} else if (cto == 2) {
			to = "GBP";
			finalCurrencySign = "£";

		} else if (cto == 3) {
			to = "CNY";
			finalCurrencySign = "¥";
		} else if (cto == 4) {
			to = "JPY";
			finalCurrencySign = "¥";
		}

		getRequest(base, to);
		// delete later
		System.out.println(json);
		getRates(to);

		result = money * Double.parseDouble(getRates(to));

		System.out.println(result);
		return result;

	}

	public static String getRates(String convertToCountry) {
		String convertRate = null;
		JsonElement root = new JsonParser().parse(json);

		if (convertToCountry == "CAD") {
			convertRate = root.getAsJsonObject().get("rates").getAsJsonObject().get(convertToCountry).getAsString();
		} else if (convertToCountry == "USD") {
			convertRate = root.getAsJsonObject().get("rates").getAsJsonObject().get(convertToCountry).getAsString();
		} else if (convertToCountry == "GBP") {
			convertRate = root.getAsJsonObject().get("rates").getAsJsonObject().get(convertToCountry).getAsString();
		} else if (convertToCountry == "CNY") {
			convertRate = root.getAsJsonObject().get("rates").getAsJsonObject().get(convertToCountry).getAsString();
		} else if (convertToCountry == "JPY") {
			convertRate = root.getAsJsonObject().get("rates").getAsJsonObject().get(convertToCountry).getAsString();
		}
		return convertRate;
	}

}
