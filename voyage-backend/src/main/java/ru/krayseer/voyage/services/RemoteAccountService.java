package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.responses.AccountResponse;

public interface RemoteAccountService {

    AccountResponse getAccountInfo(String username);

}
