//package ru.krayseer.voyage.repositories;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//import org.springframework.test.context.ActiveProfiles;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//import ru.krayseer.voyage.VoyageApplication;
//import ru.krayseer.voyage.commons.constants.Role;
//import ru.krayseer.voyage.context.ContainersEnvironment;
//import ru.krayseer.voyage.domain.entities.Car;
//import ru.krayseer.voyage.domain.repositories.CarRepository;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@ActiveProfiles("test")
//@ExtendWith(SpringExtension.class)
//@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
//@SpringBootTest(classes = VoyageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//public class CarRepositoryTest extends ContainersEnvironment {
//
//    @Autowired
//    private CarRepository carRepository;
//
//    @Autowired
//    private AccountRepository accountRepository;
//
//    private Car car;
//
//    private Account user;
//
//    @BeforeEach
//    public void init() {
//        user = Account.builder()
//                .username("user123")
//                .password("qwerty12345")
//                .name("John")
//                .age(19)
//                .phoneNumber("+88888888888")
//                .email("email@gmail.com")
//                .createdAt(LocalDateTime.now())
//                .role(Role.ROLE_USER)
//                .build();
//
//        car = Car.builder()
//                .mark("accent")
//                .color("black")
//                .licensePlate("Н222КН211")
//                .account(user)
//                .build();
//    }
//
//    @Test
//    public void createUserCar() {
//        var newCar = carRepository.save(car);
//
//        assertNotNull(newCar);
//        assertEquals("accent", newCar.getMark());
//        assertEquals("black", newCar.getColor());
//        assertEquals("Н222КН211", newCar.getLicensePlate());
//        assertEquals(user, newCar.getAccount());
//    }
//
//    @Test
//    public void findUserCarTest() {
//        accountRepository.save(user);
//        carRepository.save(car);
//
//        var userCars = carRepository.findCarsByAccount(user);
//
//        assertNotNull(userCars);
//        assertEquals(1, userCars.size());
//    }
//
//    @Test
//    public void updateCarTest() {
//        accountRepository.save(user);
//        var existingCar = carRepository.findCarsByAccount(user).stream().findFirst();
//        if (existingCar.isPresent()) {
//            existingCar.get().setColor("blue");
//            var updatedCar = carRepository.save(existingCar.get());
//
//            assertEquals("accent", updatedCar.getMark());
//            assertEquals("blue", updatedCar.getColor());
//        }
//    }
//
//    @Test
//    public void deleteCarTest() {
//        var secondCar = Car.builder()
//                .mark("lada")
//                .color("gray")
//                .licensePlate("О222РР123")
//                .account(user)
//                .build();
//
//        accountRepository.save(user);
//        carRepository.save(car);
//        carRepository.save(secondCar);
//
//        assertEquals(2, carRepository.findAll().size());
//
//        carRepository.delete(secondCar);
//        var exitingCar = carRepository.findById(secondCar.getId());
//
//        assertEquals(1, carRepository.findAll().size());
//        assertTrue(exitingCar.isEmpty());
//    }
//
//}
