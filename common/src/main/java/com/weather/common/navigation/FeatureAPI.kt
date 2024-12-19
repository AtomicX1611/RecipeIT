package com.weather.common.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureAPI {
    fun registerGraph(
        navGraphBuilder :  NavGraphBuilder,
        navHostController: NavHostController
    )
}