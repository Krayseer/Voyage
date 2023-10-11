package ru.krayseer.voyage.repositories;

import ru.krayseer.voyage.domain.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
}
