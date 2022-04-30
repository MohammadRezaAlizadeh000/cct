package com.example.cryptocurrencytable.data

import com.example.cryptocurrencytable.data.remote.RemoteDataSource
import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import com.example.cryptocurrencytable.model.SingleExchangeModel
import com.example.cryptocurrencytable.usecase.ExchangesUseCase
import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class Repository @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource
) : ExchangesUseCase {

    override fun getAllExchanges(): Single<AppState<List<SingleExchangeModel>>> {
        return remoteDataSource.getAllExchanges()
    }

    override fun getSingleExchange(exchangeId: String): Single<AppState<SingleExchangeResponseModel>> {
        return remoteDataSource.getSingleExchange(exchangeId)
    }


}