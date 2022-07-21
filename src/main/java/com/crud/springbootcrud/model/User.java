package com.crud.springbootcrud.model;

import lombok.*;
import javax.persistence.*;
import java.util.Collection;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity{

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String company;
    private String avatar;
    private String jobTitle;

    private String password;
    @ManyToMany(fetch = FetchType.EAGER) //TODO: add join column
    private Collection<Role> roles;

}
