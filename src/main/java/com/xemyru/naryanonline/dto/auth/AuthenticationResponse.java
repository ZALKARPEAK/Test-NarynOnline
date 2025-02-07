package com.xemyru.naryanonline.dto.auth;


import com.xemyru.naryanonline.enums.Role;

public record AuthenticationResponse (
        int id,
        String email,
        String token,
        Role role
) {
}