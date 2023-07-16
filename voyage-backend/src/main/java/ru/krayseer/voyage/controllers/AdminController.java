package ru.krayseer.voyage.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.krayseer.voyage.domain.dto.responses.AccountResponse;
import ru.krayseer.voyage.services.AdminService;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/person")
    public List<AccountResponse> loadAll() {
        return adminService.loadAllAccounts();
    }

}
