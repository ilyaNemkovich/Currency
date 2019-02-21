
package com.vironit.android.currency.dto.CryproCurrencyDetail;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Data {

    @SerializedName("CurrencyData")
    @Expose
    private CurrencyData bTC;

    public CurrencyData getBTC() {
        return bTC;
    }

    public void setBTC(CurrencyData bTC) {
        this.bTC = bTC;
    }

}
