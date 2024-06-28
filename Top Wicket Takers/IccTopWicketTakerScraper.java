import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IccTopWicketTakersScraper {

    public static void main(String[] args) {
        List<WicketTaker> wicketTakers = new ArrayList<>();

        try {
            // Connect to the ICC top wicket-takers page
            Document doc = Jsoup.connect("https://www.icc-cricket.com/rankings/mens/player-rankings/odi/bowling")
                                .timeout(6000).get();
            
            // Select the table rows that contain the player data
            Elements playerRows = doc.select("table.table tbody tr");

            // Iterate over each row
            for (Element row : playerRows) {
                // Extract the player name, wickets, and country
                Element nameElement = row.select("td.name").first();
                Element wicketsElement = row.select("td.wickets").first();
                Element countryElement = row.select("span.rankings-block__banner--country").first();

                if (nameElement != null && wicketsElement != null && countryElement != null) {
                    String name = nameElement.text();
                    String wickets = wicketsElement.text();
                    String country = countryElement.text();

                    // Create a new WicketTaker object and add it to the list
                    wicketTakers.add(new WicketTaker(name, wickets, country));
                }
            }

            // Write the wicket takers to a CSV file
            writeWicketTakersToFile(wicketTakers, "top_wicket_takers.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeWicketTakersToFile(List<WicketTaker> wicketTakers, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name,Wickets,Country");
            for (WicketTaker wicketTaker : wicketTakers) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n", wicketTaker.getName(), wicketTaker.getWickets(), wicketTaker.getCountry());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
