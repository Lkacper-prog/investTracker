package pl.investtrack.investtrack.Controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import pl.investtrack.investtrack.Client.CoinGeckoClient;
import pl.investtrack.investtrack.DTO.CryptoPriceDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/prices")
public class PriceController {
    private  final CoinGeckoClient coinGeckoClient;
    @GetMapping
    public Map<String,BigDecimal> getPrices(@RequestParam List<String> tickers){
        return coinGeckoClient.getPrices(tickers);
    }
}
