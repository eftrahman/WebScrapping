public class WicketTaker {
    private String name;
    private String wickets;
    private String country;

    public WicketTaker(String name, String wickets, String country) {
        this.name = name;
        this.wickets = wickets;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getWickets() {
        return wickets;
    }

    public String getCountry() {
        return country;
    }
}
