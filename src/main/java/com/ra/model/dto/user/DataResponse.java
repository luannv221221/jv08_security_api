package com.ra.model.dto.user;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class DataResponse {
    private int httpStatus;
    private String message;
}
