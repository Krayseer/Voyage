package ru.krayseer.voyage.commons.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;
import ru.krayseer.voyage.domain.entities.Trip;

@Getter
public class DeleteTripEvent extends ApplicationEvent {

    private final Trip trip;

    public DeleteTripEvent(Object source, Trip trip) {
        super(source);
        this.trip = trip;
    }

}
