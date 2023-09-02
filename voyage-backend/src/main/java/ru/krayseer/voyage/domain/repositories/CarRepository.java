package ru.krayseer.voyage.domain.repositories;

import ru.krayseer.voyage.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByAccountUsername(String account);

}
