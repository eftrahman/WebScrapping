public class ExchangeRate {
    private String currency;
    private String rate;

    public ExchangeRate(String currency, String rate) {
        this.currency = currency;
        this.rate = rate;
    }

    public String getCurrency() {
        return currency;
    }

    public String getRate() {
        return rate;
    }
}
