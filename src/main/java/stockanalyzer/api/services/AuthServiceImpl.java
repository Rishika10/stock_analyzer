package stockanalyzer.api.services;

import java.util.Date;

import org.springframework.security.crypto.bcrypt.BCrypt;

import exceptionHandling.BadCredentialsException;
import exceptionHandling.EmailAlreadyExistsException;
import exceptionHandling.UserNotFoundException;
import exceptionHandling.UsernameAlreadyExistsException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import repository.UserRepository;
import stockanalyzer.api.models.User;


public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;

    public AuthServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void signup(User user) throws UsernameAlreadyExistsException, EmailAlreadyExistsException {
        // Validate the user object

        // Check if the user already exists
        if (userRepository.existsByUsernameOrEmail(user.getUsername(), user.getEmail())) {
            throw new UsernameAlreadyExistsException();
        }

        // Generate a password hash
        String passwordHash = BCrypt.hashpw(user.getPasswordHash(), BCrypt.gensalt());

        // Set the user's password hash
        user.setPasswordHash(passwordHash);

        // Save the user account to the database
        userRepository.save(user);
    }

    @Override
    public String signin(User user) throws UserNotFoundException, BadCredentialsException {
        // Validate the user object

        // Check if the user exists in the database
        User existingUser = userRepository.findByUsernameOrEmail(user.getUsername(), user.getEmail());
        if (existingUser == null) {
            throw new UserNotFoundException();
        }

        // Verify the password
        boolean passwordMatches = BCrypt.checkpw(user.getPasswordHash(), existingUser.getPasswordHash());
        if (!passwordMatches) {
            throw new BadCredentialsException();
        }

        // Generate a JWT token
        String token = Jwts.builder()
                .setSubject(user.getUsername())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 60 minutes
                .signWith(SignatureAlgorithm.HS512, "secret")
                .compact();

        // Return the JWT token
        return token;
    }
}