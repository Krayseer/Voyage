package ru.krayseer.voyage.domain.repositories;

import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.krayseer.voyage.domain.entities.Trip;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {

    List<Car> findCarsByAccount(Account account);

}
