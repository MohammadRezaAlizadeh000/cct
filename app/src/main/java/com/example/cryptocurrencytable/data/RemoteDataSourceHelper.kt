package com.example.cryptocurrencytable.data

import com.example.cryptocurrencytable.utils.AppState
import io.reactivex.rxjava3.core.Single
import retrofit2.Call
import retrofit2.Response

interface RemoteDataSourceHelper {

    fun <T>getResponse(requestFunction: () -> Response<T>): Single<AppState<T>>
    fun <T>getCallback(requestFunction: () -> Call<T>): Single<AppState<T>>

}