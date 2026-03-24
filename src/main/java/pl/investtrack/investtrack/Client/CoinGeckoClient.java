package pl.investtrack.investtrack.Client;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import com.fasterxml.jackson.databind.JsonNode;
import pl.investtrack.investtrack.Exception.ExternalApiException;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@RequiredArgsConstructor
@Component
public class CoinGeckoClient {
    private final RestClient restClient = RestClient.create();
    private static final String COINGECKO_URL = "https://api.coingecko.com/api/v3/simple/price";

    public Map<String, BigDecimal> getPrices(List<String> tickers) {
        if (tickers.isEmpty()) {
            return new HashMap<>();
        }
        String tickersJoined = String.join(",", tickers);
        log.info("pobieranie cen kryptowalut : {}", tickersJoined);
        try {
            JsonNode response = restClient.get().uri(COINGECKO_URL + "?ids=" + tickersJoined + "&vs_currencies=usd").retrieve().body(JsonNode.class);
            if (response == null) {
                log.error(" pusta  odpowiedz z CoinGecko");
                throw new ExternalApiException("Błąd pobierania ceny: Brak danych");
            }
            Map<String, BigDecimal> prices = new HashMap<>();
            for (String ticker : tickers) {
                if (response.has(ticker) && response.get(ticker).has("usd")) {
                    BigDecimal price = response.get(ticker).get("usd").decimalValue();
                    prices.put(ticker, price);
                }
            }
            return prices;
        } catch (Exception e) {
            log.error("Błąd podczas komunikacji z API CoinGecko: {}", e.getMessage());
            throw new ExternalApiException("Nie udało się pobrać aktualnych cen z zewnętrznego serwera.");
        }
    }
}