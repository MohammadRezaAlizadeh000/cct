package com.example.cryptocurrencytable.data

import android.util.Log
import com.example.cryptocurrencytable.utils.AppState
import com.example.cryptocurrencytable.utils.toAppState
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response

class RemoteDataSourceHandlerImpl: RemoteDataSourceHandler {

    override fun <T>getResponse(requestFunction: () -> Response<T>): Single<AppState<T>> {
        return Single.create<AppState<T>?> { emitter ->
            try {
                emitter.onSuccess(requestFunction().toAppState())
            } catch (e: Exception) {
                emitter.onError(e)
                Log.d("REQUEST_TAG", "in remote data source =====> ${e.message}")
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun <T>getCallback(requestFunction: () -> Call<T>): Single<AppState<T>> {
        return Single.create<AppState<T>?> { emitter ->
            try {
                requestFunction().toAppState { emitter.onSuccess(it) }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }

}