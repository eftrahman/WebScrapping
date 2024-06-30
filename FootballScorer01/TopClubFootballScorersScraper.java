import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class TopClubFootballScorersScraper {

    public static void main(String[] args) {
        List<FootballScorer> scorers = new ArrayList<>();

        try {
            // Connect to the top club football scorers page
            Document doc = Jsoup.connect("https://www.example.com/top-club-football-scorers")
                                .timeout(6000).get();
            
            // Select the table rows that contain the player data
            Elements scorerRows = doc.select("table.scores tbody tr");

            // Iterate over each row
            for (Element row : scorerRows) {
                // Extract the player name, goals, and club
                Element nameElement = row.select("td.name").first();
                Element goalsElement = row.select("td.goals").first();
                Element clubElement = row.select("td.club").first();

                if (nameElement != null && goalsElement != null && clubElement != null) {
                    String name = nameElement.text();
                    String goals = goalsElement.text();
                    String club = clubElement.text();

                    // Create a new FootballScorer object and add it to the list
                    scorers.add(new FootballScorer(name, goals, club));
                }
            }

            // Write the scorers to a CSV file
            writeScorersToFile(scorers, "top_club_football_scorers.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeScorersToFile(List<FootballScorer> scorers, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Name,Goals,Club");
            for (FootballScorer scorer : scorers) {
                writer.printf("\"%s\",\"%s\",\"%s\"%n", scorer.getName(), scorer.getGoals(), scorer.getClub());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

