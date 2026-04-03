package pl.investtrack.investtrack.asset.dto;

import java.math.BigDecimal;

public record AssetValueDTO(String ticker, BigDecimal amount, BigDecimal purchasePrice, BigDecimal currentPrice,
                            BigDecimal totalValue, Integer userId) {
}
