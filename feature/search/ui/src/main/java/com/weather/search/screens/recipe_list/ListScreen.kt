package com.weather.search.screens.recipe_list

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.LocalLifecycleOwner
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import com.weather.common.navigation.NavRoutes
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecipeListScreen(
      modifier: Modifier = Modifier,
      recipeListViewModel: RecipeListViewModel,
      navHostController: NavHostController,
      navigateToDetail : (String?) -> Unit
) {
      val uiState = recipeListViewModel.uiState.collectAsState()
      val query = rememberSaveable {
            mutableStateOf("")
      }

      val lifeCycleOwner = LocalLifecycleOwner.current

      LaunchedEffect(key1 = recipeListViewModel.navigation) {
            recipeListViewModel.navigation.flowWithLifecycle(lifeCycleOwner.lifecycle)
                  .collectLatest {
                        when(it){
                              is RecipeListUiState.Navigation.ToRecipeDetail -> {
                                    navHostController.navigate(NavRoutes.RecipeDetail.sendID(it.id))
                              }
                        }
                  }
      }

      Scaffold(
            topBar = {
                  TextField(
                        value = query.value,
                        placeholder = {
                              Text(text = "Search")
                        },
                        onValueChange = { query.value = it },
                        keyboardOptions = KeyboardOptions.Default.copy(
                              imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                              onSearch = {
                                    Log.v("ERROR TAG", query.value)
                                    recipeListViewModel.onEvent(RecipeListUiState.Event.SearchRecipe(q = query.value))
                              }
                        ), modifier = Modifier.fillMaxWidth()
                  )
            }
      ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).padding(top = 4.dp).fillMaxSize()) {
                     if(!uiState.value.data.isNullOrEmpty()){
                           LazyColumn(modifier = Modifier.fillMaxSize()){
                                 if(uiState.value.data!=null){
                                       items(uiState.value.data!!){
                                             RecipeCard(modifier = Modifier.padding(top = 8.dp), recipeDetails = it){ id ->
                                                   navigateToDetail(id)
                                             }
                                       }
                                 }
                           }
                     }else{
                           Box(modifier = Modifier.fillMaxSize()){
                               Column(modifier = Modifier.fillMaxSize(),
                                     verticalArrangement = Arrangement.Center,
                                     horizontalAlignment = Alignment.CenterHorizontally) {
                                        CircularProgressIndicator()
                               }
                           }
                     }
            }
      }
}
