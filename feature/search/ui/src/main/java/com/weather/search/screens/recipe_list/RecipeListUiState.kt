package com.weather.search.screens.recipe_list

import com.weather.common.utils.UiText
import com.weather.domain.model.DomainModel

object RecipeListUiState {

    data class UiState(
      val data : List<DomainModel>? = null,
      val isLoading : Boolean = false,
      val error : UiText = UiText.Idle
    )

    sealed interface Navigation{
       data class ToRecipeDetail(val id : String? = null) : Navigation
    }

    sealed interface Event{
        data class SearchRecipe(val q : String) : Event
        data class GoToRecipe(val id : String? = null) : Event
    }
}