package com.metar.repo;

import com.metar.model.Metar;
import com.metar.model.Subscription;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface SubscriptionRepository extends JpaRepository<Subscription, Long> {
    Boolean existsByIcaoCode(String icaoCode);

    Subscription findByIcaoCode(String icaoCode);

    @Transactional
    void removeByIcaoCode(String icaoCode);

}
