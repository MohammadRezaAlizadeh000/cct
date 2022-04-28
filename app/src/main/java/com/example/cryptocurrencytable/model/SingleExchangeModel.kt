package com.example.cryptocurrencytable.model

import com.google.gson.annotations.SerializedName

data class SingleExchangeModel(
    val id: String?,
    val name: String?,
    val dataEnd: String?,
    val dataOrderBookEnd: String?,
    val dataOrderBookStart: String?,
    val dataQuoteEnd: String?,
    val dataQuoteStart: String?,
    val dataStart: String?,
    val dataSymbolsCount: Int?,
    val dataTradeEnd: String?,
    val dataTradeStart: String?,
    val website: String?
)
