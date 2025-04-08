package com.treinetic.treinetic.api;


import com.treinetic.treinetic.business.AuthService;
import com.treinetic.treinetic.business.DTOs.AuthRequest;
import com.treinetic.treinetic.business.DTOs.RegisterRequest;
import com.treinetic.treinetic.business.JwtService;
import com.treinetic.treinetic.data.User;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(
        origins = "http://localhost:4200",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE}
)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final JwtService jwtService;

    private final AuthService authService;

    public AuthController(JwtService jwtService, AuthService authService) {
        this.jwtService = jwtService;
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody RegisterRequest signUpRequest) {
//        System.out.println(signUpRequest.getEmail());
        User createdUser = authService.registerUser(signUpRequest);

        if (createdUser == null){
            return ResponseEntity.badRequest().body("User could not be created.");
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@Valid @RequestBody AuthRequest loginUserDto) {
        System.out.println(loginUserDto.getEmail()+" "+loginUserDto.getPassword());
        User authenticatedUser = authService.loginUser(loginUserDto);


//        LoginResponse loginResponse = new LoginResponse().setToken(jwtToken).setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(authenticatedUser);
    }
}