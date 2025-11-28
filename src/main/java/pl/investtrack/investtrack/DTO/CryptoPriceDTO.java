package pl.investtrack.investtrack.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CryptoPriceDTO( String ticker, BigDecimal price) {
}
