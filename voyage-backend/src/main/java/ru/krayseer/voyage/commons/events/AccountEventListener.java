package ru.krayseer.voyage.commons.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountEventListener {

    @EventListener
    public void handleAccountEvent(DeleteTripEvent event) {
        log.info("Delete trip: {}", event.getTrip().toString());
    }

}
