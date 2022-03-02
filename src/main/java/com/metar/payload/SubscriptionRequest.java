package com.metar.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class SubscriptionRequest {
    @NotBlank
    @Size(max = 4)
    private String icaoCode;


    public SubscriptionRequest() {
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }

}
