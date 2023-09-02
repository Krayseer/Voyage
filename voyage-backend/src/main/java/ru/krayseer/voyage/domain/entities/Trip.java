package ru.krayseer.voyage.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Цена поездки
     */
    @Column(nullable = false)
    private Integer price;

    /**
     * Адрес откуда выезжает водитель
     */
    @Column(nullable = false)
    private String addressFrom;

    /**
     * Адрес куда едет водитель
     */
    @Column(nullable = false)
    private String addressTo;

    /**
     * Допустимое количество пассажиров в поездке
     */
    private Integer countSeats;

    /**
     * Дата и время начала поездки
     */
    @Column(nullable = false)
    private LocalDateTime timeTrip;

    /**
     * Водитель
     */
    private String driverUsername;

    /**
     * Попутчики
     */
    @OneToMany(mappedBy = "trip", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Follower> followers;

    /**
     * Автомобиль, который выбрал водитель
     */
    @OneToOne
    @JoinColumn(name = "CAR_ID")
    private Car car;

}
