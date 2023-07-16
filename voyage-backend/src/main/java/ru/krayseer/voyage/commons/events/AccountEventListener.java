package ru.krayseer.voyage.commons.events;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.entities.Trip;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
@Slf4j
public class AccountEventListener {

    @EventListener
    public void handleAccountEvent(DeleteTripEvent event) {
        log.info("Delete trip: {}", event.getTrip().toString());
    }

}
