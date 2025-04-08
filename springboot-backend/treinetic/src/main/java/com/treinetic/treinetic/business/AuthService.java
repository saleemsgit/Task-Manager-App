package com.treinetic.treinetic.business;

import com.treinetic.treinetic.business.DTOs.AuthRequest;
import com.treinetic.treinetic.business.DTOs.RegisterRequest;
import com.treinetic.treinetic.data.User;
import com.treinetic.treinetic.data.UserRepository;
import com.treinetic.treinetic.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtUtils jwtUtils;


    @Autowired
    public AuthService(
            UserRepository userRepository,
            PasswordEncoder encoder,
            JwtUtils jwtUtils
    ){
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.jwtUtils = jwtUtils;

    }
    public User registerUser(RegisterRequest signUpRequest){

        User user = new User();
        user.setEmail(signUpRequest.getEmail());
        user.setFullName(signUpRequest.getFullName());
        user.setPassword(encoder.encode(signUpRequest.getPassword()));
        user.setCreatedAt(new Date());

        return userRepository.registerUser(user);
    }

    public User loginUser(AuthRequest loginRequest){
//        Authentication authentication = authenticationManager
//                .authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
//
//        SecurityContextHolder.getContext().setAuthentication(authentication);
//
//
//        return null;
        Boolean user = userRepository.findEmail(loginRequest.getEmail());

        if (!user) {
            return null;
        }

        User existingUser = userRepository.findUserByEmail(loginRequest.getEmail());

        if (!encoder.matches(loginRequest.getPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Error: Invalid credentials.");
        }

        String jwt = jwtUtils.generateJwtToken(existingUser);  // generate token based on user details
        return existingUser;
    }
}
