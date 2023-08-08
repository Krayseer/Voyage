package ru.krayseer.voyage.repositories;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ser.Serializers;
import ru.krayseer.voyage.VoyageApplication;
import ru.krayseer.voyage.commons.constants.Role;
import ru.krayseer.voyage.context.ContainersEnvironment;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.repositories.AccountRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;


@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = VoyageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountRepositoryTest extends ContainersEnvironment {

    @Autowired
    private AccountRepository accountRepository;

    private Account defaultUser;

    private Account adminUser;

    @BeforeEach
    public void init() {
        defaultUser = Account.builder()
                .username("user123")
                .password("qwerty12345")
                .name("John")
                .age(19)
                .phoneNumber("+88888888888")
                .email("email@gmail.com")
                .createdAt(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .build();

        adminUser = Account.builder()
                .username("defaulter")
                .password("ambre101")
                .name("Maxim")
                .age(21)
                .phoneNumber("+77777777777")
                .email("max@yandex.ru")
                .createdAt(LocalDateTime.now())
                .role(Role.ROLE_ADMIN)
                .build();
    }

    @Test
    public void getAllTest() {
        accountRepository.save(defaultUser);
        accountRepository.save(adminUser);
        var allUsers = accountRepository.findAll();

        assertNotNull(allUsers);
        assertEquals(2, allUsers.size());
    }

    @Test
    public void saveAccountTest() {
        var newAccount = accountRepository.save(defaultUser);

        assertNotNull(newAccount);
        assertEquals("user123", newAccount.getUsername());
        assertEquals("qwerty12345", newAccount.getPassword());
        assertEquals("John", newAccount.getName());
        assertEquals(19, newAccount.getAge());
        assertEquals("+88888888888", newAccount.getPhoneNumber());
        assertEquals(Role.ROLE_USER, newAccount.getRole());
    }

    @Test
    public void findAccountByUsernameTest() {
        var newAccount = accountRepository.save(defaultUser);

        assertNotNull(newAccount);
        accountRepository
                .findByUsername(newAccount.getUsername())
                .ifPresent(value -> {
                    assertEquals("user123", value.getUsername());
                    assertEquals("John", value.getName());
                    assertEquals(Role.ROLE_USER, value.getRole());
                });
    }

    @Test
    public void existsAccountByUsernameTest() {
        var newAccount = accountRepository.save(defaultUser);

        assertNotNull(newAccount);
        assertTrue(accountRepository.existsByUsername("user123"));
        assertFalse(accountRepository.existsByUsername("nothing"));
    }

    @Test
    public void existsAccountByEmailTest() {
        var newAccount = accountRepository.save(defaultUser);

        assertNotNull(newAccount);
        assertTrue(accountRepository.existsByEmail("email@gmail.com"));
        assertFalse(accountRepository.existsByEmail("nothing"));
    }

    @Test
    public void existsAccountByPhoneNumberTest() {
        var newAccount = accountRepository.save(defaultUser);

        assertNotNull(newAccount);
        assertTrue(accountRepository.existsByPhoneNumber("+88888888888"));
        assertFalse(accountRepository.existsByPhoneNumber("nothing"));
    }

    @Test
    public void updateAccountTest() {
        accountRepository.save(defaultUser);
        var existingAccount = accountRepository.findByUsername(defaultUser.getUsername());
        if(existingAccount.isPresent()) {
            existingAccount.get().setName("Stanley");
            var updatedAccount = accountRepository.save(existingAccount.get());

            assertEquals("user123", updatedAccount.getUsername());
            assertEquals("Stanley", updatedAccount.getName());
        }
    }

    @Test
    public void deleteAccountTest() {
        accountRepository.save(defaultUser);
        accountRepository.save(adminUser);

        assertEquals(2, accountRepository.findAll().size());

        accountRepository.delete(defaultUser);
        var exitingUser = accountRepository.findById(defaultUser.getId());

        assertEquals(1, accountRepository.findAll().size());
        assertTrue(exitingUser.isEmpty());
    }

}
