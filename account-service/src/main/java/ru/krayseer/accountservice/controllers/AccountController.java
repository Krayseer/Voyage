package ru.krayseer.accountservice.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.domain.dto.responses.AccountResponse;
import ru.krayseer.accountservice.domain.dto.responses.PhotoUploadResponse;
import ru.krayseer.accountservice.services.AccountService;

import java.security.Principal;

@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public AccountResponse loadAuthorizedAccountData(Principal principal) {
        return accountService.loadAccount(principal.getName());
    }

    @GetMapping("/{username}")
    public AccountResponse loadAccountByUsername(@PathVariable String username) {
        return accountService.loadAccount(username);
    }

    @GetMapping("/photo")
    public byte[] loadAuthorizedAccountPhoto(Principal principal) {
        return accountService.getAccountAvatar(principal.getName());
    }

    @GetMapping("/photo/{username}")
    public byte[] loadAccountPhotoByUsername(@PathVariable String username) {
        return accountService.getAccountAvatar(username);
    }

    @PostMapping("/photo")
    public PhotoUploadResponse uploadAuthorizedAccountPhoto(@RequestParam("photo") MultipartFile multipartFile,
                                                            Principal principal) {
        return accountService.uploadAccountPhoto(multipartFile, principal.getName());
    }

    @PostMapping("/photo/{username}")
    public PhotoUploadResponse uploadAccountPhotoByUsername(@PathVariable String username,
                                                            @RequestParam("photo") MultipartFile multipartFile) {
        return accountService.uploadAccountPhoto(multipartFile, username);
    }

}