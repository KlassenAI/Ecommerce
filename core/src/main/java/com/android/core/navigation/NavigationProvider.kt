package com.android.core.navigation

interface NavigationProvider {
    fun launch(navCommand: NavCommand)
}