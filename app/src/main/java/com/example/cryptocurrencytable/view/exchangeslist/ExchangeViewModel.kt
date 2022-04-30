package com.example.cryptocurrencytable.view.exchangeslist

import androidx.lifecycle.ViewModel
import com.example.cryptocurrencytable.model.SingleExchangeModel
import com.example.cryptocurrencytable.usecase.ExchangesUseCase
import com.example.cryptocurrencytable.utils.AppState
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

@HiltViewModel
class ExchangeViewModel @Inject constructor(
    useCase: ExchangesUseCase
) : ViewModel() {

    val exChangesObserver = useCase.getAllExchanges()

}