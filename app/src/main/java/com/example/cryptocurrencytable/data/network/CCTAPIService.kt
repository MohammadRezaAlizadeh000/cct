package com.example.cryptocurrencytable.data.network

import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CCTAPIService {

    @GET("exchanges/")
    fun getExchangesList(): Call<List<SingleExchangeResponseModel>>

    @GET("/exchanges/{exchange_id}")
    fun getSingleExchange(
        @Path("exchange_id") exchangeId: String
    ): Response<SingleExchangeResponseModel>
}
