package com.crud.springbootcrud.controller;

import com.crud.springbootcrud.model.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import java.util.List;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@PropertySource("classpath:application-test.yml")
@ActiveProfiles("test")
@Sql(value = {"/user_db.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
@Sql(value = {"/drop_user_db.sql"}, executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Save user success")
    void saveUserSuccess() throws Exception {
        UserDto userDto = UserDto.builder()
                .firstName("FirstUserName")
                .lastName("FirstUserLast")
                .avatar("http://test-avatar-first-user.com")
                .company("Test Company First User")
                .email("first@email.com")
                .jobTitle("Test Job Title First User")
                .gender("Male")
                .build();

//        when(userService.save(any(UserDto.class))).thenReturn(userDto);

        mockMvc.perform(post("/api/user")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.email").value("first@email.com"))
                .andReturn();

    }

    @Test
    @DisplayName("Save user without email")
    void saveUserWithoutEmail() throws Exception {
        UserDto userDto = UserDto.builder()
                .firstName("FirstUserName")
                .lastName("FirstUserLast")
                .avatar("http://test-avatar-first-user.com")
                .company("Test Company First User")
                .email(null)
                .jobTitle("Test Job Title First User")
                .gender("Male")
                .build();

        mockMvc.perform(post("/api/user")

                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.errors[0]").value("email must be present"));
    }

    @Test
    @DisplayName("Get all users by page 1 and size 2")
    void getAllUsers() throws Exception {
        List<UserDto> userDtos = List.of(
                UserDto.builder()
                        .id(1L)
                        .firstName("FirstUserName")
                        .lastName("FirstUserLast")
                        .avatar("http://test-avatar-first-user.com")
                        .company("Test Company First User")
                        .email("first@email.com")
                        .jobTitle("Test Job Title First User")
                        .gender("Male")
                        .build(),
                UserDto.builder()
                        .id(2L)
                        .firstName("SecondUserName")
                        .lastName("SecondUserLast")
                        .avatar("http://test-avatar-second-user.com")
                        .company("Test Company Second User")
                        .email("second@email.com")
                        .jobTitle("Test Job Title Second User")
                        .gender("Female")
                        .build());

//        when(userService.getAllUsers(1, 2)).thenReturn(userDtos);

        mockMvc.perform(get("/api/user?page=1&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id").value(1));
    }

    @Test
    @DisplayName("Get users by non existent page")
    void getAllUsersByNonExistentPage() throws Exception {
//        when(userService.getAllUsers(-1, 2)).thenThrow(
//                new BadRequestException("page and size must not be less than zero"));

        mockMvc.perform(get("/api/user?page=-1&size=2")
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.errors[0]")
                        .value("page and size must not be less than zero"));
    }

    @Test
    @DisplayName("Get user by id")
    void getUserById() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstName("FirstUserName")
                .lastName("FirstUserLast")
                .avatar("http://test-avatar-first-user.com")
                .company("Test Company First User")
                .email("first@email.com")
                .jobTitle("Test Job Title First User")
                .gender("Male")
                .build();

//        when(userService.getUserById(anyLong())).thenReturn(userDto);

        mockMvc.perform(get("/api/user/{id}", 1L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("first@email.com"));
    }

    @Test
    @DisplayName("Get user by id not found")
    void getUserByIdNotFound() throws Exception {
//        when(userService.getUserById(anyLong())).thenThrow(new UserNotFoundException("User with id: 4 not found"));

        mockMvc.perform(get("/api/user/{id}", 4L)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$").exists())
                .andExpect(jsonPath("$.errors").value("User with id: 4 not found"));

    }

    @Test
    void updateUser() throws Exception {
        UserDto userDto = UserDto.builder()
                .id(1L)
                .firstName("FirstUserName")
                .lastName("FirstUserLast")
                .avatar("http://test-avatar-first-user.com")
                .company("Test Company First User")
                .email("first@email.com")
                .jobTitle("Test Job Title First User")
                .gender("Male")
                .build();

        UserDto userDtoUpdated = UserDto.builder()
                .id(1L)
                .firstName("UpdatedUserName")
                .build();

//        when(userService.updateUser(any(UserDto.class))).thenReturn(userDtoUpdated);

        mockMvc.perform(put("/api/user")
                .content(asJsonString(userDto))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("UpdatedUserName"));
    }

    @Test
    void deleteUser() throws Exception {
//        when(userService.deleteUser(anyLong())).thenReturn(true); //TODO: userService.deleteUser() return boolean
        mockMvc.perform(delete("/api/user/{id}", 1L))
                .andDo(print())
                .andExpect(status().isOk());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}