package com.metar.service;

import com.metar.model.Metar;
import com.metar.model.Subscription;
import com.metar.payload.ApiResponse;
import com.metar.payload.MetarRequest;
import com.metar.payload.MetarResponse;
import com.metar.repo.MetarRepository;
import com.metar.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Optional;

@Service
public class MetarService {

    @Autowired
    private MetarRepository metarRepository;

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public ResponseEntity<?> SaveMetarData(MetarRequest metarRequest){
        if(!subscriptionRepository.existsByIcaoCode(metarRequest.getIcaoCode())){
            return new ResponseEntity(new ApiResponse(false, "Airport not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Subscription subscription = subscriptionRepository.findByIcaoCode(metarRequest.getIcaoCode());
        Metar metar = new Metar(metarRequest.getData(), Instant.now(),subscription);

        metarRepository.save(metar);
        return ResponseEntity.ok().body(new ApiResponse(true, "Data stored successfully"));
    }

    public ResponseEntity<?> GetLastMetarDataBySubscription(String icaoCode){
        if(!subscriptionRepository.existsByIcaoCode(icaoCode)){
            return new ResponseEntity(new ApiResponse(false, "Airport not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Subscription subscription = subscriptionRepository.findByIcaoCode(icaoCode);

        Metar result = metarRepository.findLastMetarData(subscription.getId());

        if(result!=null){
            return ResponseEntity.ok().body(new MetarResponse(result.getMetarData()));
        }else{
            return new ResponseEntity(new ApiResponse(false, "No data"),
                    HttpStatus.BAD_REQUEST);
        }


    }
}
