package ru.krayseer.voyage.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {

//    private final AmqpAdmin amqpAdmin;
//
//    @Bean
//    public Jackson2JsonMessageConverter jackson2Converter(ObjectMapper mapper) {
//        return new Jackson2JsonMessageConverter(mapper);
//    }
//
//    @Bean
//    public RabbitTemplate jsonRabbitTemplate(ConnectionFactory connectionFactory, MessageConverter converter) {
//        final var jsonRabbitTemplate = new RabbitTemplate(connectionFactory);
//        jsonRabbitTemplate.setMessageConverter(converter);
//        return jsonRabbitTemplate;
//    }
//
//    @PostConstruct
//    public void createQueues() {
//        amqpAdmin.declareQueue(new Queue("photoQueue", true));
//    }

}