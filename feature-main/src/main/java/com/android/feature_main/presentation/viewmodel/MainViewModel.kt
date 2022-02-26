package com.android.feature_main.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.feature_main.data.HomeRepository
import com.android.feature_main.domain.model.HomeData
import com.android.feature_main.domain.repo.IHomeRepository
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: HomeRepository
) : ViewModel() {

    private val _homeData = MutableLiveData<HomeData>()
    val homeData: LiveData<HomeData> get() = _homeData

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, throwable ->
        throwable.printStackTrace()
    }

    fun requestHomeData() {
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            val result = repository.requestHomeData()
            _homeData.postValue(result)
        }
    }
}