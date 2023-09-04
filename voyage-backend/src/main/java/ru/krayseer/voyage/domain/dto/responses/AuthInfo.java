package ru.krayseer.voyage.domain.dto.responses;

import lombok.Data;

@Data
public class AuthInfo {

    private String username;

    private String token;

    private String role;

    private String password;

}
