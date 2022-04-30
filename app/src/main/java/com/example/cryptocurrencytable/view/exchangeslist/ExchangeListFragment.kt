package com.example.cryptocurrencytable.view.exchangeslist

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import androidx.viewbinding.ViewBinding
import com.example.cryptocurrencytable.R
import com.example.cryptocurrencytable.data.model.SingleExchangeResponseModel
import com.example.cryptocurrencytable.databinding.ExchangesListFragmentBinding
import com.example.cryptocurrencytable.model.SingleExchangeModel
import com.example.cryptocurrencytable.utils.AppState
import com.example.cryptocurrencytable.utils.inflater
import com.example.cryptocurrencytable.utils.toast
import com.example.cryptocurrencytable.view.BaseFragment
import dagger.hilt.android.AndroidEntryPoint
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.schedulers.Schedulers

@AndroidEntryPoint
class ExchangeListFragment: Fragment() {

    private var _binding: ExchangesListFragmentBinding? = null
    private val binding get() = _binding!!

    private val viewModel: ExchangeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = ExchangesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        with(binding) {
            exchangesSwipeRefreshLayout.setOnRefreshListener {
                getExchangesList()
            }
        }

        getExchangesList()
    }

    private fun getExchangesList() {
        viewModel.exChangesObserver.observeOn(AndroidSchedulers.mainThread())
            .subscribe ({ data ->
                when(data) {
                    is AppState.Error -> {
                        Log.d("REQUEST_TAG", "${data.message}")
                        toast(data.message.toString())
                        binding.exchangesSwipeRefreshLayout.isRefreshing = false
                    }
                    is AppState.Loading -> Log.d("REQUEST_TAG", "in Fragment -------> onLoading")
                    is AppState.Success -> {
                        Log.d("REQUEST_TAG", "${data.data}")
                        toast("Data Received")
                        binding.exchangesSwipeRefreshLayout.isRefreshing = false
                    }
                }
            }, { e ->
                Log.d("REQUEST_TAG", " in fragment ----------> ${e.message}")
            })
    }
}