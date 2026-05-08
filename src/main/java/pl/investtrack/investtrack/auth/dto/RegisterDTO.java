package pl.investtrack.investtrack.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record RegisterDTO(@NotBlank String name, @Email @NotBlank String email, String password) {
}
