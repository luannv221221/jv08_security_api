package com.ra.service.impl;

import com.ra.model.dto.user.LoginRequestDTO;
import com.ra.model.dto.user.UserResponseDTO;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class AuhServiceImpl implements AuthService {
    @Autowired
    private AuthenticationProvider authenticationProvider;
    @Autowired
    private JwtProvider jwtProvider;
    @Override
    public UserResponseDTO login(LoginRequestDTO loginRequestDTO) {
        // kiểm tra xem có đúng thông tin hay ko
        Authentication authentication;
        authentication =authenticationProvider.
                authenticate(
                        new UsernamePasswordAuthenticationToken
                                (loginRequestDTO.getUsername(),loginRequestDTO.getPassword()));
        UserPrinciple userPrinciple = (UserPrinciple) authentication.getPrincipal();
        // trả về UserResponseDTO
        return UserResponseDTO.builder()
                .username(userPrinciple.getUsername())
                .fullName(userPrinciple.getUser().getFullName())
                .token(jwtProvider.generateToken(userPrinciple))
                .typeToken("Bearer")
                .roles(userPrinciple.getUser().getRoles())
                .build();
    }
}
