package ru.vatolin.weatherapplication.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.vatolin.weatherapplication.entity.User;
import ru.vatolin.weatherapplication.repository.UserRepository;

import java.util.Optional;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Test
    public void userIsExistTest_exist() {
        User user = new User();
        user.setId(1L);
        user.setLogin("test@mail.com");
        user.setPassword("password");

        when(userRepository.findByLogin("test@mail.com")).thenReturn(Optional.of(user));

        Assertions.assertTrue(userService.userIsExist("test@mail.com"));
    }

    @Test
    public void userIsExistTest_notExist() {
        when(userRepository.findByLogin("test@mail.com")).thenReturn(Optional.empty());

        Assertions.assertFalse(userService.userIsExist("test@mail.com"));
    }
}
