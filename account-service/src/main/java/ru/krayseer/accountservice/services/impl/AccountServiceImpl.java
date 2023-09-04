package ru.krayseer.accountservice.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.commons.errors.AccountNotExistsError;
import ru.krayseer.accountservice.commons.errors.UsernameNotFoundError;
import ru.krayseer.accountservice.domain.dto.Response;
import ru.krayseer.accountservice.domain.dto.responses.PhotoUploadResponse;
import ru.krayseer.accountservice.domain.mappers.AccountMapper;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.AccountService;
import ru.krayseer.accountservice.services.RemotePhotoService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final RemotePhotoService remotePhotoService;

    @Override
    public Response loadAccount(String username) {
        log.info("Load \"{}\" account", username);
        return accountRepository
                .findByUsername(username)
                .map(accountMapper::createAccountResponse)
                .orElseThrow(UsernameNotFoundError::new);
    }

    @Override
    @SneakyThrows
    public byte[] getAccountAvatar(String username) {
        log.info("Load \"{}\" avatar", username);
        var account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        return remotePhotoService.getAccountPhotoFromStorage(account);
    }

    @Override
    @SneakyThrows
    public Response uploadAccountPhoto(MultipartFile multipartFile, String username) {
        var account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        var photoUrl = remotePhotoService.uploadPhotoInStorage(account, multipartFile);
        account.setAvatarUrl(photoUrl);
        accountRepository.save(account);
        log.info("Save new account avatar for \"{}\"", username);
        return PhotoUploadResponse.builder()
                .photoUrl(photoUrl)
                .build();
    }

}
