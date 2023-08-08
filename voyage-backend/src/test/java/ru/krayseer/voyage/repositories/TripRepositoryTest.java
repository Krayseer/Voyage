package ru.krayseer.voyage.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.krayseer.voyage.VoyageApplication;
import ru.krayseer.voyage.commons.constants.Role;
import ru.krayseer.voyage.context.ContainersEnvironment;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.entities.Car;
import ru.krayseer.voyage.domain.entities.Trip;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.domain.repositories.CarRepository;
import ru.krayseer.voyage.domain.repositories.TripRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = VoyageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class TripRepositoryTest extends ContainersEnvironment {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private TripRepository tripRepository;

    private Car driverCar;

    private Account driver;

    private Account user;

    @BeforeEach
    public void setup() {
        user = createAccount("urepa", "ijnbgr1245", "Andrew", 25,
                "+79124251522", "rus@yandex.ru");
        driver = createAccount("coolDriver", "poiiop1551", "Daniel", 21,
                "+79995992959", "driv@gmail.com");
        driverCar = createCar("solaris", "green", "А212КЕ", driver);
    }

    @Test
    public void createTripTest() {
        var trip = createTrip(500, "Ekb", "Moscow", 3, LocalDateTime.of(2023, 7, 1, 8, 30, 0), driver, driverCar);

        assertNotNull(trip);
        assertEquals(500, trip.getPrice());
        assertEquals("Ekb", trip.getAddressFrom());
        assertEquals("Moscow", trip.getAddressTo());
        assertEquals(3, trip.getCountSeats());
        assertEquals(LocalDateTime.of(2023, 7, 1, 8, 30, 0), trip.getTimeTrip());
        assertEquals(driver, trip.getDriver());
        assertEquals(driverCar, trip.getCar());
    }

    @Test
    public void getAllTripsTest() {
        var userCar = createCar("accent", "black", "Н222КН211", user);
        var tripByDriver = createTrip(500, "Ekb", "Moscow", 3, LocalDateTime.of(2023, 7, 1, 8, 30, 0), driver, driverCar);
        var tripByUser = createTrip(200, "Tumen", "Krasnodar", 2, LocalDateTime.of(2023, 7, 3, 8, 0, 0), user, userCar);

        assertNotNull(tripByUser);
        assertNotNull(tripByDriver);
        assertEquals(2, tripRepository.findAll().size());
    }

    @Test
    public void findTripByCarTest() {
        createTrip(500, "Ekb", "Moscow", 3, LocalDateTime.of(2023, 7, 1, 8, 30, 0), driver, driverCar);

        var foundTrip = tripRepository.findTripByCar(driverCar);
        assertNotNull(foundTrip);
        foundTrip.ifPresent(trip -> {
            assertEquals(500, trip.getPrice());
            assertEquals("Ekb", trip.getAddressFrom());
            assertEquals("Moscow", trip.getAddressTo());
        });
    }

    @Test
    public void updateTripTest() {
        var trip = createTrip(500, "Ekb", "Moscow", 3, LocalDateTime.of(2023, 7, 1, 8, 30, 0), driver, driverCar);
    }

    private Account createAccount(String username, String password, String name, int age, String phoneNumber, String email) {
        return accountRepository.save(Account.builder()
                .username(username)
                .password(password)
                .name(name)
                .age(age)
                .phoneNumber(phoneNumber)
                .email(email)
                .createdAt(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .build());
    }

    private Car createCar(String mark, String color, String licensePlate, Account account) {
        return carRepository.save(Car.builder()
                .mark(mark)
                .color(color)
                .licensePlate(licensePlate)
                .account(account)
                .build());
    }

    private Trip createTrip(int price, String addressFrom, String addressTo, int countSeats, LocalDateTime timeTrip, Account driver, Car car) {
        return tripRepository.save(Trip.builder()
                .price(price)
                .addressFrom(addressFrom)
                .addressTo(addressTo)
                .countSeats(countSeats)
                .timeTrip(timeTrip)
                .driver(driver)
                .car(car)
                .build());
    }

}
