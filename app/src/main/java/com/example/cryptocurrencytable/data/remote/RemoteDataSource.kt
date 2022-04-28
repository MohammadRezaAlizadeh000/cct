package com.example.cryptocurrencytable.data.remote

import com.example.cryptocurrencytable.data.mapper.ExchangesMapper
import com.example.cryptocurrencytable.data.network.CCTAPIService
import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import com.example.cryptocurrencytable.model.SingleExchangeModel
import com.example.cryptocurrencytable.utils.*
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class RemoteDataSource @Inject constructor(
    private val cctAPIService: CCTAPIService,
    private val helperImpl: RemoteDataSourceHelper
) {

    fun getAllExchanges(): Single<AppState<List<SingleExchangeModel>>> {
        return helperImpl.getCallback({ cctAPIService.getExchangesList() }, ExchangesMapper)
    }

    fun getSingleExchange(exchangeId: String): Single<AppState<SingleExchangeResponseModel>> {
        return helperImpl.getResponse { cctAPIService.getSingleExchange(exchangeId) }
    }

}


