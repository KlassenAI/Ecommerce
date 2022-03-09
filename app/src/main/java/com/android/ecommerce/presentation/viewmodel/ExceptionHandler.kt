package com.android.ecommerce.presentation.viewmodel

import kotlinx.coroutines.CoroutineExceptionHandler

object ExceptionHandler {
    fun get() = CoroutineExceptionHandler { _, throwable -> throwable.printStackTrace() }
}