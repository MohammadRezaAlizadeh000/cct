package com.example.cryptocurrencytable.usecase

import com.example.cryptocurrencytable.model.ExchangesResponseModel
import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single

interface ExchangesUseCase {

    fun getAllExchanges(): Single<AppState<List<ExchangesResponseModelItem>>>

    fun getSingleExchange(exchangeId: String): Single<AppState<ExchangesResponseModelItem>>

}