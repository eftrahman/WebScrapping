import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopWTATennisPlayerRankingsScraper {

    public static void main(String[] args) {
        List<TennisPlayer> players = new ArrayList<>();

        try {
            // Connect to the WTA top women's singles rankings page
            Document doc = Jsoup.connect("https://www.wtatennis.com/rankings/singles")
                                .timeout(6000).get();
            
            // Select the table rows that contain the player data
            Elements playerRows = doc.select("table.mega-table tbody tr");

            // Iterate over each row
            for (Element row : playerRows) {
                // Extract the player name, ranking, and points
                Element rankingElement = row.select("td.rank-cell").first();
                Element nameElement = row.select("td.player-cell a").first();
                Element pointsElement = row.select("td.points-cell").first();

                if (rankingElement != null && nameElement != null && pointsElement != null) {
                    String ranking = rankingElement.text().trim();
                    String name = nameElement.text().trim();
                    String points = pointsElement.text().trim().replaceAll(",", "");

                    // Create a new TennisPlayer object and add it to the list
                    players.add(new TennisPlayer(name, ranking, points));
                }
            }

            // Write the players to a CSV file
            writePlayersToFile(players, "top_wta_tennis_player_rankings.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writePlayersToFile(List<TennisPlayer> players, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Ranking,Name,Points");
            for (TennisPlayer player : players) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n", player.getRanking(), player.getName(), player.getPoints());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
