package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.AuthInfo;

public interface RemoteAccountService {

    AccountResponse getAccountInfo(String username);

    String getAccountUsername(String authHeader);

    AuthInfo getAccountAuthInfo(String username);

}
