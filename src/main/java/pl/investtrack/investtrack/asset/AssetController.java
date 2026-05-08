package pl.investtrack.investtrack.asset;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.investtrack.investtrack.asset.dto.AssetDTO;
import pl.investtrack.investtrack.asset.dto.AssetValueDTO;
import pl.investtrack.investtrack.user.User;

import java.util.List;
@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/assets")
public class AssetController {
    private final AssetService assetService;

    @GetMapping("/")
    public List<Asset> getAllAssets(){
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        return assetService.getAllAssets(id);
    }
    @PostMapping
    public void addAsset(@RequestBody @Validated AssetDTO assetDTO){
        log.info("added asset ");
        User currentUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        assetService.buyAsset(assetDTO.ticker(), assetDTO.amount(),assetDTO.purchasePrice(),id);

    }
    @GetMapping("/price")
    public List<AssetValueDTO> getAssetsPrice (){
        User currentUser=(User)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Integer id = currentUser.getId();
        return assetService.getPortfolioWithValues(id);
    }
}
