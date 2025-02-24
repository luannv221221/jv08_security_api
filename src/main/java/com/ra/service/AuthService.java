package com.ra.service;

import com.ra.model.dto.user.*;

public interface AuthService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    DataResponse register(RegisterRequestDTO registerRequestDTO);
    DataResponse createAdminAccount(AccountRequestDTO accountRequestDTO);

}
