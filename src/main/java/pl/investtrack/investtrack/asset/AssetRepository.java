package pl.investtrack.investtrack.asset;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

 interface AssetRepository extends JpaRepository<Asset, Integer>{
    List<Asset> findAllByUserId(Integer userId);

}
