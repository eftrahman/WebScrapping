import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopRatedTVSeriesScraper {

    public static void main(String[] args) {
        List<Movie> TVSeries = new ArrayList<>();

        try {
            // Connect to the IMDb top rated movies page
            Document doc = Jsoup.connect("www.imdb.com/chart/toptv")
                                .timeout(6000).get();
            
            // Select the table rows that contain the movie data
            Elements TVSeriesRows = doc.select("table.chart.full-width tbody.lister-list tr");

            // Iterate over each row
            for (Element row : movieRows) {
                // Extract the movie title, rating, and year
                Element titleElement = row.select("td.titleColumn a").first();
                Element ratingElement = row.select("td.imdbRating strong").first();
                Element yearElement = row.select("span.secondaryInfo").first();

                if (titleElement != null && ratingElement != null && yearElement != null) {
                    String title = titleElement.text();
                    String rating = ratingElement.text();
                    String year = yearElement.text().replaceAll("[^\\d]", "");

                    // Create a new Movie object and add it

