package com.android.feature_cart.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.feature_cart.data.CartRepository
import com.android.feature_cart.domain.model.Cart
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class CartViewModel(
    private val repository: CartRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
): ViewModel() {

    val products = repository.getProducts()

    private val _cart = MutableLiveData<Cart>()
    val cart: LiveData<Cart> get() = _cart

    fun requestCart() {
        CoroutineScope(IO + coroutineExceptionHandler).launch {
            _cart.postValue(repository.requestCart())
        }
    }
}