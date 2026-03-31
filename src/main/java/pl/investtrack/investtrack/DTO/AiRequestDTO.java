package pl.investtrack.investtrack.DTO;

import jakarta.validation.constraints.NotNull;

public record AiRequestDTO(@NotNull String prompt) {
}
