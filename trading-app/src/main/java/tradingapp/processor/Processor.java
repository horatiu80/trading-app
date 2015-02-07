package tradingapp.processor;

import java.io.Serializable;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;

import tradingapp.messages.Trade;
import tradingapp.web.TotalWebData;

public class Processor {
	
	public static final String MSG_DESTINATION_NAME = "processor";
	
	private final HashMap<String, Float> totalBuying = new HashMap<String, Float>();
	private final HashMap<String, Float> totalSelling = new HashMap<String, Float>();

    @Autowired
    ConfigurableApplicationContext context;
    
    @Autowired
    private WebSender webSender;
    
    public void receiveMessage(Serializable message) {
    	processMessage((Trade) message);
    }
    
    private void processMessage(Trade trade) {    	
    	calcTotalBuying(trade);
    	calcTotalSelling(trade);
    	webSender.send(trade);    	
    }
    
    private void calcTotalBuying(Trade trade) {
    	if (totalBuying.get(trade.getCurrencyFrom()) == null) {
    		totalBuying.put(trade.getCurrencyFrom(), trade.getAmountBuy());
    		webSender.send(new TotalWebData("buy", trade.getCurrencyFrom(), trade.getAmountBuy()));
    	}
    	else {
    		Float total = totalBuying.get(trade.getCurrencyFrom()) + trade.getAmountBuy();
    		totalBuying.put(trade.getCurrencyFrom(), total);
    		webSender.send(new TotalWebData("buy", trade.getCurrencyFrom(), total));
    	}
    	
    	if (totalBuying.get(trade.getCurrencyTo()) == null) {
    		totalBuying.put(trade.getCurrencyTo(), (trade.getAmountSell() / trade.getRate()));
    		webSender.send(new TotalWebData("buy", trade.getCurrencyTo(), (trade.getAmountSell() / trade.getRate())));
    	}
    	else {
    		Float total = totalBuying.get(trade.getCurrencyTo()) + (trade.getAmountSell() / trade.getRate());
    		totalBuying.put(trade.getCurrencyTo(), total);
    		webSender.send(new TotalWebData("buy", trade.getCurrencyTo(), total));
    	}
    }
    
    private void calcTotalSelling(Trade trade) {
    	if (totalSelling.get(trade.getCurrencyFrom()) == null) {
    		totalSelling.put(trade.getCurrencyFrom(), trade.getAmountSell());
    		webSender.send(new TotalWebData("sell", trade.getCurrencyFrom(), trade.getAmountSell()));
    	}
    	else {
    		Float total = totalSelling.get(trade.getCurrencyFrom()) + trade.getAmountSell();
    		totalSelling.put(trade.getCurrencyFrom(), total);
    		webSender.send(new TotalWebData("sell", trade.getCurrencyFrom(), total));
    	}
    	
    	if (totalSelling.get(trade.getCurrencyTo()) == null) {
    		totalSelling.put(trade.getCurrencyTo(), (trade.getAmountBuy() * trade.getRate()));
    		webSender.send(new TotalWebData("sell", trade.getCurrencyTo(), (trade.getAmountBuy() * trade.getRate())));
    	}
    	else {
    		Float total = totalSelling.get(trade.getCurrencyTo()) + (trade.getAmountBuy() * trade.getRate());
    		totalSelling.put(trade.getCurrencyTo(), total);
    		webSender.send(new TotalWebData("sell", trade.getCurrencyTo(), total));
    	}
    }
}
