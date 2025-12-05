package pl.investtrack.investtrack.Controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.*;
import pl.investtrack.investtrack.DTO.AssetDTO;
import pl.investtrack.investtrack.DTO.AssetValueDTO;
import pl.investtrack.investtrack.Service.AssetService;
import pl.investtrack.investtrack.Entities.Asset;

import java.util.List;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/assets")
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/{userId}")
    public List<Asset> getAllAssets(@PathVariable Integer userId){
        return assetService.getAllAssets(userId);
    }
    @PostMapping
    public void addAsset(@RequestBody @Valid AssetDTO assetDTO){
        log.info("added asset ");
        assetService.buyAsset(assetDTO.ticker(), assetDTO.amount(),assetDTO.purchasePrice(),assetDTO.userId());

    }
    @GetMapping("/{userId}/price")
    public List<AssetValueDTO> getAssetsPrice (@PathVariable Integer userId){
        return assetService.getPortfolioWithValues(userId);
    }
}
