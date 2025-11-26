package pl.investtrack.investtrack;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AssetsRepository extends JpaRepository<Assets, Integer>{
    List<Assets> findAllByUserId(Integer userId);

}
