package com.example.cryptocurrencytable.usecase

import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import com.example.cryptocurrencytable.model.SingleExchangeModel
import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single

interface ExchangesUseCase {

    fun getAllExchanges(): Single<AppState<List<SingleExchangeModel>>>

    fun getSingleExchange(exchangeId: String): Single<AppState<SingleExchangeResponseModel>>

}