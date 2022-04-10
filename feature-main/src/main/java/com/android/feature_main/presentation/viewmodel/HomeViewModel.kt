package com.android.feature_main.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.core.utils.Constants
import com.android.feature_main.data.HomeRepository
import com.android.feature_main.domain.model.HomeData
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: HomeRepository,
    private val coroutineExceptionHandler: CoroutineExceptionHandler
) : ViewModel() {

    private val firebase = FirebaseDatabase.getInstance().getReference("data")

    val productCount = repository.getProductCount()

    private val _homeData = MutableLiveData<HomeData?>()
    val homeData: LiveData<HomeData?> get() = _homeData

    fun requestInitialData() {
        val mainId = repository.settings.getString(Constants.PREF_KEY_MAIN_ID_2, null)
        if (mainId == null) {
            firebase.get().addOnSuccessListener {
                val homeData = it.getValue(HomeData::class.java)!!
                doCoroutine {
                    repository.saveHomeDataFields(Constants.PREF_KEY_MAIN_ID_2, homeData)
                }
                _homeData.postValue(homeData)
            }.addOnFailureListener {
                _homeData.postValue(null)
            }
        } else {
            doCoroutine {
                _homeData.postValue(repository.getHomeData(mainId))
            }
        }
    }

    fun requestHomeData() {
        doCoroutine {
            _homeData.postValue(repository.requestHomeData())
        }
    }

    private fun doCoroutine(action: suspend () -> Unit) {
        CoroutineScope(IO + coroutineExceptionHandler).launch { action() }
    }
}