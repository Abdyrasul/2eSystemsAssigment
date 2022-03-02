package com.metar.payload;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class MetarRequest {

    @NotBlank
    @Size(max = 4)
    private String icaoCode;

    @NotBlank
    @Size(max = 1000)
    private String data;

    public MetarRequest() {
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getIcaoCode() {
        return icaoCode;
    }

    public void setIcaoCode(String icaoCode) {
        this.icaoCode = icaoCode;
    }
}
