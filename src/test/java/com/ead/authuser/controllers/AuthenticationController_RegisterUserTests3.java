package com.ead.authuser.controllers;

import com.ead.authuser.dtos.UserDto;
import com.ead.authuser.enums.UserStatus;
import com.ead.authuser.enums.UserType;
import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import com.ead.authuser.services.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class AuthenticationController_RegisterUserTests3 {

    private MockMvc mvc;

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Before
    public void setUp() {
    }

    @Test
    public void testRegisterUser() throws Exception {
        // given
        String username = "user1";
        String email = "user1@example.com";

        // o junit disse que nao precisa destes mocks
//        doNothing().when(userRepository).save(any(UserModel.class));
//        when(userRepository.save(any(UserModel.class))).thenReturn(null);
//        when(userRepository.existsByUsername(username)).thenReturn(false);
//        when(userRepository.existsByEmail(email)).thenReturn(false);

//        ObjectMapper objectMapper = new ObjectMapper();
        ObjectMapper objectMapper = JsonMapper.builder().addModule(new JavaTimeModule()).build();

        UserDto userDto = new UserDto();
        userDto.setUsername(username);
        userDto.setEmail(email);
        userDto.setPassword("password1");
        userDto.setFullName("First Last");
        userDto.setPhoneNumber("(48) 99154-9999)");
        userDto.setCpf("03892099999");

        // when
        mvc = MockMvcBuilders.standaloneSetup(authenticationController).build();
        MockHttpServletResponse response = mvc.perform(
                        post("/auth/signup")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(userDto)))
                .andExpect(status().isCreated())
                .andReturn().getResponse();

        // then
        UserModel responseUserModel = objectMapper.readValue(response.getContentAsString(), UserModel.class);
//        assertNotNull(responseUserModel.getUserId());
        assertEquals(userDto.getUsername(), responseUserModel.getUsername());
        assertEquals(userDto.getEmail(), responseUserModel.getEmail());
        // o @jsonIgnore no password faz com que o password não seja retornada pela api
        assertNull(responseUserModel.getPassword());
        assertEquals(userDto.getFullName(), responseUserModel.getFullName());
        assertEquals(userDto.getPhoneNumber(), responseUserModel.getPhoneNumber());
        assertEquals(userDto.getCpf(), responseUserModel.getCpf());
        assertEquals(UserStatus.ACTIVE, responseUserModel.getUserStatus());
        assertEquals(UserType.STUDENT, responseUserModel.getUserType());
        assertEquals(LocalDateTime.class.getName(), responseUserModel.getCreationDate().getClass().getName());
        assertEquals(LocalDateTime.class.getName(), responseUserModel.getLastUpdateDate().getClass().getName());

    }


}
