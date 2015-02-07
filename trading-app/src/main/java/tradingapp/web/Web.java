package tradingapp.web;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import tradingapp.consumer.TestConsumer;

@Controller
public class Web {
	
    @MessageMapping("/mweb")
    @SendTo("/topic/trading")
    public WebData mweb(WebMessage message) throws Exception {
    	if (message.getName().equals("test")) {
    		test();
    		return new WebData("doNothing");
    	}
        return new WebData("Hello, " + message.getName() + "!");
    }
        
    private void test() {
    	new Thread() {
    		@Override
    		public void run() {
    			TestConsumer.test();
    		}
    	}.start();
    }
}
