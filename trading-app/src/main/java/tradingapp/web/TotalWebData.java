package tradingapp.web;

public class TotalWebData {
    
    private String content;
    private String currency;
    private float value;

    public TotalWebData(String content, String currency, float value) {
        this.content = content;
        this.currency = currency;
        this.value = value;
    }

    public String getContent() {
        return content;
    }
    
    public String getCurrency() {
        return currency;
    }
    
    public float getValue() {
        return value;
    }

}
