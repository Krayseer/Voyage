package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.AuthResponse;
import ru.krayseer.voyageapi.services.RemoteService;

public interface RemoteAccountService extends RemoteService {

    AccountResponse getAccountInfo(String username);

    String getAccountUsername(String authHeader);

    AuthResponse getAccountAuthInfo(String username);

}
