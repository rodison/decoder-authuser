package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthenticationControllerTest {
    @Mock
    UserService userService;
    @InjectMocks
    AuthenticationController authenticationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Captor
    private ArgumentCaptor<UserModel> userModelCaptor;

    @Test
    void testRegisterUser_usernameExists() {
        // given
        when(userService.existsByUsername(anyString())).thenReturn(true);
        UserDto userDto = basicUserDto();

        // when
        ResponseEntity<Object> result = authenticationController.registerUser(userDto);

        // then
        Assertions.assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        Assertions.assertEquals("Error: Username is already taken!", result.getBody().toString());
    }

    @Test
    void testRegisterUser_emailExists() {
        // given
        when(userService.existsByEmail(anyString())).thenReturn(true);
        UserDto userDto = basicUserDto();

        // when
        ResponseEntity<Object> result = authenticationController.registerUser(userDto);

        // then
        Assertions.assertEquals(HttpStatus.CONFLICT, result.getStatusCode());
        Assertions.assertEquals("Error: Email is already taken!", result.getBody().toString());
    }

    @Test
    void testRegisterUser_doesntExist() {
        // given
        final UUID userId = UUID.randomUUID();
        when(userService.existsByUsername(anyString())).thenReturn(false);
        when(userService.existsByEmail(anyString())).thenReturn(false);
        doAnswer(invocation -> {
            Object[] args = invocation.getArguments();
            UserModel userModelParam = ((UserModel)args[0]);
            userModelParam.setUserId(userId);
            return null; // void method in a block-style lambda, so return null
        }).when(userService).save(any(UserModel.class));

        UserDto userDto = basicUserDto();

        // when
        ResponseEntity<Object> result = authenticationController.registerUser(userDto);
//        verify(userService).save(userModelCaptor.capture());
//        final UserModel userModelAfterServiceSave = userModelCaptor.getValue();
        UserModel responseUserModel = (UserModel) result.getBody();

        // then
        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(userId, responseUserModel.getUserId());
        assertEquals(userDto.getUsername(), responseUserModel.getUsername());
        assertEquals(userDto.getEmail(), responseUserModel.getEmail());
        // o @jsonIgnore no password faz com que o password não seja retornada pela api
        assertEquals(userDto.getPassword(), responseUserModel.getPassword());
        assertEquals(userDto.getFullName(), responseUserModel.getFullName());
        assertEquals(userDto.getPhoneNumber(), responseUserModel.getPhoneNumber());
        assertEquals(userDto.getCpf(), responseUserModel.getCpf());
        assertEquals(UserStatus.ACTIVE, responseUserModel.getUserStatus());
        assertEquals(UserType.STUDENT, responseUserModel.getUserType());
        assertEquals(LocalDateTime.class.getName(), responseUserModel.getCreationDate().getClass().getName());
        assertEquals(LocalDateTime.class.getName(), responseUserModel.getLastUpdateDate().getClass().getName());
    }

    private static UserDto basicUserDto() {
        String username = "user1";
        String email = "user1@example.com";

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword("password1");
        userDto.setFullName("First Last");
        userDto.setPhoneNumber("(48) 99154-9999)");
        userDto.setCpf("03892099999");
        return userDto;
    }

    @Test
    void testRegisterUser_dtoNull() {
        // given

        // when
        ResponseEntity<Object> result = authenticationController.registerUser(null);

        // then
        Assertions.assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
        Assertions.assertEquals("Error: userDto cant be null!", result.getBody().toString());
    }
}



//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme