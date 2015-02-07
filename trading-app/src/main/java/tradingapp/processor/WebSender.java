package tradingapp.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import tradingapp.messages.Trade;
import tradingapp.web.WebData;
import tradingapp.web.TotalWebData;

@Service
public class WebSender {
	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public WebSender(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	public void send(long t) {		
		this.messagingTemplate.convertAndSend("/topic/trading", new WebData(t + ""));
	}
	
	public void send(Trade trade) {		
		this.messagingTemplate.convertAndSend("/topic/trading", trade);
	}
	
	public void send(TotalWebData totalWebData) {		
		this.messagingTemplate.convertAndSend("/topic/trading", totalWebData);
	}
}
