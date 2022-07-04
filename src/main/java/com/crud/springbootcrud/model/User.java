package com.crud.springbootcrud.model;

import lombok.*;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Builder
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String company;
    private String avatar;
    private String jobTitle;

}
