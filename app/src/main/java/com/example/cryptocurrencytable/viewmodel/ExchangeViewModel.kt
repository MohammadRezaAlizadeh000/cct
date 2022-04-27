package com.example.cryptocurrencytable.viewmodel

import androidx.lifecycle.ViewModel
import com.example.cryptocurrencytable.usecase.ExchangesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    useCase: ExchangesUseCase
) : ViewModel(){

    val exChanges = useCase.getAllExchanges()

}