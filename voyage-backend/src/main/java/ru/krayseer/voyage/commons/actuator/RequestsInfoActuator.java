package ru.krayseer.voyage.commons.actuator;

import io.micrometer.core.instrument.Counter;
import io.micrometer.core.instrument.MeterRegistry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

@Component
@RequiredArgsConstructor
public class RequestsInfoActuator {

    private final ConcurrentHashMap<String, Counter> countRequests = new ConcurrentHashMap<>();

    private final MeterRegistry meterRegistry;

    public void addCountRequest(String endpoint) {
        Counter counter = countRequests.computeIfAbsent(endpoint, key -> {
            Counter newCounter = Counter.builder("requests_info.count")
                    .tag("endpoint", key)
                    .register(meterRegistry);
            newCounter.increment();
            return newCounter;
        });
        counter.increment();
    }

}
