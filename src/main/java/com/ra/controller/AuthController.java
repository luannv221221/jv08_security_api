package com.ra.controller;

import com.ra.model.dto.user.DataResponse;
import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.RegisterRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {
    @Autowired
    private AuthService authService;
    @PostMapping("/login")
    public ResponseEntity<UserResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        UserResponseDTO  userResponseDTO = authService.login(loginRequestDTO);
        return new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
    }
    @PostMapping("/register")
    public ResponseEntity<DataResponse> register(@RequestBody RegisterRequestDTO registerRequestDTO){
        DataResponse dataResponse = authService.register(registerRequestDTO);
        return new ResponseEntity<>(dataResponse,HttpStatus.CREATED);
    }

}
