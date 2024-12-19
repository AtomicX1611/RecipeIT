package com.weather.common.navigation

sealed class NavRoutes(val route : String) {

    data object RecipeList : NavRoutes("/list")
    data object RecipeDetail : NavRoutes("/detail/{id}"){
        fun sendID(id : String) = "/detail/${id}"
    }

}

sealed class NavigationSubGraphRoute(val route: String){

    data object Search : NavigationSubGraphRoute("/search")
}