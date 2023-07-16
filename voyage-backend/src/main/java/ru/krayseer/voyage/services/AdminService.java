package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.responses.AccountResponse;

import java.util.List;

public interface AdminService {

    /**
     * Загрузить все данные обо всех аккаунтах в БД
     * @return список всех аккаунтов
     */
    List<AccountResponse> loadAllAccounts();

}
