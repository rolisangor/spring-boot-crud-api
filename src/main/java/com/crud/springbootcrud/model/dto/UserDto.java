package com.crud.springbootcrud.model.dto;

import lombok.*;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class UserDto {

    private Long id;
    @NotNull(message = "firstName must not be null")
    private String firstName;
    @NotNull(message = "lastName must not be null")
    private String lastName;
    private String email;
    private String gender;
    private String company;
    private String avatar;
    private String jobTitle;
    private Instant createdAt;

}
