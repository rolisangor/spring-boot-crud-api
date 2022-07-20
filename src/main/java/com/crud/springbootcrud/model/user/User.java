package com.crud.springbootcrud.model.user;

import com.crud.springbootcrud.model.BaseEntity;
import com.crud.springbootcrud.model.role.Role;
import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Table(name = "users")
public class User extends BaseEntity {

    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String company;
    private String avatar;
    private String jobTitle;
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Role> roles = new ArrayList<>();

}
