package pl.investtrack.investtrack.asset;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.investtrack.investtrack.asset.dto.AssetValueDTO;
import pl.investtrack.investtrack.market.MarketClient;
import pl.investtrack.investtrack.user.User;
import pl.investtrack.investtrack.user.UserNotFoundException;
import pl.investtrack.investtrack.user.UserRepository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Log4j2
@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final List<MarketClient> marketClient;
    private final UserRepository userRepository;

    @Transactional
    public void buyAsset(String ticker, BigDecimal amount, BigDecimal purchasePrice, Integer userId) {
        log.info("bought " + ticker + " amount: " + amount + " Price: " + purchasePrice);
        User user = userRepository.findById(userId).orElseThrow(() -> new UserNotFoundException("nie ma takiego użytkownika"));


        Asset assets = new Asset(ticker, amount, purchasePrice, user);
        assetRepository.save(assets);
    }

    @Transactional(readOnly = true)
    public List<Asset> getAllAssets(Integer userId) {
        return assetRepository.findAllByUserId(userId);
    }

    @Transactional(readOnly = true)
    public List<AssetValueDTO>  getPortfolioWithValues(Integer userId) {
        List<Asset> myAssets = assetRepository.findAllByUserId(userId);
        if (myAssets.isEmpty()) {
            return List.of();
        }

        Map<TypeOfAsset, List<String>> groupedTickers = myAssets.stream().collect(Collectors.groupingBy(Asset::getType, Collectors.mapping(Asset::getTicker, Collectors.collectingAndThen(Collectors.toSet(), ArrayList::new))));
        Map<String, BigDecimal> allPrices = new HashMap<>();

        for (Map.Entry<TypeOfAsset, List<String>> entry : groupedTickers.entrySet()) {
            TypeOfAsset typeOfAsset = entry.getKey();
            MarketClient client = marketClient.stream().filter(c -> c.supports()==typeOfAsset).findFirst().orElseThrow(()->new IllegalArgumentException("nie ma  klienta typu:" +  typeOfAsset));
            Map<String, BigDecimal> prices = client.getPrices(entry.getValue());
            allPrices.putAll(prices);
        }


        return myAssets.stream()
                .map(asset -> {
                    String ticker = asset.getTicker();
                    BigDecimal currentPrice = allPrices.getOrDefault(ticker, BigDecimal.ZERO);
                    BigDecimal totalVal = asset.getAmount().multiply(currentPrice);

                    return new AssetValueDTO(
                            asset.getTicker(),
                            asset.getAmount(),
                            asset.getPurchasePrice(),
                            currentPrice,
                            totalVal,
                            asset.getUser().getId());
                })
                .collect(Collectors.toList());
    }
}
