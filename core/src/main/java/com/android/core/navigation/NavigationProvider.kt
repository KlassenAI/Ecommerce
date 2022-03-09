package com.android.core.navigation

import com.android.core.navigation.NavCommand

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}