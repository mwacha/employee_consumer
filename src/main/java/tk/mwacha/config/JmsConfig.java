package tk.mwacha.config;

import com.amazon.sqs.javamessaging.ProviderConfiguration;
import com.amazon.sqs.javamessaging.SQSConnectionFactory;
import com.amazonaws.services.sqs.AmazonSQS;
import javax.jms.Session;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.support.destination.DynamicDestinationResolver;

@Configuration
@EnableJms
public class JmsConfig {

    @Bean
    public DefaultJmsListenerContainerFactory jmsListenerContainerFactory(
            SQSConnectionFactory sqsConnectionFactory) {
        final DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(sqsConnectionFactory);
        factory.setDestinationResolver(new DynamicDestinationResolver());
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);

        return factory;
    }

    @Bean
    public SQSConnectionFactory createSQSConnectionFactoryLocal(final AmazonSQS amazonSQS) {
        return new SQSConnectionFactory(new ProviderConfiguration(), amazonSQS);
    }
}
