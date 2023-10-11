package ru.krayseer.voyage.repositories;

import ru.krayseer.voyage.domain.Car;
import ru.krayseer.voyage.domain.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findTripByCar(Car car);

}
