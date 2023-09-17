package ru.krayseer.accountservice.controllers;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.krayseer.accountservice.services.AccountService;
import ru.krayseer.voyageapi.domain.dto.Response;

@RestController
@RequiredArgsConstructor
@RequestMapping("/account")
public class AccountController {

    private final AccountService accountService;

    @GetMapping
    public Response loadAuthorizedAccountData(HttpServletRequest request) {
        return accountService.loadAccount(request);
    }

    @GetMapping("/{username}")
    public Response loadAccountByUsername(@PathVariable String username) {
        return accountService.loadAccount(username);
    }

    @GetMapping("/photo")
    public ResponseEntity<byte[]> loadAuthorizedAccountPhoto(HttpServletRequest request) {
        return accountService.getAccountAvatar(request);
    }

    @GetMapping("/photo/{username}")
    public ResponseEntity<byte[]> loadAccountPhotoByUsername(@PathVariable String username) {
        return accountService.getAccountAvatar(username);
    }

    @PostMapping("/photo")
    public Response uploadAuthorizedAccountPhoto(@RequestParam("photo") MultipartFile multipartFile,
                                                 HttpServletRequest request) {
        return accountService.uploadAccountPhoto(multipartFile, request);
    }

    @PostMapping("/photo/{username}")
    public Response uploadAccountPhotoByUsername(@PathVariable String username,
                                                 @RequestParam("photo") MultipartFile multipartFile) {
        return accountService.uploadAccountPhoto(multipartFile, username);
    }

}
