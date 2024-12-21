package com.weather.search.navigation

import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.*
import androidx.navigation.compose.composable
import com.weather.common.navigation.NavRoutes
import com.weather.common.navigation.NavigationSubGraphRoute
import com.weather.search.screens.recipe_list.RecipeListScreen
import com.weather.search.screens.recipe_list.RecipeListUiState
import com.weather.search.screens.recipe_list.RecipeListViewModel

class SearchFeatureAPIImp : SearchFeatureAPI{

    override fun registerGraph(
        navGraphBuilder: NavGraphBuilder,
        navHostController: NavHostController
    ) {

        navGraphBuilder.navigation(
            route = NavigationSubGraphRoute.Search.route,
            startDestination = NavRoutes.RecipeList.route ){

            composable(route = NavRoutes.RecipeList.route){
                val viewModel = hiltViewModel<RecipeListViewModel>()
                RecipeListScreen(recipeListViewModel = viewModel, navHostController = navHostController){
                   viewModel.onEvent(RecipeListUiState.Event.GoToRecipe(it))
                }
            }
            composable(route = NavRoutes.RecipeDetail.route){
                val mealId = it.arguments?.getString("id")
                   Text(text = "This is the END + $mealId")
            }
        }
    }


}