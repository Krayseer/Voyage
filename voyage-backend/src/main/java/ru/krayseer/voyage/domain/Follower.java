package ru.krayseer.voyage.domain;

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
    private String accountUsername;

}
