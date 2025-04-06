package ru.vatolin.weatherapplication.controller;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import ru.vatolin.weatherapplication.WeatherApplication;
import ru.vatolin.weatherapplication.repository.UserRepository;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest(classes = WeatherApplication.class)
@AutoConfigureMockMvc
@TestPropertySource(locations = "classpath:application-test.yaml")
public class UserControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepository userRepository;

    @AfterEach
    public void cleanup() {
        userRepository.deleteAll();
    }

    @Test
    public void registerGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/register")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("/sign-up"));
    }

    @Test
    public void registerPostTest_successfulRegistration() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", "test1@mail.com")
                        .param("password", "password")
                        .param("passwordConfirmation", "password")
                        .param("redirect_to", "/main"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main"));
    }

    @Test
    public void registerPostTest_shortPassword() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", "test2@mail.com")
                        .param("password", "123")
                        .param("passwordConfirmation", "123")
                        .param("redirect_to", "/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/sign-up-with-errors"))
                .andExpect(model().attribute("error", "length"));
    }

    @Test
    public void registerPostTest_userExist() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", "test2@mail.com")
                        .param("password", "password")
                        .param("passwordConfirmation", "password")
                        .param("redirect_to", "/main"));

        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", "test2@mail.com")
                        .param("password", "password")
                        .param("passwordConfirmation", "password")
                        .param("redirect_to", "/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/sign-up-with-errors"))
                .andExpect(model().attribute("error", "exists"));
    }

    @Test
    public void registerPostTest_badConfirmation() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                        .with(csrf())
                        .param("username", "test3@mail.com")
                        .param("password", "password")
                        .param("passwordConfirmation", "zzzzzzzz")
                        .param("redirect_to", "/main"))
                .andExpect(status().isOk())
                .andExpect(view().name("/sign-up-with-errors"))
                .andExpect(model().attribute("error", "match"));
    }

    @Test
    public void authorizationGet() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                        .with(csrf()))
                .andExpect(status().isOk())
                .andExpect(view().name("/sign-in"));
    }

    @Test
    public void authorizationGet_withError() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/login")
                        .with(csrf())
                        .param("error", "error"))
                .andExpect(status().isOk())
                .andExpect(view().name("sign-in-with-errors"))
                .andExpect(model().attribute("error", "invalid"));
    }

    @Test
    public void authorizationPost() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/register")
                .with(csrf())
                .param("username", "user@mail.com")
                .param("password", "password")
                .param("passwordConfirmation", "password")
                .param("redirect_to", "/main"));

        mockMvc.perform(MockMvcRequestBuilders.post("/login")
                        .with(csrf())
                        .param("username", "user@mail.com")
                        .param("password", "password"))
                .andExpect(status().is3xxRedirection())
                .andExpect(redirectedUrl("/main"));
    }
}
