package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.entities.Account;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.services.AdminService;
import ru.krayseer.voyage.domain.mappers.AccountMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Override
    public List<AccountResponse> loadAllAccounts() {
        return accountRepository.findAll().stream().map(accountMapper::createAccountResponse).toList();
    }

    @Override
    public void setAdminRoleForUser(Account account) {

    }
}
