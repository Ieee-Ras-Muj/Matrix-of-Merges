import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;
import java.util.Scanner;

public class WeatherApplication {
    
    private static final String API_KEY = "your_api_key"; // Deliberate Issue: Use actual API key
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the city name: ");
        String city = scanner.nextLine();
        
        try {
            String weatherData = getWeatherData(city);
            if (weatherData != null) {
                parseAndDisplayWeather(weatherData);
            } else {
                System.out.println("Could not fetch weather data.");
            }
        } catch (Exception e) {
            System.out.println("An error occurred: " + e.getMessage());
        }

        scanner.close();
    }
    
    private static String getWeatherData(String city) throws Exception {
        String urlString = "http://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + API_KEY + "&units=metric";
        
        URL url = new URL(urlString);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();
            
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            System.out.println("Error: Could not retrieve data (Response Code: " + responseCode + ")");
            return null;
        }
    }
    
    // Parse and display the weather data
    private static void parseAndDisplayWeather(String jsonResponse) {
        JSONObject jsonObject = new JSONObject(jsonResponse);
        
        String city = jsonObject.getString("name");
        JSONObject main = jsonObject.getJSONObject("main");
        double temperature = main.getDouble("temp");
        double feelsLike = main.getDouble("feels_like");
        int humidity = main.getInt("humidity");
        
        JSONObject weatherObject = jsonObject.getJSONArray("weather").getJSONObject(0);
        String description = weatherObject.getString("description");
        
        System.out.println("Weather in " + city + ":");
        System.out.println("Temperature: " + temperature + "°C");
        System.out.println("Feels Like: " + feelsLike + "°C");
        System.out.println("Humidity: " + humidity + "%");
        System.out.println("Description: " + description);
    }
}
