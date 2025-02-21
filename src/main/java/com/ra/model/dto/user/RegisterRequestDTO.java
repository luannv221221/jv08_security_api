package com.ra.model.dto.user;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class RegisterRequestDTO {
    private String fullName;
    private String username;
    private String password;
}
