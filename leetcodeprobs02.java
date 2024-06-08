public class Problem {
    private String title;
    private String url;

    public Problem(String title, String url) {
        this.title = title;
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }
}

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LeetCodeScraper {

    public static void main(String[] args) {
        List<Problem> problems = new ArrayList<>();

        try {
            // Connect to the LeetCode problemset page
            Document doc = Jsoup.connect("https://leetcode.com/problemset/all/")
                                .timeout(6000).get();
            
            // Select the table rows that contain the problems
            Elements problemRows = doc.select("div.table-responsive tbody tr");
            
            // Iterate over each problem row
            for (Element row : problemRows) {
                // Extract the problem title and URL
                Element titleElement = row.select("td:nth-child(3) a").first();
                String title = titleElement.text();
                String url = "https://leetcode.com" + titleElement.attr("href");

                // Create a new Problem object and add it to the list
                problems.add(new Problem(title, url));
            }

            // Write the problems to a CSV file
            writeProblemsToFile(problems, "problems.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeProblemsToFile(List<Problem> problems, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Title,URL");
            for (Problem problem : problems) {
                writer.printf("\"%s\",\"%s\"%n", problem.getTitle(), problem.getUrl());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

