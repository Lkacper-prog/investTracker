package pl.investtrack.investtrack.ai.dto;

import jakarta.validation.constraints.NotNull;

public record AiRequestDTO(@NotNull String prompt) {
}
