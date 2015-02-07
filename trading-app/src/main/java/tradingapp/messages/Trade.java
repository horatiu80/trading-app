package tradingapp.messages;

import java.io.Serializable;

public class Trade implements Serializable {

	private static final long serialVersionUID = 8727619469422245992L;
	
	private long userId;
    private String currencyFrom;
	private String currencyTo;
	private float amountSell;
	private float amountBuy;
	private float rate;
	private String timePlaced;
	private String originatingCountry;

	public Trade() {
	}

	public Trade(long userId, String currencyFrom, String currencyTo,
			float amountSell, float amountBuy, float rate, String timePlaced,
			String originatingCountry) {
		this.userId = userId;
		this.currencyFrom = currencyFrom;
		this.currencyTo = currencyTo;
		this.amountSell = amountSell;
		this.amountBuy = amountBuy;
		this.rate = rate;
		this.timePlaced = timePlaced;
		this.originatingCountry = originatingCountry;
	}


    public long getUserId() {
        return userId;
    }

    public String getCurrencyFrom() {
        return currencyFrom;
    }
    
	public String getCurrencyTo() {
		return currencyTo;
	}

	public float getAmountSell() {
		return amountSell;
	}

	public float getAmountBuy() {
		return amountBuy;
	}

	public float getRate() {
		return rate;
	}

	public String getTimePlaced() {
		return timePlaced;
	}

	public String getOriginatingCountry() {
		return originatingCountry;
	}
}
