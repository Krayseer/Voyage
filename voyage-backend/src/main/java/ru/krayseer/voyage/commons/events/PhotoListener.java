package ru.krayseer.voyage.commons.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class PhotoListener {

    @RabbitListener(queues = "photo_queue")
    public void getListener(String message) {
        log.info("Message from photo-storage: {}", message);
    }

}

