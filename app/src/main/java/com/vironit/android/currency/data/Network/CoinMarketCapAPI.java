package com.vironit.android.currency.data.Network;

import com.vironit.android.currency.dto.CryproCurrency.CryptoCurrencyResponse;
import com.vironit.android.currency.dto.CryproCurrencyDetail.CryptoDetailsResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface CoinMarketCapAPI {
    @GET("/v1/cryptocurrency/listings/latest")
    Single<CryptoCurrencyResponse> getCryptoCurrency(@Query("limit") Integer limit);

    @GET("/v1/cryptocurrency/info")
    Single<CryptoDetailsResponse> getCryptoCurrencyDetails(@Query("id") String id);
}
