public class Weather {
    private String city;
    private String temperature;
    private String condition;

    public Weather(String city, String temperature, String condition) {
        this.city = city;
        this.temperature = temperature;
        this.condition = condition;
    }

    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getCondition() {
        return condition;
    }
}
