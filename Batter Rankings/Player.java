public class Player {
    private String name;
    private String runs;
    private String country;

    public Player(String name, String runs, String country) {
        this.name = name;
        this.runs = runs;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public String getRuns() {
        return runs;
    }

    public String getCountry() {
        return country;
    }
}
