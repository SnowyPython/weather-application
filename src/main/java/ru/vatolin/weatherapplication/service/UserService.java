package ru.vatolin.weatherapplication.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.vatolin.weatherapplication.dto.UserDto;
import ru.vatolin.weatherapplication.entity.User;
import ru.vatolin.weatherapplication.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public boolean userIsExist(String login) {
        User user = userRepository.findByLogin(login).orElse(null);

        return user != null;
    }

    public boolean passwordIsValid(UserDto userDto) {
        User user = userRepository.findByLogin(userDto.getLogin()).orElse(null);

        return passwordEncoder.matches(userDto.getPassword(), user.getPassword());
    }

    public void registerUser(UserDto userDto) {
        String password = passwordEncoder.encode(userDto.getPassword());

        User user = new User();
        user.setLogin(userDto.getLogin());
        user.setPassword(password);

        userRepository.save(user);
    }
}
