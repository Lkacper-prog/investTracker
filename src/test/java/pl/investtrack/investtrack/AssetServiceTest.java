package pl.investtrack.investtrack;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import pl.investtrack.investtrack.Client.CoinGeckoClient;
import pl.investtrack.investtrack.DTO.AssetValueDTO;
import pl.investtrack.investtrack.Entities.Asset;
import pl.investtrack.investtrack.Entities.AssetRepository;
import pl.investtrack.investtrack.Service.AssetService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

public class AssetServiceTest {
    @Test
    void getPortfolioWithValuesTest (){
        Integer userId=1;
        AssetRepository assetRepository = Mockito.mock(AssetRepository.class);
        CoinGeckoClient coinGeckoClient = Mockito.mock(CoinGeckoClient.class);
        AssetService assetService = new AssetService(assetRepository,coinGeckoClient);
        List<Asset> list = List.of(new Asset(1,"bitcoin", BigDecimal.valueOf(2),BigDecimal.valueOf(3000),1));
        when(coinGeckoClient.getPrices(anyList())).thenReturn(Map.of("bitcoin", new BigDecimal("5000")));
        when(assetRepository.findAllByUserId(userId)).thenReturn(list);
        List<AssetValueDTO> result = assetService.getPortfolioWithValues(userId);
        assertThat(result.get(0).totalValue()).isEqualByComparingTo(new BigDecimal("10000"));
    }
}
