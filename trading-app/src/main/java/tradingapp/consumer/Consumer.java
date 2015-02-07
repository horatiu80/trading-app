package tradingapp.consumer;

import javax.jms.JMSException;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tradingapp.messages.Trade;
import tradingapp.processor.Processor;

@RestController
public class Consumer {
    
    @Autowired
    private ConfigurableApplicationContext appContext;
    
    @RequestMapping(value = "/consumer", method = RequestMethod.POST)
    @ResponseBody
    public Trade consume(@RequestBody Trade trade) {

    	final Trade tradeForMsg = trade;
    	MessageCreator messageCreator = new MessageCreator() {
            @Override
            public javax.jms.Message createMessage(Session session) throws JMSException {
            	return session.createObjectMessage(tradeForMsg);
            }
        };
        
    	JmsTemplate jmsTemplate = appContext.getBean(JmsTemplate.class);
        jmsTemplate.send(Processor.MSG_DESTINATION_NAME, messageCreator);

        return trade;
    }
}
