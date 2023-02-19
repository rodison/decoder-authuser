package com.ead.authuser.services.impl;

import com.ead.authuser.models.UserModel;
import com.ead.authuser.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.mockito.Mockito.anyString;
import static org.mockito.Mockito.when;

class UserServiceImplTest {
    @Mock
    UserRepository userRepository;
    @InjectMocks
    UserServiceImpl userServiceImpl;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindAll() {
        when(userRepository.findAll()).thenReturn(List.of(new UserModel()));
        List<UserModel> result = userServiceImpl.findAll();
        Assertions.assertEquals(List.of(new UserModel()), result);
    }

    @Test
    void testFindById() {
        UserModel userModel = new UserModel();
        when(userRepository.findById(UUID.fromString("9d3676e4-4b31-4a35-bd27-94af5cf77725")))
                .thenReturn(Optional.of(userModel));
        Optional<UserModel> result = userServiceImpl.findById(UUID.fromString("9d3676e4-4b31-4a35-bd27-94af5cf77725"));
        Assertions.assertEquals(userModel, result.get());
    }

    @Test
    void testDelete() {
        userServiceImpl.delete(new UserModel());
    }

    @Test
    void testSave() {
        userServiceImpl.save(new UserModel());
    }

    @Test
    void testExistsByUsername_userExists() {
        when(userRepository.existsByUsername(anyString())).thenReturn(true);

        boolean result = userServiceImpl.existsByUsername("username");
        Assertions.assertEquals(true, result);
    }

    @Test
    void testExistsByUsername_userDoesntExist() {
        when(userRepository.existsByUsername(anyString())).thenReturn(false);

        boolean result = userServiceImpl.existsByUsername("username");
        Assertions.assertEquals(false, result);
    }

    @Test
    void testExistsByEmail() {
        when(userRepository.existsByEmail(anyString())).thenReturn(true);

        boolean result = userServiceImpl.existsByEmail("email");
        Assertions.assertEquals(true, result);
    }
}

//Generated with love by TestMe :) Please report issues and submit feature requests at: http://weirddev.com/forum#!/testme