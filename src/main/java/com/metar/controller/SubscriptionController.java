package com.metar.controller;

import com.metar.model.Subscription;
import com.metar.payload.SubscriptionRequest;
import com.metar.service.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/subscription")
public class SubscriptionController {

    @Autowired
    SubscriptionService subscriptionService;

    @PostMapping()
    public ResponseEntity<?> SaveSubscription(@Valid @RequestBody SubscriptionRequest subscriptionRequest){
        return subscriptionService.SaveSubscription(subscriptionRequest);
    }

    @GetMapping("/all")
    public List<SubscriptionRequest> GetAllSubscriptions(){
        return subscriptionService.GetSubscriptions();
    }

    @DeleteMapping("/{icaoCode}")
    public ResponseEntity<?> DeleteSubscription(@PathVariable String icaoCode){
        return subscriptionService.DeleteSubscription(icaoCode);
    }
}
