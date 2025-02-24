package com.ra.model.dto.user;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class LoginRequestDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String password;
}
