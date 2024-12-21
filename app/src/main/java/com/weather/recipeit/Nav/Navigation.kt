package com.weather.recipeit.Nav

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.weather.common.navigation.NavigationSubGraphRoute

@Composable
fun Navigation(modifier: Modifier = Modifier, navSubGraphs: NavSubGraphs){
    val navHostController = rememberNavController()
    NavHost(
        navController = navHostController,
        startDestination = NavigationSubGraphRoute.Search.route ){
        navSubGraphs.searchFeatureAPI.registerGraph(
            navHostController = navHostController,
            navGraphBuilder = this)
      }
}