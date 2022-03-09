package com.android.feature_details.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.feature_details.data.DetailsRepository
import com.android.feature_details.domain.model.Product
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class DetailsViewModel(
    private val repository: DetailsRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    private val _product = MutableLiveData<Product>()
    val product: LiveData<Product> get() = _product

    fun requestDetails() {
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            _product.postValue(repository.requestDetailsData())
        }
    }

    fun addProduct(color: String, capacity: String) {
        val product = product.value!!
        val entity = product.toEntity(color, capacity)
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            repository.addProduct(entity)
        }
    }
}