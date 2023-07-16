package ru.krayseer.voyage.domain.repositories;

import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyage.domain.entities.Trip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TripRepository extends JpaRepository<Trip, Long> {

    Optional<Trip> findTripByCar(Car car);

}
