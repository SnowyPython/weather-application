package ru.vatolin.weatherapplication.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UserDto {
    @NotBlank(message = "Email can't be blank")
    @Email(message = "Invalid Email")
    private String login;

    @NotBlank(message = "Password can't be blank")
    @Size(min = 4, max = 100, message = "The password must be between 4 and 100")
    private String password;
}
