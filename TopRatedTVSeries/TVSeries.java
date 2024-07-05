public class TVSeries {
    private String title;
    private String rating;
    private String year;

    public TVSeries(String title, String rating, String year) {
        this.title = title;
        this.rating = rating;
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public String getRating() {
        return rating;
    }

    public String getYear() {
        return year;
    }
}

