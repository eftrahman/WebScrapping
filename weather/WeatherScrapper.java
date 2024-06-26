import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class WeatherScraper {

    public static void main(String[] args) {
        List<Weather> weatherList = new ArrayList<>();

        try {
            // Specify the city to scrape weather information for
            String city = "San Francisco";
            // Replace spaces with dashes for the URL
            String cityUrl = city.toLowerCase().replace(" ", "-");

            // Connect to the weather website's page for the specified city
            Document doc = Jsoup.connect("https://weather.com/weather/today/l/" + cityUrl)
                                .timeout(6000).get();

            // Select elements that contain the weather information
            Element temperatureElement = doc.select("span.CurrentConditions--tempValue--3KcTQ").first();
            Element conditionElement = doc.select("div.CurrentConditions--phraseValue--2xXSr").first();

            // Extract the temperature and condition
            String temperature = temperatureElement.text();
            String condition = conditionElement.text();

            // Create a new Weather object and add it to the list
            weatherList.add(new Weather(city, temperature, condition));

            // Write the weather information to a CSV file
            writeWeatherToFile(weatherList, "weather.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeWeatherToFile(List<Weather> weatherList, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("City,Temperature,Condition");
            for (Weather weather : weatherList) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n", weather.getCity(), weather.getTemperature(), weather.getCondition());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
