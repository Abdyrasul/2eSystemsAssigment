package com.metar.service;

import com.metar.model.Subscription;
import com.metar.payload.ApiResponse;
import com.metar.payload.SubscriptionRequest;
import com.metar.repo.MetarRepository;
import com.metar.repo.SubscriptionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@Service
public class SubscriptionService {

    @Autowired
    private SubscriptionRepository subscriptionRepository;

    @Autowired
    private MetarRepository metarRepository;

    public ResponseEntity<?> SaveSubscription(SubscriptionRequest subscription){
        if(subscriptionRepository.existsByIcaoCode(subscription.getIcaoCode())){
                return new ResponseEntity(new ApiResponse(false, subscription.getIcaoCode()+ " is already stored!"),
                        HttpStatus.BAD_REQUEST);
        }
        Subscription newItem = new Subscription(subscription.getIcaoCode(),null);

        Subscription result = subscriptionRepository.save(newItem);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{pollId}")
                .buildAndExpand(result.getId()).toUri();
        return ResponseEntity.created(location).body(new ApiResponse(true, result.getIcaoCode()+ " stored successfully"));
    }

    public List<SubscriptionRequest> GetSubscriptions(){
        List<SubscriptionRequest> responseList = new ArrayList<>();
        List<Subscription> all = subscriptionRepository.findAll();
//        System.out.println("Size: "+all.size());
        for (Subscription sub: all){
            SubscriptionRequest newSub = new SubscriptionRequest();
            newSub.setIcaoCode(sub.getIcaoCode());
            responseList.add(newSub);
        }
        return responseList;
    }

    public ResponseEntity<?> DeleteSubscription(String icaoCode){
        if(!subscriptionRepository.existsByIcaoCode(icaoCode)){
            return new ResponseEntity(new ApiResponse(false, "Airport not found"),
                    HttpStatus.BAD_REQUEST);
        }

        Subscription subscription = subscriptionRepository.findByIcaoCode(icaoCode);
        metarRepository.removeBySubscriptionId(subscription.getId());
        subscriptionRepository.removeByIcaoCode(icaoCode);
        return ResponseEntity.ok().body(new ApiResponse(true, icaoCode + " successfully removed"));
    }
}
