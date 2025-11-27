package pl.investtrack.investtrack;

import org.antlr.v4.runtime.misc.NotNull;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;

public record AssetDTO(@NotNull String ticker, BigDecimal amount, BigDecimal purchasePrice,int userId) {
}