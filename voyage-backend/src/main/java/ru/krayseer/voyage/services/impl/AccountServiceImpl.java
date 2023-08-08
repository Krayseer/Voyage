package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.voyage.ApplicationConfig;
import ru.krayseer.voyage.commons.errors.AccountNotExistsError;
import ru.krayseer.voyage.commons.errors.UsernameNotFoundError;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.PhotoUploadResponse;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.services.AccountService;
import ru.krayseer.voyage.domain.mappers.AccountMapper;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private static final String PHOTO_REQUEST_PARAM_NAME = "photo";

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    private final RestTemplate restTemplate;

    private final ApplicationConfig applicationConfig;

    @Override
    public AccountResponse loadAccount(String username) {
        log.info("Load \"{}\" account", username);
        return accountRepository
                .findByUsername(username)
                .map(accountMapper::createAccountResponse)
                .orElseThrow(UsernameNotFoundError::new);
    }

    @Override
    public ResponseEntity<byte[]> getAccountAvatar(String username) {
        log.info("Load \"{}\" avatar", username);
        var account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        String url = applicationConfig.getPhotoServiceUrl() + '/' + account.getAvatarUrl();
        return restTemplate.getForEntity(url, byte[].class);
    }

    @Override
    @SneakyThrows
    public PhotoUploadResponse uploadAccountPhoto(MultipartFile multipartFile, String username) {
        var account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        var headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        var body = new LinkedMultiValueMap<>();
        body.add(PHOTO_REQUEST_PARAM_NAME, new ByteArrayResource(multipartFile.getBytes()) {
            @Override
            public String getFilename() {
                return "photo.jpg";
            }
        });

        var requestEntity = new HttpEntity<>(body, headers);
        var photoUrl = restTemplate.postForEntity(applicationConfig.getPhotoServiceUrl(), requestEntity, String.class).getBody();
        restTemplate.delete(applicationConfig.getPhotoServiceUrl() + "/" + account.getAvatarUrl());
        account.setAvatarUrl(photoUrl);
        accountRepository.save(account);
        log.info("Save new account avatar by \"{}\"", username);
        return PhotoUploadResponse
                .builder()
                .photoUrl(photoUrl)
                .build();
    }

}
