package pl.investtrack.investtrack.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import java.math.BigDecimal;

public record AssetDTO(@NotBlank String ticker, @NotNull  @Positive  BigDecimal amount,@NotNull @Positive BigDecimal purchasePrice,@Positive int userId) {
}