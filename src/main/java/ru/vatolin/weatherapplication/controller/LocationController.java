package ru.vatolin.weatherapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.vatolin.weatherapplication.service.LocationService;
import ru.vatolin.weatherapplication.service.UserService;

import java.math.BigDecimal;
import java.security.Principal;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;
    private final UserService userService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String mainPage(@RequestParam(required = false) String location, Model model, Principal principal) {
        model.addAttribute("user", userService.getFormatedAuthenticatedLogin());
        model.addAttribute("locations", userService.getLocations(principal.getName()));
        if(location != null) model.addAttribute("searchQuery", location);
        return "/index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public String searchPage(@RequestParam(required = false) String location, Model model) {
        model.addAttribute("user", userService.getFormatedAuthenticatedLogin());
        if(location != null) model.addAttribute("locations", locationService.getSearchedLocations(location));
        if(location != null) model.addAttribute("searchQuery", location);
        return "/search-results";
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/add-location")
    public String addLocation(@RequestParam BigDecimal latitude, @RequestParam BigDecimal longitude, Principal principal) {
        userService.addLocationForUser(latitude, longitude, principal.getName());
        return "redirect:/main";
    }
}
