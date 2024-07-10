public class TennisPlayer {
    private String name;
    private String ranking;
    private String points;

    public TennisPlayer(String name, String ranking, String points) {
        this.name = name;
        this.ranking = ranking;
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public String getRanking() {
        return ranking;
    }

    public String getPoints() {
        return points;
    }
}
