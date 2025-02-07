package com.xemyru.naryanonline.controller;


import com.xemyru.naryanonline.dto.navigation.NavigationRoute;
import com.xemyru.naryanonline.service.routing.NavigationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.security.PermitAll;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/navigation")
@Tag(name = "Navigation", description = "Navigation API")
public class NavigationController {

    private final NavigationService navigationService;

    @PermitAll
    @GetMapping("/route/{houseId}")
    @Operation(summary = "Calculate route to the house", description = "Calculate the " +
            "route to the house with the given houseId")
    public NavigationRoute calculateRoute(@PathVariable("houseId") Integer houseId) {
        return navigationService.calculateRoute(houseId);
    }
}