package com.android.feature_main.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.feature_main.data.network.RetrofitService
import com.android.feature_main.domain.model.BestSeller
import com.android.feature_main.domain.model.HomeStore
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(
    private val service: RetrofitService
) : ViewModel() {

    private val _checkedItem = MutableLiveData(0)
    val checkedItem: LiveData<Int> get() = _checkedItem
    fun saveCheckedItem(position: Int) = _checkedItem.postValue(position)

    private val _hotSales = MutableLiveData<List<HomeStore>>()
    val hotSales: LiveData<List<HomeStore>> get() = _hotSales

    private val _bestSellers = MutableLiveData<List<BestSeller>>()
    val bestSellers: LiveData<List<BestSeller>> get() = _bestSellers

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun requestHome() {
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            val request = service.requestHome()
            if (request.isSuccessful) {
                val result = request.body()!!
                _hotSales.postValue(result.first().homeStore)
                _bestSellers.postValue(result.first().bestSeller)
            }
        }
    }
}