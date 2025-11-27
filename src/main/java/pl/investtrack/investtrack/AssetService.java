package pl.investtrack.investtrack;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AssetService {
    private final AssetRepository assetRepository;

    public void buyAsset(String ticker, BigDecimal amount, BigDecimal purchasePrice,Integer userId){
        log.info("bought "+ ticker + " amount: "+ amount + " Price: "+ purchasePrice);
        Asset assets = new Asset(ticker,amount,purchasePrice,1);
          assetRepository.save(assets);
    }
   public List<Asset> getAllAssets(Integer userId){
        return assetRepository.findAllByUserId(userId);

   }
}
