package com.xemyru.naryanonline.service.auth;

import com.xemyru.naryanonline.config.jwt.JwtService;
import com.xemyru.naryanonline.dto.auth.AuthenticationResponse;
import com.xemyru.naryanonline.dto.auth.SignInRequest;
import com.xemyru.naryanonline.dto.auth.SignUpRequest;
import com.xemyru.naryanonline.exceptions.AlreadyExistsException;
import com.xemyru.naryanonline.exceptions.InvalidPasswordException;
import com.xemyru.naryanonline.exceptions.NotFoundException;
import com.xemyru.naryanonline.repository.user.UserRepository;
import com.xemyru.naryanonline.tables.records.UsersRecord;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@RequiredArgsConstructor
@Service
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    private static final Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);

    @Override
    public AuthenticationResponse authenticate(SignUpRequest request) {
        logger.info("Authenticating user with email: {}", request.getEmail());
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new AlreadyExistsException("User with email " + request.getEmail() + " already exists");
        }

        String encodedPassword = passwordEncoder.encode(request.getPassword() );

        UsersRecord users = new UsersRecord();
        users.setEmail(request.getEmail() );
        users.setPassword(encodedPassword);
        users.setRole(request.getRole());

        UsersRecord createdUser = userRepository.save(users);

        String jwtToken = jwtService.generateToken(new HashMap<>(), createdUser);
        logger.info("User {} successfully registered with email: {}", createdUser.getId(), createdUser.getEmail());
        return new AuthenticationResponse(
                createdUser.getId(),
                createdUser.getEmail(),
                jwtToken,
                createdUser.getRole()
        );
    }

    @Override
    public AuthenticationResponse signIn(SignInRequest request) {
       UsersRecord usersRecord = userRepository.findByEmail(request.getEmail());

        if (usersRecord == null) {
            logger.warn("Sign-in failed: User with email {} not found", request.getEmail());
            throw new NotFoundException("User not found");
        }

        if (!passwordEncoder.matches(request.getPassword(), usersRecord.getPassword())) {
            logger.warn("Sign-in failed: Invalid password for email {}", request.getEmail());
            throw new InvalidPasswordException("Invalid password");
        }

        String jwtToken = jwtService.generateToken(new HashMap<>(), usersRecord);
        return new AuthenticationResponse(
                usersRecord.getId(),
                usersRecord.getEmail(),
                jwtToken,
                usersRecord.getRole()
        );
    }
}
