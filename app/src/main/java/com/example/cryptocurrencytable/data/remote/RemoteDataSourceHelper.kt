package com.example.cryptocurrencytable.data.remote


import com.example.cryptocurrencytable.data.mapper.BasicMapper
import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response

interface RemoteDataSourceHelper {

    fun <T> getResponse(requestFunction: () -> Response<T>): Single<AppState<T>>
    fun <T> getCallback(requestFunction: () -> Call<T>): Single<AppState<T>>
    fun <T, R> getCallback(
        requestFunction: () -> Call<T>,
        mapper: BasicMapper<T, R>
    ): Single<AppState<R>>
//    fun checkInternetConnection(): Boolean

}