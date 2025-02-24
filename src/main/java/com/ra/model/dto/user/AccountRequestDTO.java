package com.ra.model.dto.user;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class AccountRequestDTO {
    private String fullName;
    private String username;
    private String password;
    private Set<String> roleName;
}
