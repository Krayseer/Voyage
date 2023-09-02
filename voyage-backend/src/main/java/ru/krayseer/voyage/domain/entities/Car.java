package ru.krayseer.voyage.domain.entities;

import lombok.*;

import jakarta.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Марка автомобиля
     */
    @Column(nullable = false)
    private String mark;

    /**
     * Цвет автомобиля
     */
    private String color;

    /**
     * Регистрационный номер
     */
    @Column(nullable = false, unique = true)
    private String licensePlate;

    /**
     * Хозяин автомобиля
     */
    private String accountUsername;

}
