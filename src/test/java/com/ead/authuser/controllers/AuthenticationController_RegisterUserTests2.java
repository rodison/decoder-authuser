//package com.ead.authuser.controllers;
//
//import com.ead.authuser.dtos.UserDto;
//import com.ead.authuser.enums.UserStatus;
//import com.ead.authuser.enums.UserType;
//import com.ead.authuser.models.UserModel;
//import com.ead.authuser.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import java.time.LocalDateTime;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//@AutoConfigureMockMvc
//public class AuthenticationController_RegisterUserTests2 {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Mock
//    private UserService userService;
//
//    @Mock
//    private AuthenticationController authenticationController;
//
////    @Autowired
////    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() {
//
//    }
//    @Test
//    public void testRegisterUser() throws Exception {
//        authenticationController.setUserService(userService);
//        ObjectMapper objectMapper = new ObjectMapper();
//
//        // Preparar o objeto UserDto válido
//        UserDto userDto = new UserDto();
//        userDto.setUsername("user1");
//        userDto.setEmail("user1@example.com");
//        userDto.setPassword("password1");
//        userDto.setFullName("First Last");
//        userDto.setPhoneNumber("(48) 99154-9999)");
//        userDto.setCpf("03892099999");
//
//        // Mockar o serviço userService.existsByUsername() para retornar false
//        when(userService.existsByUsername(userDto.getUsername())).thenReturn(false);
//        // Mockar o serviço userService.existsByEmail() para retornar false
//        when(userService.existsByEmail(userDto.getEmail())).thenReturn(false);
//        // Mockar o serviço userService.existsByEmail() para retornar false
//        doNothing().when(userService).save(any(UserModel.class));
//
//        // Fazer a requisição POST /signup com o objeto UserDto
//        MvcResult mvcResult = mockMvc.perform(post("/auth/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userDto)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        // Verificar a resposta da requisição
//        UserModel responseUserModel = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserModel.class);
//        assertNotNull(responseUserModel.getUserId());
//        assertEquals(userDto.getUsername(), responseUserModel.getUsername());
//        assertEquals(userDto.getEmail(), responseUserModel.getEmail());
//        assertEquals(userDto.getPassword(), responseUserModel.getPassword());
//        assertEquals(userDto.getFullName(), responseUserModel.getFullName());
//        assertEquals(userDto.getPhoneNumber(), responseUserModel.getPhoneNumber());
//        assertEquals(userDto.getCpf(), responseUserModel.getCpf());
//        assertEquals(UserStatus.ACTIVE, responseUserModel.getUserStatus());
//        assertEquals(UserType.STUDENT, responseUserModel.getUserType());
//        assertEquals(LocalDateTime.class.getName(), responseUserModel.getCreationDate().getClass().getName());
//        assertEquals(LocalDateTime.class.getName(), responseUserModel.getLastUpdateDate().getClass().getName());
//
//
//    }
//
//
//}
