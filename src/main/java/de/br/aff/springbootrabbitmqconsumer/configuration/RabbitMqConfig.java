package de.br.aff.springbootrabbitmqconsumer.configuration;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMqConfig {

    @Value("${spring.rabbitmq.exchange}")
    private String exchangeName; //exchange to which messages are produced

    @Value("${spring.rabbitmq.queue}")
    private String queueName; //queue that is binded to exchange and from which we consume messages

    @Value("${spring.rabbitmq.routing-key}")
    private String routingKey; //routing key for binding exchange and the queue


    @Bean
    protected TopicExchange sourceExchange() {
        return new TopicExchange(exchangeName);
    }


    @Bean
    protected Queue sourceQueue() {
        return QueueBuilder.durable(queueName)
                .build();
    }


    @Bean
    protected Binding sourceBinding() {
        return BindingBuilder.bind(sourceQueue())
                .to(sourceExchange())
                .with(routingKey);
    }

    @Bean
    protected Jackson2JsonMessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }


    @Bean
    protected SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(jsonMessageConverter());
        return factory;
    }
}
