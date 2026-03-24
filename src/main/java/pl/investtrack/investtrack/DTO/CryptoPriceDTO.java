package pl.investtrack.investtrack.DTO;

import java.math.BigDecimal;

public record CryptoPriceDTO( String ticker, BigDecimal price) {
}
