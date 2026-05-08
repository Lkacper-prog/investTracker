package pl.investtrack.investtrack.auth;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.http.auth.InvalidCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.investtrack.investtrack.auth.dto.JwtResponseDTO;
import pl.investtrack.investtrack.auth.dto.LoginDTO;
import pl.investtrack.investtrack.auth.dto.RegisterDTO;
import pl.investtrack.investtrack.user.User;
import pl.investtrack.investtrack.user.UserNotFoundException;
import pl.investtrack.investtrack.user.UserRepository;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @PostMapping("/login")
    public JwtResponseDTO login(@RequestBody @Valid LoginDTO dto) throws InvalidCredentialsException {
        Optional<User> byEmail = userRepository.findByEmail(dto.email());
        if (!byEmail.isPresent()) {
            throw new UserNotFoundException("Invalid email");
        }
        if (!passwordEncoder.matches(dto.password(), byEmail.get().getPassword())) {
            throw new InvalidCredentialsException("Invalid password");
        }


        String token = jwtService.generateToken(dto.email());
        return new JwtResponseDTO(token);
    }

    @PostMapping("/register")
    public JwtResponseDTO register(@RequestBody @Valid RegisterDTO dto) throws InvalidCredentialsException {
        if (userRepository.findByEmail(dto.email()).isPresent()) {
            throw new IllegalArgumentException("Invalid email");
        }
        String codedPassword = passwordEncoder.encode(dto.password());
        userRepository.save(new User(dto.name(), codedPassword, dto.email()));
        return new JwtResponseDTO(jwtService.generateToken(dto.email()));
    }
}
