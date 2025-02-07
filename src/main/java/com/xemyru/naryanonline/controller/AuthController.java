package com.xemyru.naryanonline.controller;

import com.xemyru.naryanonline.dto.auth.AuthenticationResponse;
import com.xemyru.naryanonline.dto.auth.SignInRequest;
import com.xemyru.naryanonline.dto.auth.SignUpRequest;
import com.xemyru.naryanonline.service.auth.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication", description = " Authentication API")
public class AuthController {
    private final AuthService authenticationService;

    @PostMapping("/sign-up")
    @Operation(
            summary = "Sign Up",
            description = "Registers a new user with the provided sign-up details, such as username, email, and password. This endpoint creates a new user account in the system.",
            tags = {"Authentication API"}
    )
    public AuthenticationResponse signUp(
            @Parameter(description = "Sign-up request containing user details", required = true)
            @Valid @RequestBody SignUpRequest signUpRequest) {
        return authenticationService.authenticate(signUpRequest);
    }

    @PostMapping("/sign-in")
    @Operation(
            summary = "Sign In",
            description = "Authenticates the user and returns an authentication response, which includes an access token and other relevant information. The user must provide their valid sign-in credentials, such as email and password, to be authenticated.",
            tags = {"Authentication API"}
    )
    public AuthenticationResponse signIn(
            @Parameter(description = "Sign-in request containing user credentials", required = true)
            @RequestBody SignInRequest signInRequest) {
        return authenticationService.signIn(signInRequest);
    }
}
