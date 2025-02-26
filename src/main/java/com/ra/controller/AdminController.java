package com.ra.controller;

import com.ra.exception.CustomException;
import com.ra.model.dto.user.AccountRequestDTO;
import com.ra.model.dto.user.EditRoleAccountDTO;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/account")
public class AdminController {
    @Autowired
    private AuthService authService;


    @PostMapping
    public ResponseEntity<?> createAccount(@RequestBody AccountRequestDTO accountRequestDTO){

        return new ResponseEntity<>(authService.createAdminAccount(accountRequestDTO), HttpStatus.CREATED);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<?> editRoleAccount(@PathVariable Long id, @RequestBody EditRoleAccountDTO editRoleAccountDTO) throws CustomException {
        return new ResponseEntity<>(authService.editRoleAccount(editRoleAccountDTO,id),HttpStatus.OK);
    }
}
