import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class IccTopMenScorerScraper {

    public static void main(String[] args) {
        List<Player> players = new ArrayList<>();

        try {
            // Connect to the ICC top men's scorers page
            Document doc = Jsoup.connect("https://www.icc-cricket.com/rankings/mens/player-rankings/odi/batting")
                                .timeout(6000).get();
            
            // Select the table rows that contain the player data
            Elements playerRows = doc.select("table.table tbody tr");

            // Iterate over each row
            for (Element row : playerRows) {
                // Extract the player name, runs, and country
                Element nameElement = row.select("td.name").first();
                Element runsElement = row.select("td.runs").first();
                Element countryElement = row.select("span.rankings-block__banner--country").first();

                if (nameElement != null && runsElement != null && countryElement != null) {
                    String name = nameElement.text();
                    String runs = runsElement.text();
                    String country = countryElement.text();

                    // Create a new Player object and add it to the list
                    players.add(new Player(name, runs, country));
                }
            }

            // Write the players to a CSV file
            writePlayersToFile(players, "top_men_scorers.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePlayersToFile(List<Player> players, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name,Runs,Country");
            for (Player player : players) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n", player.getName(), player.getRuns(), player.getCountry());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
