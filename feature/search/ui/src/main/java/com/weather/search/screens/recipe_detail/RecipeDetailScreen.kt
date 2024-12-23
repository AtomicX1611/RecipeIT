package com.weather.search.screens.recipe_detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun RecipeDetailScreen(modifier: Modifier = Modifier,recipeDetailViewModel: RecipeDetailViewModel){
    val uiState = recipeDetailViewModel.uiState.collectAsState()
    Column(
           modifier = modifier.fillMaxSize()
               .padding(4.dp)
    ){
          AsyncImage(
              model = uiState.value.data?.strMeal
             , contentDescription = null,
              contentScale = ContentScale.Crop,
              modifier = Modifier.fillMaxWidth()
                  .height(80.dp)
          )
          Spacer(modifier = Modifier.height(4.dp))

          Text(
              text = uiState.value.data?.strMeal.toString(),
              textAlign = TextAlign.Start,
              modifier = Modifier.padding(start = 8.dp,top = 4.dp)
          )

        Text(
            text = uiState.value.data?.strInstructions.toString(),
            textAlign = TextAlign.Start,
            modifier = Modifier.padding(start = 8.dp,top = 4.dp)
        )
    }
}

