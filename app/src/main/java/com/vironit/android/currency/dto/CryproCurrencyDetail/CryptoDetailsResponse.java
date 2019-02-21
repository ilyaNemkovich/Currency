
package com.vironit.android.currency.dto.CryproCurrencyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class CryptoDetailsResponse {

    @SerializedName("data")
    @Expose
    private Map<String, CurrencyData> data = null;
    @SerializedName("status")
    @Expose
    private Status status;

    public Map<String, CurrencyData> getData() {
        return data;
    }

    public void setData(Map<String, CurrencyData> data) {
        this.data = data;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
