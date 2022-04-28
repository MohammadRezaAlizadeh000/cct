package com.example.cryptocurrencytable.data.mapper

interface BasicMapper<T, R> {
    fun map(oldData: T): R
}