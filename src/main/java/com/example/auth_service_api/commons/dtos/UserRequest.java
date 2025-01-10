package com.example.auth_service_api.commons.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRequest {
    @NotNull
    private String name;
    @NotNull
    private String email;
    @NotNull
    private String password;

}
