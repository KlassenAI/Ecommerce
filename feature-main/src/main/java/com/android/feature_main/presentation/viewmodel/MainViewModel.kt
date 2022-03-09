package com.android.feature_main.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.feature_main.data.HomeRepository
import com.android.feature_main.domain.model.HomeData
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: HomeRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    val productCount = repository.getProductCount()

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> get() = _homeData

    fun requestHomeData() {
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            val result = repository.requestHomeData()
            _homeData.postValue(result)
        }
    }
}