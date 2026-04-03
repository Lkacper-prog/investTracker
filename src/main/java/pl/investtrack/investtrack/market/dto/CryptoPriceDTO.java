package pl.investtrack.investtrack.market.dto;

import java.math.BigDecimal;

public record CryptoPriceDTO( String ticker, BigDecimal price) {
}
