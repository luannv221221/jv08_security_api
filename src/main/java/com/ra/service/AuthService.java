package com.ra.service;

import com.ra.exception.CustomException;
import com.ra.model.dto.user.*;

public interface AuthService {
    UserResponseDTO login(LoginRequestDTO loginRequestDTO);
    DataResponse register(RegisterRequestDTO registerRequestDTO);
    DataResponse createAdminAccount(AccountRequestDTO accountRequestDTO);
    DataResponse editRoleAccount(EditRoleAccountDTO editRoleAccountDTO,Long idAccount) throws CustomException;
}
