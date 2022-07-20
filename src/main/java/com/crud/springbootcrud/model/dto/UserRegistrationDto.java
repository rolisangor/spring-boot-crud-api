package com.crud.springbootcrud.model.dto;

import lombok.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserRegistrationDto {

    @NotNull(message = "Email must be present")
    @NotBlank(message = "Email must be present")
    private String email;

    @NotNull(message = "Password must be present")
    @NotBlank(message = "Password must be present")
    private String password;

    @NotNull(message = "Repeat password must be present")
    @NotBlank(message = "Repeat password must be present")
    private String rePassword;

}
