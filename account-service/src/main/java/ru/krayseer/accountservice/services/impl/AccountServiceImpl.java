package ru.krayseer.accountservice.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.commons.errors.AccountNotExistsError;
import ru.krayseer.accountservice.commons.errors.UsernameNotFoundError;
import ru.krayseer.accountservice.domain.dto.responses.AccountResponse;
import ru.krayseer.accountservice.domain.entities.Account;
import ru.krayseer.accountservice.utils.Utils;
import ru.krayseer.accountservice.domain.dto.responses.PhotoUploadResponse;
import ru.krayseer.accountservice.domain.mappers.AccountMapper;
import ru.krayseer.accountservice.domain.repositories.AccountRepository;
import ru.krayseer.accountservice.services.AccountService;
import ru.krayseer.accountservice.services.RemotePhotoService;
import ru.krayseer.accountservice.services.jwt.JwtService;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final JwtService jwtService;

    private final AccountMapper accountMapper;

    private final AccountRepository accountRepository;

    private final RemotePhotoService remotePhotoService;

    @Override
    public AccountResponse loadAccount(String username) {
        log.info("Load \"{}\" account", username);
        return accountRepository
                .findByUsername(username)
                .map(accountMapper::createAccountResponse)
                .orElseThrow(UsernameNotFoundError::new);
    }

    @Override
    public AccountResponse loadAccount(HttpServletRequest request) {
        String userToken = Utils.getTokenFromHeader(request);
        String username = jwtService.extractUsername(userToken);
        return loadAccount(username);
    }

    @Override
    @SneakyThrows
    public ResponseEntity<byte[]> getAccountAvatar(String username) {
        log.info("Load \"{}\" avatar", username);
        Account account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        return remotePhotoService.getAccountPhotoFromStorage(account);
    }

    @Override
    public ResponseEntity<byte[]> getAccountAvatar(HttpServletRequest request) {
        String userToken = Utils.getTokenFromHeader(request);
        String username = jwtService.extractUsername(userToken);
        return getAccountAvatar(username);
    }

    @Override
    @SneakyThrows
    public PhotoUploadResponse uploadAccountPhoto(MultipartFile multipartFile, String username) {
        Account account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        String photoUrl = remotePhotoService.uploadPhotoInStorage(account, multipartFile);
        account.setAvatarUrl(photoUrl);
        accountRepository.save(account);
        log.info("Save new account avatar for \"{}\"", username);
        return PhotoUploadResponse.builder().photoUrl(photoUrl).build();
    }

    @Override
    public PhotoUploadResponse uploadAccountPhoto(MultipartFile multipartFile, HttpServletRequest request) {
        String userToken = Utils.getTokenFromHeader(request);
        String username = jwtService.extractUsername(userToken);
        return uploadAccountPhoto(multipartFile, username);
    }

}
