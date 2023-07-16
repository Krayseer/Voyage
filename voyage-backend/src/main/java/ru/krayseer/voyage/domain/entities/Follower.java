package ru.krayseer.voyage.domain.entities;

import jakarta.persistence.*;
import lombok.*;


@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Поездка, на которую подписывается попутчик
     */
    @ManyToOne
    @JoinColumn(name = "TRIP_ID")
    private Trip trip;

    /**
     * Попутчик
     */
    @ManyToOne
    @JoinColumn(name = "ACCOUNT_ID")
    private Account account;

}
