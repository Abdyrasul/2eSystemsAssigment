package com.metar.repo;

import com.metar.model.Metar;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface MetarRepository extends JpaRepository<Metar, Long>{
    Optional<Metar> findById(Long metarId);

    @Transactional
    @Query(value = "SELECT * from metars WHERE subscription_id = ?1 ORDER BY date_time DESC LIMIT 1", nativeQuery = true)
    Metar findLastMetarData(Long subscriptionId);

    @Transactional
    void removeBySubscriptionId(Long subscriptionId);

}
