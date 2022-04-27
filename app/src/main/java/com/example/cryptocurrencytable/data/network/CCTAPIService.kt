package com.example.cryptocurrencytable.data.network

import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import com.example.cryptocurrencytable.utils.AUTHENTICATION_TOKEN
import com.example.cryptocurrencytable.utils.HEADER_AUTHENTICATION_TOKEN
import com.example.cryptocurrencytable.utils.HEADER_CONTENT_TYPE
import com.example.cryptocurrencytable.utils.HEADER_CONTENT_TYPE_VALUE
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path

interface CCTAPIService {

    @GET("exchanges/")
    fun getExchangesList(): Call<List<ExchangesResponseModelItem>>

    @GET("/exchanges/{exchange_id}")
    fun getSingleExchange(
        @Path("exchange_id") exchangeId: String
    ): Response<ExchangesResponseModelItem>
}
