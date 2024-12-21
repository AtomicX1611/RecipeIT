package com.weather.search.screens.recipe_list


import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.weather.common.utils.NetworkResult
import com.weather.common.utils.UiText
import com.weather.domain.use_cases.GetAllRecipeUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.forEach
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val getAllRecipeUseCase: GetAllRecipeUseCase
) : ViewModel(){

    private val _uiState = MutableStateFlow(RecipeListUiState.UiState())
    val uiState : StateFlow<RecipeListUiState.UiState>  get() = _uiState.asStateFlow()

    fun onEvent(event : RecipeListUiState.Event){
        when(event){
            is RecipeListUiState.Event.SearchRecipe -> onSearch(event.q)
        }
    }


        private fun onSearch(q : String) = viewModelScope.launch {
            Log.v("TAGG","Calling invoke")
            getAllRecipeUseCase.invoke(q)
                .onEach {res ->
                    when(res){
                        is NetworkResult.Error -> {
                            _uiState.update {
                                RecipeListUiState.UiState(error = UiText.RemoteString(res.message.toString()))
                            }
                        }
                        is NetworkResult.Success -> {
                            Log.v("TAGGG","${res.data}")
                            RecipeListUiState.UiState(data = res.data)}
                        is NetworkResult.Loading ->  {RecipeListUiState.UiState(isLoading = true)}
                    }

                }.launchIn(viewModelScope)
        }
}