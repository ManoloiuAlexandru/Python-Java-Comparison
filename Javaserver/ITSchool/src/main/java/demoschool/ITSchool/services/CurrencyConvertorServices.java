package demoschool.ITSchool.services;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import demoschool.ITSchool.data.CurrencyToConvert;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CurrencyConvertorServices {

	public HashMap<String, String> getCurrencyConverting() throws IOException, InterruptedException {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest.newBuilder().uri(URI.create("http://127.0.0.1:8081/currencys")).build();

		HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
		Map<String, String> currencyMap = new Gson().fromJson(response.body(),
				new TypeToken<HashMap<String, String>>() {
				}.getType());
	return (HashMap<String, String>) currencyMap;
	}

	public String CurrencyConverting(CurrencyToConvert currencyToConvert) throws IOException, InterruptedException {
		HashMap<String, String> currency_dict=this.getCurrencyConverting();
		return currencyToConvert.getCurrency()+ " " +String.valueOf(Float.parseFloat(currency_dict.get(currencyToConvert.getCurrency()))/Float.parseFloat(currency_dict.get(currencyToConvert.getCurrencyToConvertTo()))*currencyToConvert.getAmount())+" = "+currencyToConvert.getCurrencyToConvertTo();
	}
}
