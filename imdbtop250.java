import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

class Movie {
    String img;
    String title;
    String year;
    String rate;

    public Movie(String img, String title, String year, String rate) {
        this.img = img;
        this.title = title;
        this.year = year;
        this.rate = rate;
    }
}

public class JsoupRun {

    public static void main(String[] args) {
        try {
            Document doc = Jsoup.connect("https://www.imdb.com/chart/top")
                    .timeout(6000).get();
            Elements body = doc.select("tbody.lister-list");
            ArrayList<Movie> movies = new ArrayList<>();
            
            for (Element e : body.select("tr")) {
                String img = e.select("td.posterColumn img").attr("src");
                String title = e.select("td.posterColumn img").attr("alt");
                String year = e.select("td.titleColumn span.secondaryInfo")
                        .text().replaceAll("[^\\d]", "");
                String rate = e.select("td.ratingColumn.imdbRating").text().trim();
                movies.add(new Movie(img, title, year, rate));
            }

            writeMoviesToFile(movies, "movies.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeMoviesToFile(ArrayList<Movie> movies, String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            writer.println("Image,Title,Year,Rating");
            for (Movie movie : movies) {
                writer.printf("%s,%s,%s,%s%n", movie.img, movie.title, movie.year, movie.rate);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
