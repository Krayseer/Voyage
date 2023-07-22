package ru.krayseer.voyage.services;

import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.entities.Account;

import java.util.List;

public interface AdminService {

    /**
     * Загрузить все данные обо всех аккаунтах в БД
     * @return список всех аккаунтов
     */
    List<AccountResponse> loadAllAccounts();

    /**
     * Выдать права администратора аккаунту
     * @param account аккаунт, которому нужно выдать права
     */
    void setAdminRoleForUser(Account account);

}
