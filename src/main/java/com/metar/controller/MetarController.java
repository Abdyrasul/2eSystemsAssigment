package com.metar.controller;

import com.metar.model.Metar;
import com.metar.model.Subscription;
import com.metar.payload.MetarRequest;
import com.metar.service.MetarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/airport")
public class MetarController {

    @Autowired
    MetarService metarService;

    @PostMapping("/METAR")
    public ResponseEntity<?> SaveMetarData(@Valid @RequestBody MetarRequest metarRequest){
        return metarService.SaveMetarData(metarRequest);
    }

    @GetMapping("/{icaoCode}/METAR")
    public ResponseEntity<?> GetLastMetarData(@PathVariable String icaoCode){
        return metarService.GetLastMetarDataBySubscription(icaoCode);
    }

}
