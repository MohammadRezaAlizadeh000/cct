package com.example.cryptocurrencytable.utils

sealed class AppState<T>(val data: T? = null, val message: String? = null) {

    class Loading<T>: AppState<T>()
    class Success<T>(data: T): AppState<T>(data = data)
    class Error<T>(message: String?): AppState<T>(message = message)
//    class NoInternet<T>: AppState<T>()

}
