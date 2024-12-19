package com.weather.search.screens.recipe_detail

import com.weather.common.utils.UiText
import com.weather.domain.model.RecipeDetails

object RecipeDetailUiState {

    data class UiState(
        val error : UiText = UiText.Idle,
        val data : RecipeDetails? = null,
        val isLoading : Boolean = false
    )

    sealed interface Navigation{

    }

    sealed interface Event{
           data class FetchRecipeDetails(val id : String) : Event
    }
}