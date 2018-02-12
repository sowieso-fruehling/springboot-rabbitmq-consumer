package de.br.aff.springbootrabbitmqconsumer.listener;

import de.br.aff.springbootrabbitmqconsumer.domain.Profile;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class Receiver {
    @RabbitListener(queues = "${spring.rabbitmq.queue}")  //queue to consume from
    public void receiveMessage(Profile profile) throws Exception {
       log.info("Received message {}", profile);
    }
}