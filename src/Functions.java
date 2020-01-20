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
	static String finalCurrencySign = "$";
	static double result;

	// Sends a GET Request to a free exchange rate api and retrieves the updated
	// currency rates.
	public static void getRequest() throws Exception {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder()
				.uri(URI.create("https://api.exchangeratesapi.io/latest?base=CAD&symbols=USD,GBP,CNY")).build();

		HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

		json = response.body();
	}

	//
	public static double convertTo(int cfrom, int cto, double money) throws Exception {
		getRequest();
		System.out.println(json);

		getRates(cfrom, cto, money);

		result = money / rate2;

		System.out.println(result);
		return result;

	}

	public static void getRates(int cfrom, int cto, double tempmoney) {

		JsonElement root = new JsonParser().parse(json);

		// canada
		if (cfrom == 0) {
			rate = 1.00;

		}

		// usa
		else if (cfrom == 1) {
			
			
			String USD = root.getAsJsonObject().get("rates").getAsJsonObject().get("USD").getAsString();
			rate = Double.parseDouble(USD);
			tempmoney = tempmoney * rate;
			
			System.out.println(tempmoney);
		}

		// great britain
		else if (cfrom == 2) {
			String GBP = root.getAsJsonObject().get("rates").getAsJsonObject().get("GBP").getAsString();
			rate = 2 - Double.parseDouble(GBP);
			System.out.println(rate);

		}
		// china
		else if (cfrom == 3) {
			String CNY = root.getAsJsonObject().get("rates").getAsJsonObject().get("CNY").getAsString();
			rate = 2 - Double.parseDouble(CNY);
			System.out.println(rate);

		}

		if (cto == 0) {
			rate2 = 1.00;
			finalCurrencySign = "$";

		}

		// usa
		else if (cto == 1) {
			String USD = root.getAsJsonObject().get("rates").getAsJsonObject().get("USD").getAsString();
			rate2 = Double.parseDouble(USD);
			System.out.println(rate2);
			finalCurrencySign = "$";

		}

		// great britain
		else if (cto == 2) {
			String GBP = root.getAsJsonObject().get("rates").getAsJsonObject().get("GBP").getAsString();
			rate2 = Double.parseDouble(GBP);
			System.out.println(rate2);
			finalCurrencySign = "£";
			
//			tempmoney = tempmoney / rate2;
//			System.out.println(tempmoney);

		}
		// china
		else if (cto == 3) {
			String CNY = root.getAsJsonObject().get("rates").getAsJsonObject().get("CNY").getAsString();
			rate2 = Double.parseDouble(CNY);
			System.out.println(rate2);
			finalCurrencySign = "¥";

		}

	}

}
