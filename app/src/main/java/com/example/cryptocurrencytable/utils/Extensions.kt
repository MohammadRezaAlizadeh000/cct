package com.example.cryptocurrencytable.utils

import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.cryptocurrencytable.model.ExchangesResponseModelItem
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

fun <T> Response<T>.toAppState(): AppState<T> {
    return if (this.isSuccessful) {
        if (this.body() != null) {
            AppState.Success(data = this.body()!!)
        } else {
            AppState.Error(message = "body is null")
        }
    } else {
        AppState.Error(message = "response failed")
    }
}

fun <T> Call<T>.toAppState(result: (state: AppState<T>) -> Unit) {
    this.enqueue(object : Callback<T> {
        override fun onResponse(call: Call<T>, response: Response<T>) {
            Log.d("REQUEST_TAG", "in getResponseCallback onResponse")
            if (response.body() != null) {
                result(AppState.Success(data = response.body()!!))
            } else {
                result(AppState.Error(message = "body is null"))
            }
        }

        override fun onFailure(call: Call<T>, t: Throwable) {
            Log.d("REQUEST_TAG", "in getResponseCallback onFailure")
            result(AppState.Error(message = "request Failed ====> ${t.message}"))
        }

    })
}


fun AppCompatActivity.transaction(f: Fragment) {
    supportFragmentManager.beginTransaction()
        .replace(FRAGMENT_CONTAINER, f)
        .addToBackStack(null)
        .commit()
}