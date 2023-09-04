package ru.krayseer.accountservice.domain.dto.requests;

import lombok.Data;
import ru.krayseer.accountservice.domain.dto.Request;

@Data
public class AuthRequest implements Request {

    private String username;

    private String password;

}
