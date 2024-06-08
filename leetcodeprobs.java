import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class LeetCodeScraper {

    public static void main(String[] args) {
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
                
                // Print the problem title and URL
                System.out.println("Title: " + title);
                System.out.println("URL: " + url);
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
