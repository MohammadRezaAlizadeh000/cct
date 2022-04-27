package com.example.cryptocurrencytable.data

import com.example.cryptocurrencytable.data.network.CCTAPIService
import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import com.example.cryptocurrencytable.utils.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cctAPIService: CCTAPIService,
    private val helperImpl: RemoteDataSourceHelper
) {

    fun getAllExchanges(): Single<AppState<List<ExchangesResponseModelItem>>> {
        return helperImpl.getCallback { cctAPIService.getExchangesList() }
    }

    fun getSingleExchange(exchangeId: String): Single<AppState<ExchangesResponseModelItem>> {
        return helperImpl.getResponse { cctAPIService.getSingleExchange(exchangeId) }
    }

}


