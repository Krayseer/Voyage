package ru.krayseer.voyage.domain.repositories;

import org.springframework.stereotype.Repository;
import ru.krayseer.voyage.domain.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByPhoneNumber(String phoneNumber);

    Optional<Account> findByUsername(String username);

}
