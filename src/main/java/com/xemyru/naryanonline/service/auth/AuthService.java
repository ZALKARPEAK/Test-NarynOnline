package com.xemyru.naryanonline.service.auth;

import com.xemyru.naryanonline.dto.auth.AuthenticationResponse;
import com.xemyru.naryanonline.dto.auth.SignInRequest;
import com.xemyru.naryanonline.dto.auth.SignUpRequest;

public interface AuthService {
    AuthenticationResponse authenticate(SignUpRequest request);
    AuthenticationResponse signIn(SignInRequest request);
}