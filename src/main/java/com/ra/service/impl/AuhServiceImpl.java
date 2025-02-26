package com.ra.service.impl;

import com.ra.exception.CustomException;
import com.ra.model.dto.user.*;
import com.ra.model.entity.Role;
import com.ra.model.entity.User;
import com.ra.repository.RoleRepository;
import com.ra.repository.UserRepository;
import com.ra.security.UserPrinciple;
import com.ra.security.jwt.JwtProvider;
import com.ra.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuhServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
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

    @Override
    public DataResponse register(RegisterRequestDTO registerRequestDTO) {
       // gan quyen mac dinh USER cho tai khoan dang ky
        Set<Role> roles = new HashSet<>();
        Role role = roleRepository.findRoleByRoleName("USER");
        roles.add(role);
        // convert DTO=>ENTITY
        User user = User.builder()
                .fullName(registerRequestDTO.getFullName())
                .username(registerRequestDTO.getUsername())
                .passowrd(new BCryptPasswordEncoder().encode(registerRequestDTO.getPassword()))
                .roles(roles)
                .status(true)
                .build();
        // tao moi ban ghi vao bang user
        userRepository.save(user);
        return new DataResponse(201, "Dang ku thanh cong");
    }

    @Override
    public DataResponse createAdminAccount(AccountRequestDTO accountRequestDTO) {
        // xu ly lay ve danh sach quyen gui len tu client
        Set<Role> roles = new HashSet<>();
        for (String roleName: accountRequestDTO.getRoleName()) {
            Role role = roleRepository.findRoleByRoleName(roleName);
            roles.add(role);
        }
        // convert DTO=>ENTITY
        User user = User.builder()
                .fullName(accountRequestDTO.getFullName())
                .username(accountRequestDTO.getUsername())
                .passowrd(new BCryptPasswordEncoder().encode(accountRequestDTO.getPassword()))
                .roles(roles)
                .status(true)
                .build();
        userRepository.save(user);
        return new DataResponse(201, "Tao moi thanh cong");
    }

    @Override
    public DataResponse editRoleAccount(EditRoleAccountDTO editRoleAccountDTO,Long idAccount) throws CustomException {
        // tìm về đối tượng account cần cập nhật quyền
        User user = userRepository.findById(idAccount).orElseThrow(()->new CustomException("User Not Found"));
        Set<Role> roles = new HashSet<>();
        for (String roleName: editRoleAccountDTO.getRoleName()) {
            Role role = roleRepository.findRoleByRoleName(roleName);
            if(role== null){
                throw new CustomException("Role not found");
            }
            roles.add(role);
        }

        assert user != null;
        user.setRoles(roles);
        userRepository.save(user);
        return new DataResponse(200, "cap nhat  thanh cong");
    }


}
