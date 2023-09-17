package ru.krayseer.voyage.domain.dto.responses;

import lombok.Data;
import ru.krayseer.voyageapi.domain.dto.Response;

import java.time.LocalDateTime;

@Data
public class AccountResponse implements Response {

    private Long id;

    private String username;

    private String name;

    private Integer age;

    private String phoneNumber;

    private String email;

    private LocalDateTime createdAt;

    private String role;

}
