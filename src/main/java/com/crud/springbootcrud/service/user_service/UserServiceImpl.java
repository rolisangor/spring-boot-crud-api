package com.crud.springbootcrud.service.user_service;

import com.crud.springbootcrud.exception.BadRequestException;
import com.crud.springbootcrud.exception.InternalServerError;
import com.crud.springbootcrud.exception.UserNotFoundException;
import com.crud.springbootcrud.model.user.User;
import com.crud.springbootcrud.model.dto.UserDto;
import com.crud.springbootcrud.repository.UserRepository;
import com.crud.springbootcrud.service.mapper.UserMapper;
import com.crud.springbootcrud.service.role_service.RoleService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.RoleNotFoundException;
import java.util.List;

@Service
@Slf4j
@AllArgsConstructor
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @Transactional
    @Override
    public UserDto save(UserDto userDto) throws RoleNotFoundException {
        if (existByEmail(userDto.getEmail())) {
            throw new BadRequestException("User with email: " + userDto.getEmail() + " already exist");
        }
        User currentUser = userMapper.toUser(userDto);
//        currentUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        if (currentUser.getRoles() == null) {
            currentUser.setRoles(List.of(roleService.getRoleByName("ROLE_USER")));
        }
//        try {
//            user = userRepository.save(currentUser);
//        }catch (Exception e) {
//            log.error("Save user error: {}", e.getMessage());
//            throw new InternalServerError("Internal server error user save please try again");
//        }
        return userMapper.toUserDto(currentUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsers(int page, int size) {
        if (page < 0 || size < 0) throw new BadRequestException("page and size must not be less than zero");
        Pageable pageable = PageRequest.of(page, size, Sort.by("firstName"));
        return userMapper.toUserDtoList(userRepository.findAll(pageable).toList());
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserById(Long id) {
        return userMapper.toUserDto(userRepository.findById(id).orElseThrow(() ->
                new UserNotFoundException("User with id: " + id + " not found")));
    }

    @Transactional
    @Override
    public UserDto updateUser(UserDto userDto) {
        User user = userRepository.findById(userDto.getId()).orElseThrow(() ->
                new UserNotFoundException("User with id: " + userDto.getId() + " not found"));
        userMapper.updateFromUserDto(userDto, user);
        return userMapper.toUserDto(user);
    }

    @Transactional
    @Override
    public void deleteUser(Long id) {
        try {
            userRepository.deleteById(id);
        }catch (Exception e) {
            throw new InternalServerError("Internal server error user delete please try again");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserByEmail(String email) {
        if (!existByEmail(email)) throw new UserNotFoundException("User with email: " + email + " not found");
        return userRepository.findByEmail(email);
    }

    private boolean existByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

}
