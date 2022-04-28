package com.example.cryptocurrencytable.data.mapper

import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import com.example.cryptocurrencytable.model.SingleExchangeModel

object ExchangesMapper: BasicMapper<List<SingleExchangeResponseModel>, List<SingleExchangeModel>> {
    override fun map(oldData: List<SingleExchangeResponseModel>): List<SingleExchangeModel> {
        return oldData.map {
            SingleExchangeModel(
                it.exchangeId,
                it.name,
                it.dataEnd,
                it.dataOrderbookEnd,
                it.dataOrderbookStart,
                it.dataQuoteEnd,
                it.dataQuoteStart,
                it.dataStart,
                it.dataSymbolsCount,
                it.dataTradeEnd,
                it.dataTradeStart,
                it.website
            )
        }
    }
}
