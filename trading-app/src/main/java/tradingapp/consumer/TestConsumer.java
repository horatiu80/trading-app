package tradingapp.consumer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import org.json.simple.JSONObject;

public class TestConsumer {

	private static ArrayList<String> countries = new ArrayList<String>();
	private static ArrayList<String> currency = new ArrayList<String>();
	
	public static void test() 
	{
		
		countries.add("UK");
		countries.add("RO");
		countries.add("FR");
		countries.add("US");
		countries.add("ES");
		countries.add("IT");
		countries.add("DE");
		
		currency.add("GBP");
		currency.add("RON");
		currency.add("EUR");
		currency.add("USD");
		currency.add("CAD");
		currency.add("JPY");
		currency.add("NZD");
		currency.add("CHF");
		
		for (int i = 1; i<2000; i++)
			sendPostMessage();
		System.out.println("end");

	}
	
	@SuppressWarnings("unchecked")
	public static void sendPostMessage()  
	{			
		Random myRandomizer = new Random();
		HttpURLConnection con;
		
		URL obj;
		try {
			
			obj = new URL("http://localhost:8080/consumer");
			con = (HttpURLConnection) obj.openConnection();
			
			con.setDoOutput(true);
			con.setDoInput(true);
			
			con.setRequestProperty("Content-Type", "application/json; charset=utf8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestMethod("POST");
						
			JSONObject parent=new JSONObject();
			parent.put("userId", 134256 + myRandomizer.nextInt(100));
			
			String currencyFrom = currency.get(myRandomizer.nextInt(currency.size()));
			String currencyTo = currency.get(myRandomizer.nextInt(currency.size()));
			
			while (currencyFrom.equals(currencyTo)) {
				currencyTo = currency.get(myRandomizer.nextInt(currency.size()));
			}
			
			parent.put("currencyFrom",  currencyFrom);
			parent.put("currencyTo", currencyTo);
			parent.put("amountSell", myRandomizer.nextInt(5000) + myRandomizer.nextInt(2)*((float)(myRandomizer.nextInt(10) + 1)/10));
			parent.put("amountBuy", myRandomizer.nextInt(5000) + myRandomizer.nextInt(2)*((float)(myRandomizer.nextInt(10) + 1)/10));
			parent.put("rate", myRandomizer.nextInt(10) + ((float)(myRandomizer.nextInt(10000) + 1)/10000));
			parent.put("timePlaced", "14-JAN-15 10:27:44");
			parent.put("originatingCountry", countries.get(myRandomizer.nextInt(countries.size())));

			OutputStream os = con.getOutputStream();
			os.write(parent.toString().getBytes("UTF-8"));
			con.getInputStream();
							
		} 
		catch (IOException e2) {
			e2.printStackTrace();
		}
	} 
}
