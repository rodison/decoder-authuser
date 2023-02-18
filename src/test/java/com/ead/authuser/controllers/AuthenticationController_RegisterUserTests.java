//package com.ead.authuser.controllers;
//
//import com.ead.authuser.dtos.UserDto;
//import com.ead.authuser.services.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.Before;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.MvcResult;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//import org.springframework.web.context.WebApplicationContext;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@SpringBootTest
//public class AuthenticationController_RegisterUserTests {
//
//    private MockMvc mockMvc;
//
//    private ObjectMapper objectMapper;
//
//    @Mock
//    private UserService userService;
//
//    @Autowired
//    private WebApplicationContext webApplicationContext;
//
//    @Before
//    public void setUp() {
//        objectMapper = new ObjectMapper();
//        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
//    }
//    @Test
//    public void testRegisterUser() throws Exception {
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
//
//        // Fazer a requisição POST /signup com o objeto UserDto
//        MvcResult mvcResult = mockMvc.perform(post("/signup")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(objectMapper.writeValueAsString(userDto)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        // Verificar a resposta da requisição
//        UserDto responseUserDto = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), UserDto.class);
//        assertEquals(userDto.getUsername(), responseUserDto.getUsername());
//        assertEquals(userDto.getEmail(), responseUserDto.getEmail());
//        assertEquals(userDto.getPassword(), responseUserDto.getPassword());
//        assertEquals(userDto.getFullName(), responseUserDto.getFullName());
//        assertEquals(userDto.getPhoneNumber(), responseUserDto.getPhoneNumber());
//        assertEquals(userDto.getCpf(), responseUserDto.getCpf());
//
//    }
//
//
//}
