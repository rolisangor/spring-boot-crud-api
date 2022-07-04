package com.crud.springbootcrud.repository;

import com.crud.springbootcrud.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {

}
