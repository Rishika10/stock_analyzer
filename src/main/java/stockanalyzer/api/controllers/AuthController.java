package stockanalyzer.api.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import stockanalyzer.api.models.User;
import stockanalyzer.api.services.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody User user) {
        authService.signup(user);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/signin")
    public ResponseEntity<String> signin(@RequestBody User user) {
        String token = authService.signin(user);

        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();
        builder.body(token);
        ResponseEntity<String> responseEntity = builder.build();

        return responseEntity;
    }

}
