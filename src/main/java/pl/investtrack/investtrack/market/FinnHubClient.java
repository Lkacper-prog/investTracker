package pl.investtrack.investtrack.market;

import com.fasterxml.jackson.databind.JsonNode;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;
import pl.investtrack.investtrack.asset.TypeOfAsset;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Log4j2
@RequiredArgsConstructor
@Component
public class FinnHubClient implements MarketClient {

    private final RestClient restClient = RestClient.create();
    private final static String FinnHubURL = "https://finnhub.io/api/v1/";
    @Value("${finnhub.api.key}")
    private String apiKey;

    @Override
    public TypeOfAsset supports() {
        return TypeOfAsset.STOCK;
    }

    @Override
    public Map<String, BigDecimal> getPrices(List<String> tickers) {
        if (tickers == null || tickers.isEmpty()) {
            return Collections.emptyMap();
        }
        String tickersJoined = String.join(",", tickers);
        log.info("pobieranie cen akcji : {}", tickersJoined);
        Map<String, BigDecimal> prices = new HashMap<>();
        try {
            tickers.forEach(ticker -> {
                JsonNode response = restClient.get().uri(FinnHubURL + "quote?symbol=" + ticker + "&token=" + apiKey).retrieve().body(JsonNode.class);
                if (response == null) {
                    log.error("Pusta lub błędna odpowiedź z FinnHub dla tickera: {}", ticker);
                } else {
                    BigDecimal price = response.get("c").decimalValue();
                    prices.put(ticker, price);
                }
            });
        } catch (Exception ex) {
            log.error("Błąd podczas komunikacji z API FinnHubClient: {}", ex.getMessage());
            throw new ExternalApiException("Nie udało się pobrać aktualnych cen z zewnętrznego serwera.");
        }


        return prices;
    }
}
