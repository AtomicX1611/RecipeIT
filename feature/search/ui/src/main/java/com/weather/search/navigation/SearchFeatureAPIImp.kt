package com.weather.search.navigation

import androidx.navigation.*
import androidx.navigation.compose.composable
import com.weather.common.navigation.NavRoutes
import com.weather.common.navigation.NavigationSubGraphRoute

class SearchFeatureAPIImp : SearchFeatureAPI{

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {

        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavRoutes.RecipeList.route ){

            composable(route = NavRoutes.RecipeList.route){

            }
            composable(route = NavRoutes.RecipeDetail.route){

            }
        }
    }


}