package ru.vatolin.weatherapplication.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vatolin.weatherapplication.dto.UserDto;
import ru.vatolin.weatherapplication.service.UserService;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping("/login")
    public String loginGet(@RequestParam(required = false) String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "invalid");
            return "sign-in-with-errors";
        }
        return "sign-in";
    }

    @GetMapping("/register")
    public String registerGet() {
        return "sign-up";
    }

    @PostMapping("/register")
    public String registerPost(HttpServletRequest request,
                               @RequestParam(name = "username") String login,
                               @RequestParam(name = "password") String password,
                               @RequestParam(name="passwordConfirmation") String confirmation,
                               @RequestParam(name="redirect_to") String redirectTo,
                               Model model) {

        if(password.length() < 4) {
            model.addAttribute("error", "length");
            return "sign-up-with-errors";
        }
        if(userService.userIsExist(login)) {
            model.addAttribute("error", "exists");
            return "sign-up-with-errors";
        }
        if(!password.equals(confirmation)) {
            model.addAttribute("error", "match");
            return "sign-up-with-errors";
        }

        UserDto userDto = new UserDto(login, password);

        userService.registerUser(userDto);

        try {
            request.login(login, password);
            return "redirect:" + redirectTo;
        } catch (ServletException e) {
            return "redirect:/login";
        }
    }
}
