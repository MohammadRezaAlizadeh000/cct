package com.example.cryptocurrencytable.data

import com.example.cryptocurrencytable.data.network.CCTAPIService
import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import com.example.cryptocurrencytable.utils.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cctAPIService: CCTAPIService,
    private val handlerImpl: RemoteDataSourceHandler
) {

    fun getAllExchanges(): Single<AppState<List<ExchangesResponseModelItem>>> {
        return handlerImpl.getCallback { cctAPIService.getExchangesList() }
    }

    fun getSingleExchange(exchangeId: String): Single<AppState<ExchangesResponseModelItem>> {
        return handlerImpl.getResponse { cctAPIService.getSingleExchange(exchangeId) }
    }

}


