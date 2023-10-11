package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.responses.AccountResponse;
import ru.krayseer.voyage.domain.responses.AuthResponse;
import ru.krayseer.voyageapi.services.RemoteService;

public interface RemoteAccountService extends RemoteService {

    AccountResponse getAccountInfo(String username);

    String getAccountUsername(String authHeader);

    AuthResponse getAccountAuthInfo(String username);

}
