package com.ra.model.dto.user;

import com.ra.model.entity.Role;
import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserResponseDTO {
    private String fullName;
    private String username;
    private String token;
    private String typeToken;
    private Set<Role> roles;
}
