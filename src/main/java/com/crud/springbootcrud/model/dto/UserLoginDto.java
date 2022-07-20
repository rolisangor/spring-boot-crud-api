package com.crud.springbootcrud.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserLoginDto {

    @NotNull(message = "Username must be present")
    @NotBlank(message = "Username must be present")
    @Email
    private String username;

    @NotNull(message = "Username must be present")
    @NotBlank(message = "Username must be present")
    private String password;

}
