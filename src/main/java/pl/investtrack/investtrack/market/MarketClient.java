package pl.investtrack.investtrack.market;

import pl.investtrack.investtrack.asset.TypeOfAsset;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public interface MarketClient {
    TypeOfAsset supports();
    Map<String, BigDecimal> getPrices(List<String> tickers);
}
