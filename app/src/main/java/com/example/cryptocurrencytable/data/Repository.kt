package com.example.cryptocurrencytable.data

import com.example.cryptocurrencytable.model.ExchangesResponseModel
import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import com.example.cryptocurrencytable.usecase.ExchangesUseCase
import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
): ExchangesUseCase {

    override fun getAllExchanges(): Single<AppState<List<ExchangesResponseModelItem>>> {
        return remoteDataSource.getAllExchanges()
    }

    override fun getSingleExchange(exchangeId: String): Single<AppState<ExchangesResponseModelItem>> {
        return remoteDataSource.getSingleExchange(exchangeId)
    }


}