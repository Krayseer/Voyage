package ru.krayseer.voyage.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.voyage.commons.exceptions.AccountNotExistsError;
import ru.krayseer.voyage.commons.exceptions.UsernameAlreadyExistsError;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.domain.dto.responses.PhotoUploadResponse;
import ru.krayseer.voyage.domain.repositories.AccountRepository;
import ru.krayseer.voyage.services.AccountService;
import ru.krayseer.voyage.services.factories.AccountFactory;

import static ru.krayseer.voyage.commons.constants.CommonEnums.PHOTO_REQUEST_PARAM_NAME;

@Slf4j
@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    @Value("${PHOTO_SERVICE_URL}")
    private String photoServiceUrl;

    private final AccountRepository accountRepository;

    private final AccountFactory accountFactory;

    private final RestTemplate restTemplate;

    @Override
    public AccountResponse loadAccount(String username) {
        log.info("Load \"{}\" account", username);
        return accountRepository
                .findByUsername(username)
                .map(accountFactory::createAccountResponse)
                .orElseThrow(UsernameAlreadyExistsError::new);
    }

    @Override
    public ResponseEntity<byte[]> getAccountAvatar(String username) {
        log.info("Load \"{}\" avatar", username);
        var account = accountRepository.findByUsername(username).orElseThrow(AccountNotExistsError::new);
        String url = photoServiceUrl + '/' + account.getAvatarUrl();
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
        var photoUrl = restTemplate.postForEntity(photoServiceUrl, requestEntity, String.class).getBody();
        restTemplate.delete(photoServiceUrl + "/" + account.getAvatarUrl());
        account.setAvatarUrl(photoUrl);
        accountRepository.save(account);
        log.info("Save new account avatar by \"{}\"", username);
        return PhotoUploadResponse
                .builder()
                .photoUrl(photoUrl)
                .build();
    }

}
