package com.ra.controller;

import com.ra.model.dto.user.AccountRequestDTO;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin/account")
public class AdminController {
    @Autowired
    private AuthService authService;
    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO){

        return new ResponseEntity<>(authService.createAdminAccount(accountRequestDTO), HttpStatus.CREATED);
    }
}
