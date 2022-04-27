package com.example.cryptocurrencytable.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.cryptocurrencytable.utils.AppState
import com.example.cryptocurrencytable.viewmodel.ExchangeViewModel
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class ExchangeListFragment: Fragment() {

    private val viewModel: ExchangeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FrameLayout(container?.context!!)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.exChanges.observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ data ->
                when(data) {
                    is AppState.Error -> Log.d("REQUEST_TAG", "${data.message}")
                    is AppState.Loading -> Log.d("REQUEST_TAG", "in Fragment -------> onLoading")
                    is AppState.Success -> Log.d("REQUEST_TAG", "${data.data}")
                }
            }, { e ->
                Log.d("REQUEST_TAG", " in fragment ----------> ${e.message}")
            })
    }
}