package com.example.cryptocurrencytable.data.remote

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.util.Log
import com.example.cryptocurrencytable.data.mapper.BasicMapper
import com.example.cryptocurrencytable.utils.AppState
import com.example.cryptocurrencytable.utils.toAppState
import dagger.hilt.android.qualifiers.ApplicationContext
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Response
import javax.inject.Inject

class RemoteDataSourceHelperImpl @Inject constructor(
    private val context: Context
) : RemoteDataSourceHelper {

    override fun <T> getResponse(requestFunction: () -> Response<T>): Single<AppState<T>> {
        return Single.create<AppState<T>> { emitter ->
            try {
                emitter.onSuccess(requestFunction().toAppState())
            } catch (e: Exception) {
                emitter.onError(e)
                Log.d("REQUEST_TAG", "in remote data source =====> ${e.message}")
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun <T> getCallback(requestFunction: () -> Call<T>): Single<AppState<T>> {
        return Single.create<AppState<T>> { emitter ->
            try {
                requestFunction().toAppState {
                    emitter.onSuccess(it)
                }
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }

    override fun <T, R> getCallback(
        requestFunction: () -> Call<T>,
        mapper: BasicMapper<T, R>
    ): Single<AppState<R>> {
        return Single.create<AppState<R>> { emitter ->
            try {
                if (checkInternetConnection())
                    requestFunction().toAppState({ emitter.onSuccess(it) }, mapper)
                else
                    emitter.onSuccess(AppState.Error("اینترنت خود را چک کنید"))
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }.subscribeOn(Schedulers.io())
    }

    private fun checkInternetConnection(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val network = connectivityManager.activeNetwork ?: return false
            val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false
            when {
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
                activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
                else -> false
            }
        } else {
            @Suppress("DEPRECATION") val networkInfo =
                connectivityManager.activeNetworkInfo ?: return false
            @Suppress("DEPRECATION") networkInfo.isConnected
        }
    }

}