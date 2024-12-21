package com.weather.search.screens.recipe_list

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun RecipeListScreen(
      modifier: Modifier = Modifier,
      recipeListViewModel: RecipeListViewModel
) {
      val uiState = recipeListViewModel.uiState.collectAsState()
      val query = rememberSaveable {
            mutableStateOf("")
      }

      Scaffold(
            topBar = {
                  TextField(
                        value = query.value,
                        placeholder = {
                              Text(text = "Search")
                        },
                        onValueChange = { query.value = it },
                        label = { Text("Search Recipes") },
                        keyboardOptions = KeyboardOptions.Default.copy(
                              imeAction = ImeAction.Search
                        ),
                        keyboardActions = KeyboardActions(
                              onSearch = {
                                    recipeListViewModel.onEvent(RecipeListUiState.Event.SearchRecipe(q = query.value))
                              }
                        ), modifier = Modifier.fillMaxWidth()
                  )
            }
      ) { paddingValues ->
            Column(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
                     if(uiState.value.data?.size!=null){
                           LazyColumn(modifier = Modifier.fillMaxSize()){
                                 items(uiState.value.data!!){
                                       RecipeCard(recipeDetails = it)
                                 }
                           }
                     }
            }
      }
}


@Preview(showBackground = true)
@Composable
fun RecipeListPreview(modifier: Modifier = Modifier) {
}