package pl.investtrack.investtrack.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.investtrack.investtrack.Client.CoinGeckoClient;
import pl.investtrack.investtrack.DTO.AssetValueDTO;
import pl.investtrack.investtrack.DTO.CryptoPriceDTO;
import pl.investtrack.investtrack.Entities.AssetRepository;
import pl.investtrack.investtrack.Entities.Asset;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;
    private final CoinGeckoClient coinGeckoClient;

    public void buyAsset(String ticker, BigDecimal amount, BigDecimal purchasePrice,Integer userId){
        log.info("bought "+ ticker + " amount: "+ amount + " Price: "+ purchasePrice);
        Asset assets = new Asset(ticker,amount,purchasePrice,1);
          assetRepository.save(assets);
    }
   public List<Asset> getAllAssets(Integer userId){
        return assetRepository.findAllByUserId(userId);

   }
   public List<AssetValueDTO> getPortfolioWithValues(Integer userId){
        List<Asset> myAssets= assetRepository.findAllByUserId(userId);
        if (myAssets.isEmpty()){
            return List.of();
        }
        List<String> tickers = myAssets.stream().map(Asset::getTicker).distinct().toList();
       Map<String,BigDecimal> prices= coinGeckoClient.getPrices(tickers);

       return myAssets.stream()
               .map(asset -> {
                   String ticker = asset.getTicker();
                   BigDecimal currentPrice = prices.getOrDefault(ticker,BigDecimal.ZERO);
                   BigDecimal totalVal= asset.getAmmount().multiply(currentPrice);

                   return new AssetValueDTO(
                           asset.getTicker(),
                           asset.getAmmount(),
                           asset.getPurchasePrice(),
                           currentPrice,
                           totalVal,
                           asset.getUserId()
                   );
               })
               .collect(Collectors.toList());
   }
}
