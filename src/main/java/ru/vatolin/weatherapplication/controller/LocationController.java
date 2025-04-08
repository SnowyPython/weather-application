package ru.vatolin.weatherapplication.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import ru.vatolin.weatherapplication.service.LocationService;

@Controller
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/main")
    public String mainPage() {
        return "/index";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/search")
    public String searchPage() {
        return "/search-results";
    }
}
