public class FootballScorer {
    private String name;
    private String goals;
    private String club;

    public FootballScorer(String name, String goals, String club) {
        this.name = name;
        this.goals = goals;
        this.club = club;
    }

    public String getName() {
        return name;
    }

    public String getGoals() {
        return goals;
    }

    public String getClub() {
        return club;
    }
}

