package com.example.cryptocurrencytable.utils

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.cryptocurrencytable.R
import com.example.cryptocurrencytable.data.mapper.BasicMapper
import com.example.cryptocurrencytable.data.mapper.ExchangesMapper
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Response<T>.toAppState(): AppState<T> {
    return if (this.isSuccessful) {
        if (this.body() != null) {
            AppState.Success(data = this.body()!!)
        } else {
            AppState.Error(RequestErrorHandler.getErrorMessage(this.code()))
        }
    } else {
        AppState.Error(RequestErrorHandler.getErrorMessage(this.code()))
    }
}

fun <T, R> Call<T>.toAppState(result: (state: AppState<R>) -> Unit, mapper: BasicMapper<T, R>) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.d("REQUEST_TAG", "in getResponseCallback onResponse")
            if (response.body() != null) {
                result(AppState.Success(mapData(mapper, response.body()!!)))
            } else {
                result(AppState.Error(RequestErrorHandler.getErrorMessage(response.code())))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("REQUEST_TAG", "in getResponseCallback onFailure")
            result(AppState.Error(message = "request Failed ====> ${t.message}"))
        }

    })


}

fun <T> Call<T>.toAppState(result: (state: AppState<T>) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.d("REQUEST_TAG", "in getResponseCallback onResponse")
            if (response.body() != null) {
                result(AppState.Success(data = response.body()!!))
            } else {
                result(AppState.Error(RequestErrorHandler.getErrorMessage(response.code())))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("REQUEST_TAG", "in getResponseCallback onFailure")
            result(AppState.Error(message = "request Failed ====> ${t.message}"))
        }

    })


}

private fun <T, R> mapData(mapper: BasicMapper<T, R>, oldDAta: T): R {
    return mapper.map(oldDAta)
}
