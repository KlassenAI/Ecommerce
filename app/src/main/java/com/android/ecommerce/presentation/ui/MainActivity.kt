package com.android.ecommerce.presentation.ui

import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavCommands
import com.android.core.navigation.NavigationProvider
import com.android.ecommerce.R
import com.android.feature_main.presentation.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val navController: NavController get() = findNavController(R.id.container)
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.d("VM", "actvivity: $viewModel")
        initData()
    }

    private fun initData() {
        viewModel.requestInitialData()
    }

    override fun launch(navCommand: NavCommand) {
        when (val target = navCommand.target) {
            is NavCommands.DeepLink -> openDeepLink(
                url = target.url
            )
        }
    }

    private fun openDeepLink(url: Uri) {
        navController.navigate(url, null)
    }
}