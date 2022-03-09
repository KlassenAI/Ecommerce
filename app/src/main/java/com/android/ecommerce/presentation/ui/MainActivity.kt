package com.android.ecommerce.presentation.ui

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.android.core.navigation.NavCommand
import com.android.core.navigation.NavCommands
import com.android.core.navigation.NavigationProvider
import com.android.ecommerce.R

class MainActivity : AppCompatActivity(), NavigationProvider {

    private val navController: NavController get() = findNavController(R.id.container)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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