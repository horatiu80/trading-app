package tradingapp;

import java.io.File;

import javax.jms.ConnectionFactory;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jms.listener.SimpleMessageListenerContainer;
import org.springframework.jms.listener.adapter.MessageListenerAdapter;
import org.springframework.util.FileSystemUtils;

import tradingapp.processor.Processor;

@ComponentScan
@EnableAutoConfiguration
public class Application {			
	
	@Bean
    Processor receiver() {
        return new Processor();
    }

    @Bean
    MessageListenerAdapter adapter(Processor receiver) {
        MessageListenerAdapter messageListener
                = new MessageListenerAdapter(receiver);
        messageListener.setDefaultListenerMethod("receiveMessage");
        return messageListener;
    }

    @Bean
    SimpleMessageListenerContainer container(MessageListenerAdapter messageListener,
                                             ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
        container.setMessageListener(messageListener);
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName(Processor.MSG_DESTINATION_NAME);
        return container;
    }

    public static void main(String[] args) {
    	FileSystemUtils.deleteRecursively(new File("activemq-data"));
        SpringApplication.run(Application.class, args);
    }
}
