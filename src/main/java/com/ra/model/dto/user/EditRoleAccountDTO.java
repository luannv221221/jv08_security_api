package com.ra.model.dto.user;

import lombok.*;

import java.util.Set;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class EditRoleAccountDTO {
    private Set<String> roleName;
}
