package ru.krayseer.voyage.services;

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
import ru.krayseer.voyage.domain.repositories.AccountRepository;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ActiveProfiles("test")
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@SpringBootTest(classes = VoyageApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class AccountServiceTest extends ContainersEnvironment {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountService accountService;

    @Test
    public void testLoadAccountMethod() {
        Account testAccount = Account.builder()
                .username("urepa")
                .password("ijnbgr1245")
                .name("Andrew")
                .age(25)
                .phoneNumber("79124251522")
                .email("rus@yandex.ru")
                .createdAt(LocalDateTime.now())
                .role(Role.ROLE_USER)
                .build();
        accountRepository.save(testAccount);
        var account = accountService.loadAccount("urepa");
        assertEquals("Andrew", account.getName());
        assertEquals("79124251522", account.getPhoneNumber());
    }

}
