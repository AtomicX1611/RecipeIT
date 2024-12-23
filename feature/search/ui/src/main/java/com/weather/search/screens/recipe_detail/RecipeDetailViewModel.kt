package com.weather.search.screens.recipe_detail

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.common.utils.NetworkResult
import com.weather.common.utils.UiText
import com.weather.domain.use_cases.GetRecipeDetailUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
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
        Log.v("MEAL display", id)

        val response = getRecipeDetailUseCase.invoke(id)

        response
            .collect{ res ->
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