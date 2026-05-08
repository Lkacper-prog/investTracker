package pl.investtrack.investtrack.auth.dto;

import jakarta.validation.constraints.Email;

public record LoginDTO(@Email String email, String password) {
}
