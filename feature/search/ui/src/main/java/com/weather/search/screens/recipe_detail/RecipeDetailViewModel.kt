package com.weather.search.screens.recipe_detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.common.utils.NetworkResult
import com.weather.common.utils.UiText
import com.weather.domain.use_cases.GetRecipeDetailUseCase
import com.weather.search.screens.recipe_list.RecipeListUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

class RecipeDetailViewModel @Inject constructor(
    private val getRecipeDetailUseCase: GetRecipeDetailUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(RecipeDetailUiState.UiState())
    val uiState : StateFlow<RecipeDetailUiState.UiState> get() = _uiState.asStateFlow()

    fun onEvent(event : RecipeDetailUiState.Event){
        when(event){
            is RecipeDetailUiState.Event.FetchRecipeDetails -> {
                displayDetail(event.id)
            }
        }
    }

    private fun displayDetail(id : String) = viewModelScope.launch {
        getRecipeDetailUseCase.invoke(id)
            .onEach { res ->
                when(res){
                    is NetworkResult.Error -> {
                        _uiState.update {
                            RecipeDetailUiState.UiState(
                                error = UiText.RemoteString(res.message.toString())
                            )
                        }
                    }
                    is NetworkResult.Loading -> {
                       _uiState.update {
                           RecipeDetailUiState.UiState(isLoading = true)
                       }
                    }
                    is NetworkResult.Success -> {
                      _uiState.update {
                          RecipeDetailUiState.UiState(data = res.data)
                      }
                    }
                }
            }
    }


}