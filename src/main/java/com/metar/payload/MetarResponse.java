package com.metar.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MetarResponse {

    @NotBlank
    @Size(max = 100)
    private String data;

    public MetarResponse(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
