package com.crud.springbootcrud.model.role;

import com.crud.springbootcrud.model.BaseEntity;
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
@Table(name = "role")
public class Role extends BaseEntity {
    private String name;
}
